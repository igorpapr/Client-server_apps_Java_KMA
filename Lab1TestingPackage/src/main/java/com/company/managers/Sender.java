package com.company.managers;

import java.net.InetAddress;

public class Sender {

    private InetAddress address;
    public Sender(){
        // * * * (in next lab)
        //fake address now
        address = InetAddress.getLoopbackAddress();
    }

    public void send(byte[] message, InetAddress target) {
        System.out.println("Everything is OK, message array length: " + message.length);
    }
}
