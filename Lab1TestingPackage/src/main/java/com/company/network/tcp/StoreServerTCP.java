package com.company.network.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StoreServerTCP {

    static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(PORT);
        System.out.println("Server has been launched.");

        try {
            while (true) {
                Socket socket = s.accept();
                try {
                    new StoreServerTCPThread(socket);
                } catch (IOException e) {
                    //в разі невдачі закриваємо сокет
                    socket.close();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    socket.close();//TODO
                }
            }
        } finally {
            s.close();
        }
    }
}
