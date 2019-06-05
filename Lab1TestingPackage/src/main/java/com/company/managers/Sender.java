package com.company.managers;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static com.company.network.udp.StoreServerUDP.serverSocket;

public class Sender {

    static BlockingQueue<byte[]> bq = new ArrayBlockingQueue<>(10);

    public Sender(byte[] mess) throws InterruptedException {
        bq.put(mess);
        // * * * (in next lab)
        //fake address now
    }

    public void send(InetAddress target, int port) throws InterruptedException {
        byte[] mess = bq.take();
        DatagramPacket sendPacket =
                        new DatagramPacket(mess, mess.length, target, port);
                try{
                    serverSocket.send(sendPacket);
                }catch (Exception e){
                    e.printStackTrace();
                }
        System.out.println("Everything is OK, message array length: " + mess.length);
    }

    //TCP
    public void send(ObjectOutputStream out) throws InterruptedException, IOException {
        out.writeObject(bq.take());
        out.flush();
    }
}
