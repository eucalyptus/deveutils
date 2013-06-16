Lists the volume state from the SC database from either the 'frontend' or 'backend' of the storage controller.

Requirements to run:
* Eucalyptus 3.3.0+ Eucalyptus 3.2.x will cause these utilities to fail due to changes in internal structures
* Eucalyptus binaries installed
* EucaAdmin tools must be installed (euca-modify-property must work)
* Credentials: see next
* $EUCALYPTUS must be set to the root directory of the eucalyptus installation on the local host. For a package install, that is usually '/'. For a
source install it is whatever --with-prefix was set to during ./configure time of the source build.

* The Eucalyptus SC must be running on the local host.
* You must have admin credentials for the Eucalyptus install and have EC2_URL set to the local host. The easiest way to accomplish this is to source the eucarc file
on the local host (SC) after modifying the EC2_URL line to refer to the current local IP instead of the CLC.

* The SC has two sides, the 'frontend' and the 'backend'. The frontend handles the logical entities in EBS: volumes and snapshots. The backend is responsible for
implementing those entities on the specific backend that is configured for use on this SC. It maps Volumes to SAN LUNs, or TGT targets, etc.

##Usage

###Example usage of listing frontend volumes (list-volumes):

    >query-sc list-volumes
    Listing volumes from SC frontend (logical)
    PROPERTY        euca    Volume listing:
    Volume: vol-676B3DB2 Status: available Size: 1GB Created: 2013-06-11 16:30:28.954
    Volume: vol-E8A14484 Status: deleted Size: 3GB Created: 2013-06-11 15:59:03.474
    [vol-E8A14484]   Token: ****************************************************************************IPAg Is Valid: false Created: 2013-06-11 15:59:41.733
    [vol-E8A14484]          Export to Host: 10.111.1.56 IQN: iqn.1994-05.com.redhat:35cdac5bc2f Is Active: false Last Updated: 2013-06-11 16:02:12.512
    [vol-E8A14484]   Token: ****************************************************************************6tLi Is Valid: false Created: 2013-06-11 16:02:27.926
    [vol-E8A14484]          Export to Host: 10.111.1.56 IQN: iqn.1994-05.com.redhat:35cdac5bc2f Is Active: false Last Updated: 2013-06-11 16:04:04.962
    [vol-7B1A3F05]   Token: ****************************************************************************yq5B Is Valid: false Created: 2013-06-11 15:48:52.484
    [vol-7B1A3F05]          Export to Host: 10.111.1.56 IQN: iqn.1994-05.com.redhat:35cdac5bc2f Is Active: false Last Updated: 2013-06-11 15:50:47.418
    Volume: vol-9B9C44B3 Status: deleted Size: 3GB Created: 2013-06-11 15:10:39.196
    Volume: vol-8EAD3F4A Status: deleted Size: 1GB Created: 2013-06-11 16:25:12.903
    Volume: vol-D5C844EB Status: available Size: 1GB Created: 2013-06-11 16:33:49.851
    Volume: vol-17AE3C4C Status: available Size: 1GB Created: 2013-06-11 15:17:01.285
    [vol-17AE3C4C]   Token: ****************************************************************************SDpg Is Valid: false Created: 2013-06-11 15:17:21.78
    [vol-17AE3C4C]          Export to Host: 10.111.1.56 IQN: iqn.1994-05.com.redhat:35cdac5bc2f Is Active: false Last Updated: 2013-06-11 16:21:22.752
    [vol-17AE3C4C]   Token: ****************************************************************************oCrF Is Valid: false Created: 2013-06-13 09:06:29.743
    [vol-17AE3C4C]          Export to Host: 10.111.1.56 IQN: iqn.1994-05.com.redhat:35cdac5bc2f Is Active: false Last Updated: 2013-06-13 10:27:56.159
    Volume: vol-6A133E47 Status: deleted Size: 1GB Created: 2013-06-11 16:24:23.879
    [vol-6A133E47]   Token: ****************************************************************************AoGd Is Valid: false Created: 2013-06-11 16:24:34.86
    [vol-6A133E47]          Export to Host: 10.111.1.56 IQN: iqn.1994-05.com.redhat:35cdac5bc2f Is Active: false Last Updated: 2013-06-11 16:25:31.148
    Volume: vol-8F5C428E Status: available Size: 1GB Created: 2013-06-11 16:28:02.874 was executed successfully.


The output indicates the full history of all volumes. This includes all deleted volumes, so the output may be quite long. It is best to grep for the volumeId of any specific volume
you are interested in.

The 'token' field is a redacted version of the token used for export/unexport during the process of attach/detach operations. There may be multiple tokens and many exports per volume,
however, there should only ever be one 'Is Valid' token, and up to two 'Is Active' exports per volume. The only time more than one export may be active is during the process of VM
migration.

###Example usage of listing backend volumes on a TGT-based SC (list-backend-volumes);

    > ./query-sc list-backend-volumes
    Listing volumes from SC backend (SAN/TGT)
    PROPERTY        euca    Backend-TGT Volume listing:
    Volume: vol-676B3DB2  LVM LV Name:euca-vol-676B3DB2 LVM PV Name:null LVM VG Name:euca-ebs-storage-vg-vol-676B3DB2 Loopback Device:null Size:1 Status:available TGT Store Name:null TGT tid:-1 TGT lun:null TGT Chap User:null
    Volume: snap-31B641FC  LVM LV Name:null LVM PV Name:null LVM VG Name:null Loopback Device:null Size:1 Status:available TGT Store Name:null TGT tid:-1 TGT lun:null TGT Chap User:null
    Volume: vol-58D83B8B  LVM LV Name:euca-vol-58D83B8B LVM PV Name:null LVM VG Name:euca-ebs-storage-vg-vol-58D83B8B Loopback Device:null Size:1 Status:available TGT Store Name:null TGT tid:-1 TGT lun:-1 TGT Chap User:eucalyptus
    Volume: vol-909C3E67  LVM LV Name:euca-vol-909C3E67 LVM PV Name:null LVM VG Name:euca-ebs-storage-vg-vol-909C3E67 Loopback Device:null Size:1 Status:available TGT Store Name:null TGT tid:-1 TGT lun:-1 TGT Chap User:eucalyptus
    Volume: snap-5E2F40ED  LVM LV Name:null LVM PV Name:null LVM VG Name:null Loopback Device:null Size:3 Status:available TGT Store Name:null TGT tid:-1 TGT lun:null TGT Chap User:null
    Volume: snap-944D3EF0  LVM LV Name:null LVM PV Name:null LVM VG Name:null Loopback Device:null Size:1 Status:available TGT Store Name:null TGT tid:-1 TGT lun:null TGT Chap User:null
    Volume: vol-01E042B8  LVM LV Name:euca-vol-01E042B8 LVM PV Name:null LVM VG Name:euca-ebs-storage-vg-vol-01E042B8 Loopback Device:null Size:2 Status:available TGT Store Name:null TGT tid:-1 TGT lun:-1 TGT Chap User:eucalyptus
    Volume: vol-17AE3C4C  LVM LV Name:euca-vol-17AE3C4C LVM PV Name:null LVM VG Name:euca-ebs-storage-vg-vol-17AE3C4C Loopback Device:null Size:1 Status:available TGT Store Name:null TGT tid:-1 TGT lun:-1 TGT Chap User:eucalyptus
    Volume: vol-8F5C428E  LVM LV Name:euca-vol-8F5C428E LVM PV Name:null LVM VG Name:euca-ebs-storage-vg-vol-8F5C428E Loopback Device:null Size:1 Status:available TGT Store Name:null TGT tid:-1 TGT lun:null TGT Chap User:null
    Volume: snap-25E33DEA  LVM LV Name:null LVM PV Name:null LVM VG Name:null Loopback Device:null Size:1 Status:available TGT Store Name:null TGT tid:-1 TGT lun:null TGT Chap User:null
    Volume: vol-7A283E24  LVM LV Name:euca-vol-7A283E24 LVM PV Name:null LVM VG Name:euca-ebs-storage-vg-vol-7A283E24 Loopback Device:null Size:1 Status:available TGT Store Name:null TGT tid:-1 TGT lun:-1 TGT Chap User:eucalyptus
    Volume: vol-8BAF3DC5  LVM LV Name:euca-vol-8BAF3DC5 LVM PV Name:null LVM VG Name:euca-ebs-storage-vg-vol-8BAF3DC5 Loopback Device:null Size:3 Status:available TGT Store Name:null TGT tid:-1 TGT lun:-1 TGT Chap User:eucalyptus
    Volume: vol-D5C844EB  LVM LV Name:euca-vol-D5C844EB LVM PV Name:null LVM VG Name:euca-ebs-storage-vg-vol-D5C844EB Loopback Device:null Size:1 Status:available TGT Store Name:null TGT tid:-1 TGT lun:null TGT Chap User:null was executed successfully.

Because the example was run on a SC configured to use 'overlay' we see LVM information as well as TGT info in the backend listing. Note that snapshots are also listed in the backend listing. This is because for some backends volumes and snapshots are managed essentially the same way.


