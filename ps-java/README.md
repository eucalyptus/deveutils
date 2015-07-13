# ps-java
Produces output similar to 'ps', especially cputime for the LWP corresponding to the thread from a running JVM along with the internal com.sum.jvm.JavaThread's name.
## Do it!
- ```[root@g-15-11 ps-java]# yum install pstack kernel-devel```
- ```[root@g-15-11 ps-java]# bash <(wget -O- -q https://github.com/eucalyptus/deveutils/raw/ps-java/ps-java/ps-java)```

# Example output
```bash
[root@g-15-11 ps-java]# ./ps-java
Storing 27141 info to /tmp/euca-ps-ZB9k/euca-ps-ZB9k-20150712-2032.41
PSR RUSER      PID   LWP  PPID COMMAND          STARTED     TIME %CPU %MEM   RSS    VSZ    JTHREAD
   2 500      27141 27154 27140 eucalyptus-clou 19:07:28 00:00:57  1.1 16.3 1189436 6292908 "C2 CompilerThread0" daemon
   3 500      27141 27155 27140 eucalyptus-clou 19:07:28 00:00:53  1.0 16.3 1189436 6292908 "C2 CompilerThread1" daemon
   3 500      27141 27195 27140 eucalyptus-clou 19:07:46 00:00:24  0.4 16.3 1189436 6292908 "Eucalyptus.bootstrap:EphemeralConfiguration:arn:euca:bootstrap:Topology::external/.class java.util.concurrent.ThreadPoolExecutor$Worker#18"
   3 500      27141 27190 27140 eucalyptus-clou 19:07:46 00:00:23  0.4 16.3 1189436 6292908 "Eucalyptus.bootstrap:EphemeralConfiguration:arn:euca:bootstrap:Topology::external/.class java.util.concurrent.ThreadPoolExecutor$Worker#13"
   3 500      27141 27184 27140 eucalyptus-clou 19:07:46 00:00:23  0.4 16.3 1189436 6292908 "Eucalyptus.bootstrap:EphemeralConfiguration:arn:euca:bootstrap:Topology::external/.class java.util.concurrent.ThreadPoolExecutor$Worker#7"
   3 500      27141 27181 27140 eucalyptus-clou 19:07:46 00:00:24  0.4 16.3 1189436 6292908 "Eucalyptus.bootstrap:EphemeralConfiguration:arn:euca:bootstrap:Topology::external/.class java.util.concurrent.ThreadPoolExecutor$Worker#4"
   2 500      27141 27203 27140 eucalyptus-clou 19:07:46 00:00:24  0.4 16.3 1189436 6292908 "Eucalyptus.bootstrap:EphemeralConfiguration:arn:euca:bootstrap:Topology::external/.class java.util.concurrent.ThreadPoolExecutor$Worker#26"
   2 500      27141 27201 27140 eucalyptus-clou 19:07:46 00:00:23  0.4 16.3 1189436 6292908 "Eucalyptus.bootstrap:EphemeralConfiguration:arn:euca:bootstrap:Topology::external/.class java.util.concurrent.ThreadPoolExecutor$Worker#24"
   1 500      27141 27208 27140 eucalyptus-clou 19:07:46 00:00:24  0.4 16.3 1189436 6292908 "Eucalyptus.bootstrap:EphemeralConfiguration:arn:euca:bootstrap:Topology::external/.class java.util.concurrent.ThreadPoolExecutor$Worker#31"
   1 500      27141 27202 27140 eucalyptus-clou 19:07:46 00:00:24  0.4 16.3 1189436 6292908 "Eucalyptus.bootstrap:EphemeralConfiguration:arn:euca:bootstrap:Topology::external/.class java.util.concurrent.ThreadPoolExecutor$Worker#25"
   3 500      27141 27145 27140 eucalyptus-clou 19:07:28 00:00:14  0.2 16.3 1189436 6292908 "Gang worker#3 (Parallel GC Threads)"
   3 500      27141 27144 27140 eucalyptus-clou 19:07:28 00:00:14  0.2 16.3 1189436 6292908 "Gang worker#2 (Parallel GC Threads)"
   3 500      27141 11427 27140 eucalyptus-clou 20:32:11 00:00:00  0.2 16.3 1189436 6292908 "Eucalyptus.bootstrap:ListenerRegistry:listenerTasks.class java.util.concurrent.ThreadPoolExecutor$Worker#733"
   3 500      27141 11411 27140 eucalyptus-clou 20:32:01 00:00:00  0.2 16.3 1189436 6292908 "Eucalyptus.bootstrap:ListenerRegistry:listenerTasks.class java.util.concurrent.ThreadPoolExecutor$Worker#732"
   3 500      27141 11373 27140 eucalyptus-clou 20:31:03 00:00:00  0.2 16.3 1189436 6292908 "Eucalyptus.bootstrap:Callbacks:class com.eucalyptus.util.async.Callbacks$BasicCallbackProcessor.class java.util.concurrent.ThreadPoolExecutor$Worker#730"
   2 500      27141 27142 27140 eucalyptus-clou 19:07:28 00:00:14  0.2 16.3 1189436 6292908 "Gang worker#0 (Parallel GC Threads)"
   1 500      27141 27147 27140 eucalyptus-clou 19:07:28 00:00:12  0.2 16.3 1189436 6292908 "VM Thread"
   0 500      27141 27143 27140 eucalyptus-clou 19:07:28 00:00:14  0.2 16.3 1189436 6292908 "Gang worker#1 (Parallel GC Threads)"
   3 500      27141 27932 27140 eucalyptus-clou 19:08:31 00:00:08  0.1 16.3 1189436 6292908 "Eucalyptus.bootstrap:EphemeralConfiguration:arn:euca:bootstrap:Topology::internal/.class java.util.concurrent.ThreadPoolExecutor$Worker#137"
   3 500      27141 27809 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #298"
   3 500      27141 27800 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #293"
   3 500      27141 27798 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #292"
   3 500      27141 27795 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #289"
   3 500      27141 27784 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #278"
   3 500      27141 27783 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #277"
   3 500      27141 27770 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #264"
   3 500      27141 27766 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #260"
   3 500      27141 27283 27140 eucalyptus-clou 19:07:48 00:00:05  0.1 16.3 1189436 6292908 "Eucalyptus.bootstrap:Hosts:normal-pool.org.jgroups.protocols.UDP$PacketReceiver#37#multicast receiver"
   2 500      27141 27792 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #286"
   2 500      27141 27771 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #265"
   2 500      27141 27767 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #261"
   2 500      27141 27377 27140 eucalyptus-clou 19:07:58 00:00:07  0.1 16.3 1189436 6292908 "HouseKeeper" daemon
   2 500      27141 11401 27140 eucalyptus-clou 20:31:41 00:00:00  0.1 16.3 1189436 6292908 "Eucalyptus.bootstrap:ListenerRegistry:listenerTasks.class java.util.concurrent.ThreadPoolExecutor$Worker#731"
   2 500      27141 11300 27140 eucalyptus-clou 20:28:51 00:00:00  0.1 16.3 1189436 6292908 "pool-8-thread-76"
   1 500      27141 27808 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #297"
   1 500      27141 27805 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #296"
   1 500      27141 27802 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #295"
   1 500      27141 27801 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #294"
   1 500      27141 27797 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #291"
   1 500      27141 27793 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #287"
   1 500      27141 27790 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #284"
   1 500      27141 27788 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #282"
   1 500      27141 27787 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #281"
   1 500      27141 27785 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #279"
   1 500      27141 27782 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #276"
   1 500      27141 27781 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #275"
   1 500      27141 27780 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #274"
   1 500      27141 27779 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #273"
   1 500      27141 27778 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #272"
   1 500      27141 27777 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #271"
   1 500      27141 27775 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #269"
   1 500      27141 27774 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #268"
   1 500      27141 27773 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #267"
   1 500      27141 27772 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #266"
   1 500      27141 11339 27140 eucalyptus-clou 20:30:02 00:00:00  0.1 16.3 1189436 6292908 "Eucalyptus.bootstrap:Callbacks:class com.eucalyptus.util.async.Callbacks$BasicCallbackProcessor.class java.util.concurrent.ThreadPoolExecutor$Worker#728"
   1 500      27141 11200 27140 eucalyptus-clou 20:26:41 00:00:00  0.1 16.3 1189436 6292908 "Eucalyptus.bootstrap:ListenerRegistry:listenerTasks.class java.util.concurrent.ThreadPoolExecutor$Worker#722"
   1 500      27141 11128 27140 eucalyptus-clou 20:24:43 00:00:00  0.1 16.3 1189436 6292908 "Eucalyptus.bootstrap:Futures.class java.util.concurrent.ThreadPoolExecutor$Worker#719"
   0 500      27141 27796 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #290"
   0 500      27141 27794 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #288"
   0 500      27141 27791 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #285"
   0 500      27141 27789 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #283"
   0 500      27141 27786 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #280"
   0 500      27141 27776 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #270"
   0 500      27141 27769 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #263"
   0 500      27141 27768 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #262"
   0 500      27141 27765 27140 eucalyptus-clou 19:08:21 00:00:06  0.1 16.3 1189436 6292908 "New I/O worker #259"
   0 500      27141 27149 27140 eucalyptus-clou 19:07:28 00:00:10  0.1 16.3 1189436 6292908 "Finalizer" daemon
   0 500      27141 11302 27140 eucalyptus-clou 20:28:51 00:00:00  0.1 16.3 1189436 6292908 "pool-8-thread-74"

```
