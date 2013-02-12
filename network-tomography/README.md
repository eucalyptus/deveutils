Network tomography tool that uses udp unicast and multicast to determine reachability and reliability for a range of packet sizes across a set of hosts.
./network-tomography 192.168.51.174 192.168.51.196 192.168.51.86 192.168.51.99
No dependencies.

# Sample output
From one of the hosts in the above indicated example:
```bash
[root@eucahost-51-86 mcast-test]# ./network-tomography 192.168.51.174 192.168.51.196 192.168.51.86 192.168.51.99
# Network information
Found local host address:      192.168.51.86 on eth0 mtu=1500 addrs=[/192.168.51.86/18]  default
Found remote host addresses:   [192.168.51.174, 192.168.51.196, 192.168.51.99]
Found routing entries:         192.168.122.0/24 dev virbr0  proto kernel  scope link  src 192.168.122.1 
Found routing entries:         192.168.0.0/18 dev eth0  proto kernel  scope link  src 192.168.51.86 
Found routing entries:         169.254.0.0/16 dev eth0  scope link  metric 1002 
Found routing entries:         default via 192.168.7.1 dev eth0 
# Multicast receiver information
Wed Feb 13 11:35:13 PST 2013 Socket=0.0.0.0/0.0.0.0:5551, group=/224.10.10.1:5551 bind interface=/0.0.0.0
Wed Feb 13 11:35:13 PST 2013 Socket=0.0.0.0/0.0.0.0:8762, group=/228.7.7.2:8762 bind interface=/0.0.0.0
Wed Feb 13 11:35:13 PST 2013 Socket=0.0.0.0/0.0.0.0:8761, group=/228.7.7.1:8761 bind interface=/0.0.0.0
Wed Feb 13 11:35:13 PST 2013 Socket=0.0.0.0/0.0.0.0:5552, group=/224.10.10.2:5552 bind interface=/0.0.0.0
Wed Feb 13 11:35:13 PST 2013 Socket=0.0.0.0/0.0.0.0:8764, group=/228.7.7.4:8764 bind interface=/0.0.0.0
Wed Feb 13 11:35:13 PST 2013 Socket=0.0.0.0/0.0.0.0:5554, group=/224.10.10.4:5554 bind interface=/0.0.0.0
# Chance to get all the hosts ready
Sleeping for 5 seconds:  5 4 3 2 1  GO!
# First iteration:  
# - a "." is a multicast send by the local host
# - a ":" is a received unicast udp from some other group member
# Packet sizes vary from largest to smallest across the test.
Wed Feb 13 11:35:19 PST 2013 /192.168.51.86:5553->224.10.10.3 ttl=32 iface=eth0
.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::
# First iteration complete:
# - List of hosts who responded
# - Number and Range of packet sizes sent to multicast group
# - Number of packets acknowledged using unicast udp of the exact same payload
# - Lost packet count and sizes on a per host basis; 
# - Lost packets dont distinguish between multicast delivery failure and udp unicast acknowledgement failure.
Wed Feb 13 11:35:22 PST 2013 192.168.51.99 sent=100 sizes=(185,8192) ackd=100 lost-packets=[ count=0 sizes=[] ]
Wed Feb 13 11:35:22 PST 2013 192.168.51.174 sent=100 sizes=(185,8192) ackd=100 lost-packets=[ count=0 sizes=[] ]
Wed Feb 13 11:35:22 PST 2013 192.168.51.196 sent=100 sizes=(185,8192) ackd=100 lost-packets=[ count=0 sizes=[] ]
Wed Feb 13 11:35:23 PST 2013 /192.168.51.86:8763->228.7.7.3 ttl=32 iface=eth0
# Second iteration: using a different multicast group
.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::
Wed Feb 13 11:35:27 PST 2013 192.168.51.99 sent=100 sizes=(185,8192) ackd=100 lost-packets=[ count=0 sizes=[] ]
Wed Feb 13 11:35:27 PST 2013 192.168.51.174 sent=100 sizes=(185,8192) ackd=100 lost-packets=[ count=0 sizes=[] ]
Wed Feb 13 11:35:27 PST 2013 192.168.51.196 sent=100 sizes=(185,8192) ackd=100 lost-packets=[ count=0 sizes=[] ]
Wed Feb 13 11:35:28 PST 2013 /192.168.51.86:5553->224.10.10.3 ttl=32 iface=eth0
```

# Unobstructed Sample Output
```bash
[root@eucahost-51-86 mcast-test]# ./network-tomography 192.168.51.174 192.168.51.196 192.168.51.86 192.168.51.99
Found local host address:      192.168.51.86 on eth0 mtu=1500 addrs=[/192.168.51.86/18]  default
Found remote host addresses:   [192.168.51.174, 192.168.51.196, 192.168.51.99]
Found routing entries:         192.168.122.0/24 dev virbr0  proto kernel  scope link  src 192.168.122.1 
Found routing entries:         192.168.0.0/18 dev eth0  proto kernel  scope link  src 192.168.51.86 
Found routing entries:         169.254.0.0/16 dev eth0  scope link  metric 1002 
Found routing entries:         default via 192.168.7.1 dev eth0 
Wed Feb 13 11:35:13 PST 2013 Socket=0.0.0.0/0.0.0.0:5551, group=/224.10.10.1:5551 bind interface=/0.0.0.0
Wed Feb 13 11:35:13 PST 2013 Socket=0.0.0.0/0.0.0.0:8762, group=/228.7.7.2:8762 bind interface=/0.0.0.0
Wed Feb 13 11:35:13 PST 2013 Socket=0.0.0.0/0.0.0.0:8761, group=/228.7.7.1:8761 bind interface=/0.0.0.0
Wed Feb 13 11:35:13 PST 2013 Socket=0.0.0.0/0.0.0.0:5552, group=/224.10.10.2:5552 bind interface=/0.0.0.0
Wed Feb 13 11:35:13 PST 2013 Socket=0.0.0.0/0.0.0.0:8764, group=/228.7.7.4:8764 bind interface=/0.0.0.0
Wed Feb 13 11:35:13 PST 2013 Socket=0.0.0.0/0.0.0.0:5554, group=/224.10.10.4:5554 bind interface=/0.0.0.0
Sleeping for 5 seconds:  5 4 3 2 1  GO!
Wed Feb 13 11:35:19 PST 2013 /192.168.51.86:5553->224.10.10.3 ttl=32 iface=eth0
.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::
Wed Feb 13 11:35:22 PST 2013 192.168.51.99 sent=100 sizes=(185,8192) ackd=100 lost-packets=[ count=0 sizes=[] ]
Wed Feb 13 11:35:22 PST 2013 192.168.51.174 sent=100 sizes=(185,8192) ackd=100 lost-packets=[ count=0 sizes=[] ]
Wed Feb 13 11:35:22 PST 2013 192.168.51.196 sent=100 sizes=(185,8192) ackd=100 lost-packets=[ count=0 sizes=[] ]
Wed Feb 13 11:35:23 PST 2013 /192.168.51.86:8763->228.7.7.3 ttl=32 iface=eth0
.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::.:::
Wed Feb 13 11:35:27 PST 2013 192.168.51.99 sent=100 sizes=(185,8192) ackd=100 lost-packets=[ count=0 sizes=[] ]
Wed Feb 13 11:35:27 PST 2013 192.168.51.174 sent=100 sizes=(185,8192) ackd=100 lost-packets=[ count=0 sizes=[] ]
Wed Feb 13 11:35:27 PST 2013 192.168.51.196 sent=100 sizes=(185,8192) ackd=100 lost-packets=[ count=0 sizes=[] ]
Wed Feb 13 11:35:28 PST 2013 /192.168.51.86:5553->224.10.10.3 ttl=32 iface=eth0
```