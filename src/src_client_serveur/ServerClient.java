package src_client_serveur;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(5555);

        byte[] buf = new byte[512];
        // receive request
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet); // méthode bloquante, tant que nous recevons pas, nous attendons
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println(received);


        // response
        // String dString = new Date().toString();
        String dString = "Nous avons reçu ta connexion";
        buf = dString.getBytes();


        // send the response to the client at "address" and "port"
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        packet = new DatagramPacket(buf, buf.length, address, port);
        socket.send(packet);


        socket.close();
    }
}
