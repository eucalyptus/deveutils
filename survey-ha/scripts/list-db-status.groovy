import com.eucalyptus.bootstrap.Hosts;

import net.sf.hajdbc.sql.DriverDatabaseClusterMBean
import org.logicalcobwebs.proxool.ProxoolFacade
import org.logicalcobwebs.proxool.admin.SnapshotIF
import com.eucalyptus.bootstrap.Databases
import com.eucalyptus.entities.PersistenceContexts
import com.eucalyptus.util.Mbeans
import com.google.common.collect.ImmutableMap

def verbose = true;
def lookupCluster = { ctx ->
  Mbeans.lookup( Databases.jdbcJmxDomain,
      ImmutableMap.builder( ).put( "cluster", ctx ).build( ),
      DriverDatabaseClusterMBean.class );
}
def summarizeCluster = { DriverDatabaseClusterMBean db, String ctx ->
  def dbHosts = Hosts.listActiveDatabases( ).collect{ it.getDisplayName( ) };
  def active = db.getActiveDatabases( );
  def inactive = db.getInactiveDatabases( );
  def aliveActive = db.getAliveMap( active.collect{ db.getDatabase(it) } );
  def aliveInactive = db.getAliveMap( inactive.collect{ db.getDatabase(it) } );
  def dbConfig = ProxoolFacade.getConnectionPoolDefinition( ctx )
  def dbStats = ProxoolFacade.getStatistics( ctx )
  def errors = []
  if (!active.containsAll( dbHosts ) ) {
    errors << "ERROR Mismatch between active database connections and database hosts: connections=${active} hosts=${dbHosts}"
  }
  if (!inactive.isEmpty( ) ) {
    errors << "ERROR Inactive database connections found (is the host down?): inactive=${inactive}"
  }
  if (!aliveActive.get(false).isEmpty()) {
    errors << "ERROR Found active database connections which are not alive (is the host down?): active-but-dead=${aliveActive.get(false)}"
  }
  if ( !dbHosts.containsAll(aliveActive.get(true).collect{ it.id })) {
    errors << "ERROR Failed to find healthy active connections for each database host: connections=${aliveActive.get(true)} hosts=${dbHosts}"
  }
  SnapshotIF dbSnap = ProxoolFacade.getSnapshot( ctx );
  def result = [
    'active-hosts':active,
  ]
  if(verbose||!errors.isEmpty( )) {
    result.putAll([
      'db-hosts':dbHosts,
      'inactive-hosts':inactive,
      'active-connections':[
        'alive':aliveActive.get(true),
        'dead':aliveActive.get(false),
      ],
      'inactive-connections':[
        'alive':aliveInactive.get(true),
        'dead:':aliveInactive.get(false),
      ],
      'available/active/offline/max/served/refused':"${dbSnap.availableConnectionCount}/${dbSnap.activeConnectionCount}/${dbSnap.offlineConnectionCount}/${dbSnap.maximumConnectionCount}/${dbSnap.servedCount}/${dbSnap.refusedCount}",
    ])
  }
  result.putAll(['health-checks':errors.isEmpty( )?["OK"]:errors])
  result
}
def log = { ctx, args ->
  args = ( args instanceof List ? args : [args])
  "\n${sprintf '%s %-30.30s %s', new Date(), ctx, args.join(' ')}"
}
PersistenceContexts.list( ).collect{ ctx ->
  DriverDatabaseClusterMBean db = lookupCluster(ctx)
  def summary = ""
  def clusterSummary = summarizeCluster(db,ctx)
  if ( verbose || clusterSummary.size() < 3 ) {
    summary += log(ctx,clusterSummary)
  } else {
    clusterSummary.each{
      summary += log(ctx,it)
    }
  }
  summary
}
