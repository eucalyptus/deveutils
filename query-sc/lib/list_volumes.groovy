import edu.ucsb.eucalyptus.cloud.entities.VolumeInfo;
import edu.ucsb.eucalyptus.cloud.entities.VolumeToken;
import edu.ucsb.eucalyptus.cloud.entities.VolumeExportRecord;
import com.eucalyptus.entities.Entities;
import javax.persistence.EntityTransaction;
import com.eucalyptus.scripting.Groovyness;

//Lists all the volumes, their status, tokens, and export records
def list_volumes() {
	StringBuilder listingString = new StringBuilder("Volume listing:\n");
	EntityTransaction db = Entities.get(VolumeInfo.class);
	VolumeInfo searchVol = Groovyness.expandoMetaClass(new VolumeInfo());
	Entities.query(searchVol).collect() { volume ->
			vol = Groovyness.expandoMetaClass(volume);
			listingString.append('Volume: ' + vol.getVolumeId() + ' Status: ' + vol.getStatus() + ' Size: ' + vol.getSize() + 'GB Created: ' + vol.getCreateTime() + '\n');
			vol.getAttachmentTokens().each() { tok ->
					token = Groovyness.expandoMetaClass(tok);
					listingString.append('[' + vol.getVolumeId() + ']\t Token: ');
					listingString.append(VolumeInfo.redactToken(token.getToken()) + ' Is Valid: ' + token.getIsValid() + ' Created: ' + token.getCreationTimestamp() + '\n');

					token.getExportRecords().each() { export ->
							exportRecord = Groovyness.expandoMetaClass(export);
							listingString.append('[' + vol.getVolumeId() + ']\t\tExport to Host: ' + exportRecord.getHostIp() + ' IQN: ' + exportRecord.getHostIqn() + ' Is Active: ' + exportRecord.getIsActive() + ' Last Updated: ' + exportRecord.getLastUpdateTimestamp() + '\n');
					}
			}
	}
	db.rollback();
	return listingString.toString();
}

return list_volumes();
