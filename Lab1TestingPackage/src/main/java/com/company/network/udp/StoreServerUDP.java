package com.company.network.udp;

import com.company.managers.impl.ReceiverUDP;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class StoreServerUDP {

    static final int PORT = 8081;
    public static DatagramSocket serverSocket;
    public static BlockingQueue<DatagramPacket> queue;

    public static void main(String args[]) throws IOException
    {
        try {
            serverSocket = new DatagramSocket(PORT);
            queue = new ArrayBlockingQueue<>(20);
        }catch (Exception e){
            e.printStackTrace();
        }

        byte[] receiveData = new byte[1024];
        byte[] sendData;

        while(true)
        {
            String sentence = "Received packet {UDP}";
            ReceiverUDP receiver = null;

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            try {
                receiver = new ReceiverUDP(receivePacket);
                receiver.receiveMessage();
                sentence += ": pcktID - " + receiver.getPcktId();
                queue.put(receivePacket);
///////
            //send back
                // System.out.println(sentence);
                // InetAddress IPAddress = receivePacket.getAddress();
                // int port = receivePacket.getPort();
                // String capitalizedSentence = sentence.toUpperCase();
                // sendData = capitalizedSentence.getBytes();
                //DatagramPacket sendPacket =
                //        new DatagramPacket(sendData, sendData.length, IPAddress, port);
                //try{
                //    serverSocket.send(sendPacket);
                //}catch (Exception e){
                //    e.printStackTrace();
                //}
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static DatagramSocket getServerSocket() {
        return serverSocket;
    }
}
