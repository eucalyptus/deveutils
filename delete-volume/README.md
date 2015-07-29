Marks a given volume in the SC database as "deleting". SC then performs the job of cleaning up and deletes 
the given volume. This is useful in scenarios where the volume does not exists on the CLC but is still 
visible on the SC database. This utility should be run after the unexport-volume utility.

This utility should be used with **EXTREME** caution as it changes the state of Eucalyptus and may result
in corruption of a volume if run while a volume is attached and being used for IO by a VM.

