package org.jgroups.tests;


import java.net.*;
import java.util.Enumeration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

int maxPacketSize = 8192-5
def mcastAddr = { testCase ->
  [
    "224.10.10.${testCase}": 5550+testCase,
    "228.7.7.${testCase}": 8760+testCase,
  ]
}

sortedHosts = Ordering.usingToString( ).sortedCopy( Arrays.asList( args ) );

String localHost = sortedHosts.find{ host -> NetworkInterface.getByInetAddress( Inet4Address.getByName( host ) ) != null };
if ( localHost == null ) {
  System.err.println "ERROR: Failed to find the local host address from the given arguments: ${sortedHosts}"
  System.exit(1);
} 
List<String> remoteHosts = Ordering.usingToString( ).sortedCopy( Arrays.asList( args ) )
remoteHosts.remove( localHost )

def ipRoute = {
  p = "/sbin/ip route".execute();
  if ( p.waitFor( ) != 0 ) {
    System.err.println p.err
    System.exit(1);
  }
  p.text
}
List<String> ipRoutes = ipRoute().readLines( );
InetAddress localInetAddr = Inet4Address.getByName( localHost );
NetworkInterface iface = NetworkInterface.getByInetAddress( localInetAddr );
Integer ifaceMtu = iface.getMTU( );

def ifaceDefaultGateway = {
  String defaultGwIfaceName = ipRoutes.find{ it.startsWith( "default" ) }.tokenize().get(4)
  if ( defaultGwIfaceName == null ) {
    System.err.println "WARN: Failed to find the default gateway interface."
    false
  } else if ( iface.getDisplayName( ) != defaultGwIfaceName ) {
    System.err.println "WARN: Found default gateway interface which is not same as that of the bind address."
    false
  }
  true
}

println "Found local host address:      ${localHost} on ${iface.getDisplayName( )} mtu=${iface.getMTU( )} addrs=${iface.getInterfaceAddresses( ).collect{ InterfaceAddress iaddr -> ''+iaddr.getAddress( )+'/'+iaddr.getNetworkPrefixLength( ) }}  ${ifaceDefaultGateway()?'default':''}"
println "Found remote host addresses:   ${remoteHosts}"
ipRoutes.each {
  println "Found routing entries:         ${it}"
}

ConcurrentMap<Integer,Integer> sent = new ConcurrentHashMap( )
hostAcks = [:]
acks = [] as Set<Integer>

def ackReceiver = { MulticastSocket sock ->
  while(true) {
    try {
      ackbuf=new byte[8192];
      ackpacket=new DatagramPacket(ackbuf, ackbuf.length);
      sock.receive(ackpacket);
      String response = new String(ackpacket.getData());
      String index = response.split(':')[0].trim();
      hostAddr = ackpacket.getAddress().getHostAddress( );
      !hostAcks.containsKey( hostAddr ) ? hostAcks.put( hostAddr, [] as Set<Integer> ) : {} ;
      hostAcks.get( hostAddr ).add( Integer.parseInt( index ) );
      print ":"
      //          println "ACK:${ackpacket.getAddress()}:${ackpacket.getPort()}->${index}: ${response}"
    }
    catch(Exception e) {
      System.err.println(e);
    }
  }
}

def receiver = { addr, port ->
  InetAddress bind_addr=null, mcast_addr=null;
  byte[] recv_buf;
  String tmp;
  boolean receive_on_all_interfaces=false;
  mcast_addr=InetAddress.getByName(addr);
  MulticastSocket sock=new MulticastSocket(port);
  SocketAddress join_addr=new InetSocketAddress(mcast_addr, port);
  sock.joinGroup(join_addr, null);
  System.out.println("${new Date()} Socket=" + sock.getLocalAddress() + ':' + sock.getLocalPort() + ", group=${join_addr} bind interface=" + sock.getInterface());
  int length;
  while(true) {
    buf =new byte[8192];
    DatagramPacket packet=new DatagramPacket(buf, buf.length);
    sock.receive(packet);
    recv_buf=packet.getData();
    length=packet.getLength();
    String recv = new String(recv_buf, 0, length);
    String index = recv.split(':')[0];
//    print "[${packet.getAddress()}:${packet.getPort()}${join_addr}->${index} ${recv}] ";
    String response = "${index}:bai"
    byte[] resbuf = response.getBytes();
    DatagramPacket rsp=new DatagramPacket(resbuf, resbuf.length, packet.getAddress(), packet.getPort());
    sock.send(rsp);
//    println " ACK:${index}[${response}]"
  }
}
def sender = { addr, port, bindAddr ->
  byte[] buf=new byte[0];
  String tmp;
  int ttl=32;
  String line;
  
  sock=new MulticastSocket(new InetSocketAddress(bindAddr, port));
  sock.setTimeToLive(ttl);
  sock.setInterface(Inet4Address.getByName( bindAddr ));
  sock.setLoopbackMode( false )
  //?  group = new InetSocketAddress(addr, port);
  //  sock.joinGroup(group, null);
  println "${new Date()} ${sock.getLocalAddress()}:${sock.getLocalPort()}->${addr} ttl=${sock.getTimeToLive()} iface=${sock.getNetworkInterface().getDisplayName()}";
  Thread.start ackReceiver.curry(sock)
  100.times {
    packetSize = (maxPacketSize/100)*(100-it)
    line = sprintf( "%04d:", it );
    packetSize.times{ line += "${it}".substring(0,1) }
    it.times{ line += "${it}".substring(0,1) }
    //    println "${addr}:${port}->${it} "
    print "."
    packet=new DatagramPacket(line.getBytes(), line.getBytes().length, InetAddress.getByName(addr), port);
    sent.put( it, line.length( ) )
    sock?.send(packet);
    TimeUnit.MILLISECONDS.sleep( 10 );
  }
  print "\n"
  endTime = new Date();
  TimeUnit.MILLISECONDS.sleep( 1000 );
    hostAcks.each{ hostAddr, acks -> 
    lost = sent.keySet().findAll{ !acks.contains(it) }
    lostSizes = lost.collect{ sent.get(it) }
    println "${endTime} ${hostAddr} sent=${sent.size()} sizes=(${sent.values().min()},${sent.values().max()}) ackd=${acks.size()} lost-packets=[ count=${lost.size()} sizes=${lostSizes} ]"
//    int i = 0;
//    lost.each{ k ->
//      System.err.println "lost ${hostAddr} ${addr}:${port} ${k} @ ${new Date(sent.get(k))}"
//    }
    acks.clear()
  }
  sent.clear()
}

sortedHosts.eachWithIndex{ h, i ->
  if( remoteHosts.contains( h ) ) {
    mcastAddr(i+1).each { k, v ->
      Thread.start receiver.curry(k,v)
    }
  } else {
    Thread.start {
      sleep(1000)
      print "Sleeping for 5 seconds:  "
      5.times{ sleep( 1000 ); print "${5-it} "; }
      println " GO!"
      10.times {
        mcastAddr(i+1).each { k, v ->
          sender(k,v,h)
        }
      }
    }
  }
}

