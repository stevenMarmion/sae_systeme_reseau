package src_client_serveur;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class ClientServeur  {
    public static void main(String[] args) throws UnknownHostException, IOException {
        // get a datagram socket
        DatagramSocket socket = new DatagramSocket();
        // send request
        byte[] buf = new byte[256];
        InetAddress address = InetAddress.getByName("127.0.0.1");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);
        // get response
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        // display response
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Date: " + received);
        socket.close();

        DatagramSocket socketServ = new DatagramSocket(4445);
        byte[] bufServeur = new byte[256];

        // receive request
        DatagramPacket packetServ = new DatagramPacket(bufServeur, bufServeur.length);
        socketServ.receive(packet);

        // response
        String dString = new Date().toString();
        bufServeur = dString.getBytes();

        // send the response to the client at "address" and "port"
        InetAddress addressServ = packetServ.getAddress();
        int port = packetServ.getPort();
        packet = new DatagramPacket(bufServeur, bufServeur.length, addressServ, port);
        socketServ.send(packet);
        socketServ.close();
    }
}
