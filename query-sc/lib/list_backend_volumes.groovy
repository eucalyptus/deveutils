import com.eucalyptus.blockstorage.entities.VolumeInfo;
import com.eucalyptus.blockstorage.entities.VolumeToken;
import com.eucalyptus.blockstorage.entities.VolumeExportRecord;
import com.eucalyptus.blockstorage.entities.ISCSIVolumeInfo;
import com.eucalyptus.blockstorage.entities.LVMVolumeInfo;
import com.eucalyptus.blockstorage.san.common.entities.SANVolumeInfo;
import com.eucalyptus.entities.Entities;
import javax.persistence.EntityTransaction;
import com.eucalyptus.scripting.Groovyness;
import com.eucalyptus.blockstorage.StorageManagers;
import com.eucalyptus.blockstorage.DASManager;

//Lists all the volumes, their status, tokens, and export records
def listVolumesTGT() {
	StringBuilder listingString = new StringBuilder("Backend-TGT Volume listing:\n");
	EntityTransaction db = Entities.get(ISCSIVolumeInfo.class);
	ISCSIVolumeInfo searchVol = Groovyness.expandoMetaClass(new ISCSIVolumeInfo());
	ISCSIVolumeInfo vol = null;
	Entities.query(searchVol).each{ volume ->
		vol= Groovyness.expandoMetaClass(volume);
		listingString.append('Volume: ' + volume.getVolumeId());
		listingString.append(' ' + printISCSIFields(vol) + '\n');
	}
	db.rollback();
	return listingString.toString();
}

def printISCSIFields(ISCSIVolumeInfo vol) {
	StringBuilder output = new StringBuilder();
	output.append(printLVMFields((LVMVolumeInfo) vol));
	output.append(' TGT Store Name:' + vol.getStoreName());
	output.append(' TGT tid:' + vol.getTid());
	output.append(' TGT lun:' + vol.getLun());
	output.append(' TGT Chap User:' + vol.getStoreUser());
	return output.toString();
}

def printLVMFields(LVMVolumeInfo vol) {
	StringBuilder output = new StringBuilder();
	output.append(' LVM LV Name:' + vol.getLvName());
	output.append(' LVM PV Name:' + vol.getPvName());
	output.append(' LVM VG Name:' + vol.getVgName());
	output.append(' Loopback Device:' + vol.getLoDevName());
	output.append(' Size:' + vol.getSize());
	output.append(' Status:' + vol.getStatus());
	return output.toString();
}

def printSANFields(com.eucalyptus.blockstorage.san.common.entities.SANVolumeInfo vol) {
	StringBuilder output = new StringBuilder();
	output.append(' VolumeId:' + vol.getVolumeId());
	output.append(' Status:' + vol.getStatus());
	output.append(' IQN:' + vol.getIqn());
	output.append(' StoreUser:' + vol.getStoreUser());
	output.append(' Size:' + vol.getSize());
	return output.toString();
}

def listVolumesSAN() {
	StringBuilder listingString = new StringBuilder("Backend-TGT Volume listing:\n");
	EntityTransaction db = Entities.get(com.eucalyptus.blockstorage.san.common.entities.SANVolumeInfo.class);
	com.eucalyptus.blockstorage.san.common.entities.SANVolumeInfo searchVol = Groovyness.expandoMetaClass(new com.eucalyptus.blockstorage.san.common.entities.SANVolumeInfo());
	com.eucalyptus.blockstorage.san.common.entities.SANVolumeInfo vol = null;
	Entities.query(searchVol).each{ volume ->
		vol= Groovyness.expandoMetaClass(volume);
		listingString.append('Volume: ' + volume.getVolumeId());
		listingString.append(' ' + printSANFields(vol) + '\n');
	}
	db.rollback();
	return listingString.toString();
}

def listFromBackend() {
	if(StorageManagers.getInstance() instanceof DASManager) {
		return listVolumesTGT();
	} else {
		return listVolumesSAN();
	}
}

return listFromBackend();
