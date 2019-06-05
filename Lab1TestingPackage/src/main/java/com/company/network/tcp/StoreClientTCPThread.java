package com.company.network.tcp;

import com.company.entities.Message;
import com.company.entities.Packet;
import com.company.utils.RandomPacketGenerator;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;

public class StoreClientTCPThread extends Thread{

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private static int counter = 0;
    private int id = counter++;
    private static int threadcount = 0;

    public StoreClientTCPThread(InetAddress addr) {
        System.out.println("Запустимо клієнт з номером " + id);
        threadcount++;
        try {
            socket = new Socket(addr, StoreServerTCP.PORT);
        }
        catch (IOException e) {
            System.err.println("Не вдалося з'єднатися з сервером");
            // Якщо не вдалося створити сокер нічого
            // не потрібно чистити
        }
        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
            this.start();
        }
        catch (IOException e) {
            // Сокет має бути закритий при будь якій помилці
            // крім помилки конструктора сокета
            try {
                socket.close();
            }
            catch (IOException e2) {
                System.err.println("Сокет не закрито");
            }
        }
        // Якщо все відбудеться нормально сокет буде закрито
        // в методі run() потоку.
    }

    public static int threadCount() {
        return threadcount;
    }

    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Packet p = RandomPacketGenerator.generate(false); //true in future
                oos.writeObject(p.getData());
                oos.flush();

                byte[] response = (byte[]) ois.readObject();
                Message recievedMessage = new Message(response);
                System.out.println("= = = = = = = = = = = =\nFROM SERVER:\ncType: "+recievedMessage.getcType() +
                        "\nbUserId: " + recievedMessage.getbUserId() + "\nMessage: " + recievedMessage.getJsonMessage()
                        .toString());
                //get
                //String str = in.readLine();
                //System.out.println(str+ " час отримання: "+new Date().getTime());
            }
        }
        catch (IOException e) {
            System.err.println("IO Exception");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Завжди закриває:
            try {
                socket.close();
                //ois.close();
                oos.close();
            }
            catch (IOException e) {
                System.err.println("Socket not closed");
            }
            //threadcount--; // Завершуємо цей потік
        }
    }
}
