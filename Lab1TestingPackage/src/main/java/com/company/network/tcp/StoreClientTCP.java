package com.company.network.tcp;

import java.io.IOException;
import java.net.InetAddress;

public class StoreClientTCP {
    static final int MAX_THREADS = 5;

    public static void main(String[] args) throws IOException, InterruptedException {
        InetAddress addr = InetAddress.getByName(null);
        while (true) {
            if (StoreClientTCPThread.threadCount() < MAX_THREADS)
                new StoreClientTCPThread(addr);
            Thread.sleep(100);
        }
    }

}
