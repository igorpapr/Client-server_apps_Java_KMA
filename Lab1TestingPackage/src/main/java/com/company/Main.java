package com.company;

import com.company.entities.Packet;

public class Main {
    public static void main(String args[]) {
        for (int i = 0; i < 10; i++) {
            Packet p = new Packet(i, "My new message!3 "+ i);
        }
    }
}

