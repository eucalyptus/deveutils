import org.logicalcobwebs.proxool.ConnectionPoolManager
import org.logicalcobwebs.proxool.ProxyConnection
import com.eucalyptus.entities.PersistenceContexts;
import org.apache.log4j.Logger

Logger LOG = Logger.getLogger("list-db-connection-threads")
def finder = { ProxyConnection p ->
	p.isActive() || p.isClosed()
}
//PersistenceContexts.list( ).collect{ ctx ->
["eucalyptus_cloud"].collect{ ctx ->
  ConnectionPoolManager.getInstance().getConnectionPool(ctx).getProxyConnections( ).findAll{ 
  	finder(it)
  }.collect{ ProxyConnection p ->
    stack = Thread.getAllStackTraces( ).findAll{ thread -> thread?.getKey()?.getName( ) == p.getRequester( ) }.collect{ Arrays.asList(it.getValue()) }
output = """
${p.id} active=${p.isActive( )} available=${p.isAvailable( )} closed=${p.isClosed( )} expiring=${p.isMarkedForExpiry( )} null=${p.isNull( )} offline=${p.isOffline( )} really-closed=${p.isReallyClosed( )} age=${p.age/1000.0}secs birthdate=${p.getBirthDate( )} reason=${p.getReasonForMark( )}
${p.id} last-sql=${p.getLastSqlCall( )}
${p.id} owning-thread=${p.getRequester( )}
${p.id} ${stack.collect{it.join("\n\t")}}"""
	LOG.info(output)
	output
  }
}
