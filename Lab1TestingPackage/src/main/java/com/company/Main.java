package com.company;

import com.company.entities.Packet;
import com.company.managers.PacketReceiver;

public class Main {
    public static void main(String args[]) {
        for (int i = 1; i < 10; i++) {
            Packet p = new Packet(i+2, 1, 2, "Hello World"+(i*i*i^i));
            System.out.println("Size of packet (in main): " + p.getData().length);
            PacketReceiver pr = new PacketReceiver(p.getData());
            pr.checkSums();
        }
    }
}

