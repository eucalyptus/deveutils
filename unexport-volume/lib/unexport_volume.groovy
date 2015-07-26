import com.eucalyptus.blockstorage.entities.VolumeInfo;
import com.eucalyptus.blockstorage.entities.VolumeToken;
import com.eucalyptus.blockstorage.entities.VolumeExportRecord;
import com.eucalyptus.entities.Entities;
import javax.persistence.EntityTransaction;
import com.eucalyptus.scripting.Groovyness;
import com.eucalyptus.blockstorage.StorageManagers;

volume="CHANGEME";

//Clear db export state
def clear_volume_export(String volumeId) {
		StringBuilder statusString = new StringBuilder();
		EntityTransaction db = Entities.get(VolumeInfo.class);
		VolumeInfo searchVol = Groovyness.expandoMetaClass(new VolumeInfo(volumeId));
		Entities.query(searchVol).collect() { volume ->
						vol = Groovyness.expandoMetaClass(volume);
						statusString.append('Volume: ' + vol.getVolumeId() + ' Status: ' + vol.getStatus() + ' Size: ' + vol.getSize() + 'GB Created: ' + vol.getCreateTime() + '\n');
						vol.getAttachmentTokens().each() { tok ->
										token = Groovyness.expandoMetaClass(tok);
										statusString.append('[' + vol.getVolumeId() + ']\t Token: ');
										statusString.append(VolumeInfo.redactToken(token.getToken()) + ' Is Valid: ' + token.getIsValid() + ' Created: ' + token.getCreationTimestamp() + '\n');

										token.getExportRecords().each() { export ->
														exportRecord = Groovyness.expandoMetaClass(export);
														statusString.append('[' + vol.getVolumeId() + ']\t\tExport to Host: ' + exportRecord.getHostIp() + ' IQN: ' + exportRecord.getHostIqn() + ' Is Active: ' + exportRecord.getIsActive() + ' Last Updated: ' + exportRecord.getLastUpdateTimestamp() + '\n');
														if(exportRecord.getIsActive()) {
																exportRecord.setIsActive(false);
																statusString.append('\t\t!!!Made this export inactive!!!\n');
														}
										}

										if(token.getIsValid()) {
												token.setIsValid(false);
												statusString.append('\t!!!Made this token invalid!!!\n');
										}
						}
		}
		db.commit();
		return statusString.toString();
}

//Do unexport
def unexportFromAll(String volumeId) {
		blockManager = StorageManagers.getInstance();
		blockManager.unexportVolumeFromAll(volumeId);
		return 'Unexported ' + volumeId;
}

status="";
status = "Unexport result: " + unexportFromAll(volume);
status += "\nClear export records result: " + clear_volume_export(volume);

return status;
