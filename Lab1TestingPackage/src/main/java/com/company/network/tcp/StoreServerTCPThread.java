package com.company.network.tcp;

import com.company.entities.Packet;
import com.company.managers.impl.ReceiverTCP;

import java.io.*;
import java.net.Socket;

public class StoreServerTCPThread extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream oin;


    public StoreServerTCPThread(Socket s) throws IOException, ClassNotFoundException {
        socket = s;
        oin = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());

        //Якщо будь-який з вище перерахованих викликів приведе до виникнення
        //виключення, тоді викликаючий буде відповідальний за закриття сокета
        //інакше потік закриє його
        this.start();//викликаємо run();
    }

    public void run() {
        try {
            byte[] packet;
            while (true) {
                try {
                    packet = (byte[]) oin.readObject();
                }catch (IOException e){
                    System.out.println("End of this thread's packet stream..........................");
                    break;
                }

                ReceiverTCP receiverTCP = new ReceiverTCP(packet,out);
                receiverTCP.receiveMessage();
            }
            System.out.println("Закриваємо сокет на сервері");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Сокет не закрито ...");
            }
        }
    }


}
