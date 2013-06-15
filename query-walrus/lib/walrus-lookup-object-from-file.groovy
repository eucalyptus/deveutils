import edu.ucsb.eucalyptus.cloud.entities.ObjectInfo;
import com.eucalyptus.entities.Entities;
import javax.persistence.EntityTransaction;
import com.eucalyptus.scripting.Groovyness;

filePath="CHANGEME";

//Returns the object key and stats associated with the input file
def getObjectFromFile(String file) {
	String[] parts = file.split("/",2);
	String filename = parts[1];
	String bucketDir = parts[0];

	StringBuilder statusString = new StringBuilder();
	EntityTransaction db = Entities.get(ObjectInfo.class);
	ObjectInfo searchObj = Groovyness.expandoMetaClass(new ObjectInfo());
	searchObj.setBucketName(bucketDir);
	searchObj.setObjectName(filename);
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
status = "File -> object lookup result: \n" + getObjectFromFile(filePath);
return status;
