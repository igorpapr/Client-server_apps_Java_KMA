package com.company.network.udp;

import com.company.entities.Message;
import com.company.entities.Packet;
import com.company.utils.RandomPacketGenerator;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class StoreClientUDP {
    private static Message result;

    public static void main(String args[]) throws Exception
    {
        for (int i = 0; i < 5; i++) {
            //BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            //String sentence = inFromUser.readLine();

            DatagramSocket clientSocket = new DatagramSocket();

            InetAddress IPAddress = InetAddress.getByName("localhost");

            Packet p = RandomPacketGenerator.generate(false); //true in future

            byte[] receiveData = new byte[1024];
            byte[] sendData;

            sendData = p.getData();//sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, StoreServerUDP.PORT);

            while (true) {
                clientSocket.send(sendPacket);

                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);

                Message recievedMessage = new Message(receivePacket.getData());
                result = recievedMessage;
                if (recievedMessage.getcType() != -1) {
                    System.out.println("= = = = = = = = = = = =\nFROM SERVER:\ncType: " + recievedMessage.getcType() +
                            "\nbUserId: " + recievedMessage.getbUserId() + "\nMessage: " + recievedMessage.getJsonMessage()
                            .toString());
                    System.out.println("= = = = = = = = = = = =");
                    break;
                } else {
                    System.out.println("The packet was corrupted, sending it once more...");
                }
            }
            clientSocket.close();
        }
    }

    public static Message getResult() {
        return result;
    }
}
