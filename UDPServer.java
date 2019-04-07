import java.io.*;
import java.net.*;
public class UDPServer
{
   private static final int PORT=1234;
private static DatagramSocket 
 datagramSocket;
private static DatagramPacket
 inPacket,outPacket;
private static byte[] buffer;
 public static void main(String[] args)
 {
    System.out.println("opening port \n");
try
{
  datagramSocket=new 
 DatagramSocket(PORT);  
}
catch(SocketException sockEx)
{
  System.out.println("fail to open ");
System.exit(1);   
}
handleClient();
 }
 private static void handleClient()
 {
     try
     {
     String messageIn,messageOut;
int numMessages=0;
InetAddress clientAddress=null;
int clientPort;
do    
{
   buffer= new byte[256];
   inPacket=new DatagramPacket(buffer,buffer.length);
   datagramSocket.receive(inPacket);
   clientAddress=inPacket.getAddress();
clientPort=inPacket.getPort();
messageIn=new String(inPacket.getData(),0,inPacket.getLength());
System.out.print(clientAddress);
System.out.print(" : ");
System.out.println(messageIn);
numMessages++;
messageOut="message" + numMessages + ": " + messageIn;
outPacket=new DatagramPacket(messageOut.getBytes(),messageOut.length(),clientAddress,clientPort);
datagramSocket.send(outPacket); 
}while(true);
     }
     catch(IOException ioEx)
{
}
finally
     {
     System.out.println("\n Closing connection.. ");
datagramSocket.close();     
     }
    
}
         
  } 

