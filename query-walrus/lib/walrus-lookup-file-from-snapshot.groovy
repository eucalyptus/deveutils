import edu.ucsb.eucalyptus.cloud.entities.WalrusSnapshotInfo;
import edu.ucsb.eucalyptus.cloud.entities.ObjectInfo;
import com.eucalyptus.entities.Entities;
import javax.persistence.EntityTransaction;
import com.eucalyptus.scripting.Groovyness;

snapshotId="CHANGEME";

//Returns the directory in $EUCALYPTUS/var/lib/bukkits that contains the snapshot corresponding to the snapshotId.
def getFileFromSnapId(String snapId) {
	StringBuilder statusString = new StringBuilder();
	EntityTransaction db = Entities.get(ObjectInfo.class);
	WalrusSnapshotInfo searchObj = Groovyness.expandoMetaClass(new WalrusSnapshotInfo());
	searchObj.setSnapshotId(snapId);
	Entities.query(searchObj).collect() { walrusObject ->
		vol = Groovyness.expandoMetaClass(walrusObject);
		statusString.append('Snapshot object key: ' + walrusObject.getSnapshotId());
		statusString.append(' Bucket directory: ' + walrusObject.getSnapshotBucket());
		statusString.append(' Snapshot Size: ' + walrusObject.getSize() + '\n');
	}
	db.commit();
	return statusString.toString();
}

status="";
status = "SnapshotId -> File lookup result: \n" + getFileFromSnapId(snapshotId);
return status;
