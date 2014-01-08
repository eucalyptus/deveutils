import groovy.sql.Sql
import java.sql.ResultSet
import com.eucalyptus.bootstrap.Databases
import com.eucalyptus.bootstrap.Host
import com.eucalyptus.bootstrap.Hosts
import com.eucalyptus.component.ServiceUris
import com.eucalyptus.component.id.Eucalyptus.Database
import com.eucalyptus.entities.PersistenceContexts

def SQL = { host, db, tx ->
  String url = String.format( "jdbc:%s", ServiceUris.remote( Database.class, host, db ) )
  def sql = Sql.newInstance( url, Databases.getUserName(), Databases.getPassword(), Databases.getDriverName() )
  try {
    return tx(sql)
  } catch( Exception ex ) {
    sql?.close()
    throw ex;
  }
}

def contexts = PersistenceContexts.list( )
def dbSummaries = [:]

Hosts.listActiveDatabases( ).collect{ Host h -> h.getBindAddress( ) }.collect{ host ->
  contexts.collect{ ctx ->
    db = new Expando(database : ctx, tables : [:], rows : [:], host : host )
    Databases.getBootstrapper( ).listTables( ctx ).each{ table ->
      SQL(host, ctx, { Sql sql ->
        try {
          sql.query "select * from "+table,{
            def meta = it.createMetaData()
            db.tables[table] = columnNames = (1..meta.columnCount).collect { meta.getColumnName(it) }
          }
//          sql.query "select count("+db.tables[table].get(0)+") count from "+table,{ ResultSet rs ->
//            rs.next( );
//            db.rows[table] = rs.getInt(1)
//          }
        } catch( Exception ex ) {
          throw new RuntimeException( "Failed doing: " + host + "/" + ctx + "/" + table + " for " + this + " because of " + ex, ex )
        }
      })
    }
    dbSummaries[ctx+host]=db
  }
}
contexts.collect{ ctx ->
  def ctxDbs = dbSummaries.findAll{ k, v -> v.database.contains(ctx) }.collect{ k,v->k }
  if ( !ctxDbs.size( ) == 2 ) {
    "Failed to find two databases for ${ctx}."
  } else {
    def db1 = dbSummaries[ctxDbs.get(0)]
    def db2 = dbSummaries[ctxDbs.get(1)]
    def mismatchedTables = Databases.getBootstrapper( ).listTables( ctx ).findAll{ table ->
      !db1.tables[table].equals(db2.tables[table])
    }
    def mismatchedRows = Databases.getBootstrapper( ).listTables( ctx ).findAll{ table ->
      !db1.rows[table].equals(db2.rows[table])
    }
    ["${ctx}":['mismatched-table-schemas':mismatchedTables,'mismatched-row-counts':mismatchedRows,'checked-hosts':ctxDbs]]
  }
}
//dbSummaries