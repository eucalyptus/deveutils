import com.eucalyptus.objectstorage.entities.ObjectInfo;
import com.eucalyptus.entities.Entities;
import javax.persistence.EntityTransaction;
import com.eucalyptus.scripting.Groovyness;

objectPath="CHANGEME";

//Returnes the object key and bucket associated with the file path
def getFileFromObject(String objectPath) {
	String[] parts = objectPath.split("/",2);
	String keyName = parts[1];
	String bucket = parts[0];

	StringBuilder statusString = new StringBuilder();
	EntityTransaction db = Entities.get(ObjectInfo.class);
	ObjectInfo searchObj = Groovyness.expandoMetaClass(new ObjectInfo(bucket, keyName));
	Entities.query(searchObj).collect() { walrusObject ->
		vol = Groovyness.expandoMetaClass(walrusObject);
		statusString.append('Object: ' + walrusObject.getObjectKey());
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
status = "Object -> File lookup result: \n" + getFileFromObject(objectPath);
return status;
