package com.company;

import com.company.entities.Packet;
import com.company.managers.PacketReceiver;

public class Main {

    public static void main(String[] args)
    {
        Packet p = new Packet(1,1,1,"Привіт світ");
        PacketReceiver pr = new PacketReceiver(p.getData());
        System.out.println(pr.getMessageContent());

    }
}
