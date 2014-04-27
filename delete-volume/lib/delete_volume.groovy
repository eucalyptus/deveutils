import com.eucalyptus.blockstorage.entities.VolumeInfo;
import com.eucalyptus.entities.Entities;
import javax.persistence.EntityTransaction;
import com.eucalyptus.scripting.Groovyness;

volume="CHANGEME";

//Mark the volume as deletion
def markForDelete() {	
    StringBuilder output = new StringBuilder();
    EntityTransaction db = Entities.get(VolumeInfo.class);
    VolumeInfo volumeInfo = new VolumeInfo();
    volumeInfo.setVolumeId(volume);
    VolumeInfo foundvol = Entities.uniqueResult(volumeInfo);
    foundvol.setStatus("deleting");
    db.commit();
    output.append("\nVolume: " + foundvol.getVolumeId() + " marked for deletion");
    return output.toString();
}
return markForDelete()
