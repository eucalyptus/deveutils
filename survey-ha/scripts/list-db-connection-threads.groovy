import org.logicalcobwebs.proxool.ConnectionPoolManager
import org.logicalcobwebs.proxool.ProxyConnection
import com.eucalyptus.entities.PersistenceContexts;

//PersistenceContexts.list( ).collect{ ctx ->
ctx = "eucalyptus_cloud"
  ConnectionPoolManager.getInstance().getConnectionPool(ctx).getProxyConnections( ).findAll{ !p.isAvailable() }.collect{ ProxyConnection p ->
    stack = Thread.getAllStackTraces( ).findAll{ thread -> thread?.getKey()?.getName( ) == p.getRequester( ) }.collect{ Arrays.asList(it.getValue()) }
    """
${p.id} active=${p.isActive( )} available=${p.isAvailable( )} closed=${p.isClosed( )} expiring=${p.isMarkedForExpiry( )} null=${p.isNull( )} offline=${p.isOffline( )} really-closed=${p.isReallyClosed( )} age=${p.age} birthdate=${p.getBirthDate( )} reason=${p.getReasonForMark( )}
${p.id} last-sql=${p.getLastSqlCall( )}
${p.id} owning-thread=${p.getRequester( )}
${p.id} """ + stack.collect{it.join("\n\t")}
  }
//}
