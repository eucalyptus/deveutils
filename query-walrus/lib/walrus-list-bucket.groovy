import edu.ucsb.eucalyptus.cloud.entities.ObjectInfo;
import com.eucalyptus.entities.Entities;
import javax.persistence.EntityTransaction;
import com.eucalyptus.scripting.Groovyness;

bucket="CHANGEME";

def getListBucketFiles(String bucketName) {
	StringBuilder statusString = new StringBuilder();
	EntityTransaction db = Entities.get(ObjectInfo.class);
	ObjectInfo searchObj = Groovyness.expandoMetaClass(new ObjectInfo());
	searchObj.setBucketName(bucketName);
	Entities.query(searchObj).collect() { walrusObject ->
		vol = Groovyness.expandoMetaClass(walrusObject);
		statusString.append('Object Key: ' + walrusObject.getObjectKey());
		statusString.append(' File Path: ' + bucket + "/" + walrusObject.getObjectName());
		statusString.append(' Version: ' + walrusObject.getVersionId());
		statusString.append(' Size: ' + walrusObject.getSize());
		statusString.append(' Last Modified: ' + walrusObject.getLastModified());
		statusString.append(' Etag: ' + walrusObject.getEtag() + '\n');
	}
	db.commit();
	return statusString.toString();
}

status="";
status = "Bucket listing result: \n" + getListBucketFiles(bucket);
return status;
