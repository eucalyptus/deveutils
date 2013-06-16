import edu.ucsb.eucalyptus.cloud.entities.WalrusSnapshotInfo;
import edu.ucsb.eucalyptus.cloud.entities.ObjectInfo;
import com.eucalyptus.entities.Entities;
import javax.persistence.EntityTransaction;
import com.eucalyptus.scripting.Groovyness;

objectPath="CHANGEME";

//Returns the snapshotId of the snapshot in the given bucket path
def getSnapshotFilePath(String objectPath) {
	String[] parts = objectPath.split("/",2);
	String keyName = parts[1];
	String bucket = parts[0];

	StringBuilder statusString = new StringBuilder();
	EntityTransaction db = Entities.get(ObjectInfo.class);
	ObjectInfo searchObj = Groovyness.expandoMetaClass(new ObjectInfo());
	searchObj.setBucketName(bucket);
	Entities.query(searchObj).collect() { walrusObject ->
		vol = Groovyness.expandoMetaClass(walrusObject);
		statusString.append('Snapshot Id: ' + walrusObject.getObjectKey());
		statusString.append(' Snapste/File Path: ' + walrusObject.getBucketName() + "/" + walrusObject.getObjectName());
		statusString.append(' Size: ' + walrusObject.getSize() + '\n');
	}
	db.commit();
	return statusString.toString();
}

status="";
status = "Snapshot File -> Snapshot lookup result: \n" + getSnapshotFilePath(objectPath);
return status;
