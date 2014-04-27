import com.eucalyptus.objectstorage.entities.ImageCacheInfo;
import com.eucalyptus.entities.Entities;
import javax.persistence.EntityTransaction;
import com.eucalyptus.scripting.Groovyness;

//Lists all images that are cached.
def listCachedImages() {
	StringBuilder statusString = new StringBuilder();
	EntityTransaction db = Entities.get(ImageCacheInfo.class);
	ImageCacheInfo searchObj = Groovyness.expandoMetaClass(new ImageCacheInfo());
	searchObj.setInCache(true);
	Entities.query(searchObj).collect() { walrusObject ->
		vol = Groovyness.expandoMetaClass(walrusObject);
		statusString.append('Bucket: ' + walrusObject.getBucketName());
		statusString.append(' Image_Manifest: ' + walrusObject.getManifestName());
		statusString.append(' Image_File: ' + walrusObject.getImageName());
		statusString.append(' Size: ' + walrusObject.getSize() + '\n');
	}
	db.commit();
	return statusString.toString();
}

status="";
status = "Image ID -> Cached file lookup result: \n" + listCachedImages();
return status;
