import com.eucalyptus.bootstrap.Hosts;
import com.eucalyptus.bootstrap.Hosts.Coordinator;

def result = ""
Hosts.list( ).collect{ 
  result += "\nhost=${sprintf '%-15.15s booted=%-6.6s database=%-6.6s synced=%-6.6s coordinator=%-15.15s', it.getDisplayName( ),it.hasBootstrapped(),it.hasDatabase(),it.hasSynced( ),Hosts.getCoordinator( ).getDisplayName( )}"
}
result