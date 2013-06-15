Queries Walrus DB entities directly to get info not available from the API endpoint. Resolves files on the filesystem to buckets and objects.

Requirements to run:
$EUCALYPTUS must be set to the root directory of the eucalyptus installation on the local host. For a package install, that is usually '/'. For a
source install it is whatever --with-prefix was set to during ./configure time of the source build.

The Eucalyptus SC must be running on the local host.
You must have admin credentials for the Eucalyptus install and have EC2_URL set to the local host. The easiest way to accomplish this is to source the eucarc file
on the local host (SC) after modifying the EC2_URL line to refer to the current local IP instead of the CLC.


usage: query-walrus <sub-command> <options>

Sub-commands:
lookup-object-from-file <file path from $EUCALYPTUS/var/lib/eucalyptus/bukkits> : given the bucket/filename it returns the object key and bucket as well as object info for the file
lookup-file-from-object <bucket/object key> : given the bucket/object-key it displays where that object is stored on the filesystem relative to $EUCALYPTUS/var/lib/eucalyptus/bukkits
lookup-snapshot-from-file <bucket/snapshot_file_path> : returns the snapshotId and info that correspond to the file on the filesystem 
lookup-file-from-snapshot <snap-Id> : returns the bucket and file that represent the given snapshot on Walrus
list-bucket <bucket> : lists all objects in the bucket including object keys and the files that correspond to those keys
list-cached-images : lists all images currently in the Walrus image cache as well as the files that correspond to the constructed and cached images

For all sub-commands that take a file as input, use the path relative to the 'bukkits' directory.
For all sub-commands that take an object key, include the bucket/object-key path

##Examples:

###Example lookup-object-from-file:

    > ./query-walrus lookup-object-from-file newbuck1370993982/856f64e9-e4ff-4a43-b844-7dd3f26241ce
    PROPERTY        euca    File -> object lookup result: 
    Object Key: testfile File Path: images/856f64e9-e4ff-4a43-b844-7dd3f26241ce Version: null Size: 2097152 Last Modified: 2013-06-11 16:39:43.744 Etag: b2d1236c286a3c0704224fe4105eca49 was executed successfully.


###Example lookup-file-from-object:

    > ./query-walrus lookup-file-from-object newbuck1370993982/testfile
    PROPERTY        euca    Object -> File lookup result: 
    Object: testfile File Path: newbuck1370993982/856f64e9-e4ff-4a43-b844-7dd3f26241ce Version: null Size: 2097152 Last Modified: 2013-06-11 16:39:43.744 Etag: b2d1236c286a3c0704224fe4105eca49 was executed successfully.


###Example lookup-snapshot-from-file:

    > ./query-walrus lookup-snapshot-from-file snapset-d0dcb274-ad9e-4658-95fe-ce12fee0b437/d3887cf8-c078-49e7-be6a-7bbfe9cb3ab5 
    PROPERTY        euca    Snapshot File -> Snapshot lookup result: 
    Snapshot Id: snap-25E33DEA Snapste/File Path: snapset-d0dcb274-ad9e-4658-95fe-ce12fee0b437/d3887cf8-c078-49e7-be6a-7bbfe9cb3ab5 Size: 5636150 was executed successfully.


###Example lookup-file-from-snap:

    > ./query-walrus lookup-file-from-snapshot snap-25E33DEA
    PROPERTY        euca    SnapshotId -> File lookup result: 
    Snapshot object key: snap-25E33DEA Bucket directory: snapset-d0dcb274-ad9e-4658-95fe-ce12fee0b437 Snapshot Size: 1 was executed successfully.


###Example list-bucket:

    > ./query-walrus list-bucket newbuck1370993982
    PROPERTY        euca    Bucket listing result: 
    Object Key: testfile File Path: newbuck1370993982/856f64e9-e4ff-4a43-b844-7dd3f26241ce Version: null Size: 2097152 Last Modified: 2013-06-11 16:39:43.744 Etag: b2d1236c286a3c0704224fe4105eca49 was executed successfully.


###Example list-cached-images:

    > ./query-walrus list-cached-images
    PROPERTY        euca    Image ID -> Cached file lookup result: 
    Bucket: images Image_Manifest: vmlinuz-2.6.32-279.14.1.el6.x86_64.manifest.xml Image_File: c0bdd6d7-b6dc-458b-b373-aba282aa1952 Size: 3988752
    Bucket: images Image_Manifest: initrd-2.6.32-279.14.1.el6.x86_64.img.manifest.xml Image_File: 1dea54b7-5c00-4c48-84ce-4671dee2f123 Size: 5943048
    Bucket: images Image_Manifest: centos-6.3-x86_64.img.manifest.xml Image_File: afa4c728-63f0-45db-8f40-94d18dd2e757 Size: 4781506560 was executed successfully.


