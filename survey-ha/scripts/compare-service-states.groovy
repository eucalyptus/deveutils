import com.eucalyptus.component.Component
import com.eucalyptus.component.Components
import com.eucalyptus.component.ServiceConfiguration
import com.eucalyptus.component.Topology
import com.eucalyptus.component.Topology.ServiceKey
import com.eucalyptus.scripting.Groovyness

def result = []
Components.list( ).collect{ Component comp ->
  comp.services( ).collect{ Groovyness.expandoMetaClass(it) }.collect{ ServiceConfiguration conf ->
    boolean topoMatch = Component.State.ENABLED.equals(conf) && Topology.isEnabled( conf ) || !Component.State.ENABLED.equals(conf);
    if ( !topoMatch ) {
      result << "${conf.getFullName()} state=${conf.lookupState()} topology=${topoMatch}"
    }
  }
}
if(result.isEmpty( )) {
  "OK"
} else {
  result.collect{ 
    "\n${it}"
  }
}