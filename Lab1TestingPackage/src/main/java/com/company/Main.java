package com.company;

import com.company.entities.Packet;
import com.company.managers.PacketReceiver;

public class Main {
    public static void main(String args[]) {
        System.out.println("= = = = = = = = == = = = = = =");
        Packet p = new Packet(2, 1, 2, "Hello World3123");
        System.out.println("Size of packet (in main): " + p.getData().length);

        try {
            PacketReceiver pr = new PacketReceiver(p.getData());
            System.out.println(pr.checkSums());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

