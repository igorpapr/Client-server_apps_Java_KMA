package com.company.managers.impl;

import com.company.entities.Packet;
import com.company.managers.IReceiver;
import com.company.utils.Decriptor;
import com.company.utils.ProtocolInfo;
import com.company.utils.RandomPacketGenerator;

import java.util.Arrays;

public class RandomFakeReceiver implements IReceiver {

    @Override
    public void receiveMessage() {
        Packet p = RandomPacketGenerator.generate();
        new Thread(){
            @Override
            public void run(){
                System.out.println("Start of " + this.getName());
                Decriptor.getInstance().decryptAndProcess(Arrays.copyOfRange(p.getData(), ProtocolInfo.O_MESSAGE,
                        ProtocolInfo.O_MESSAGE + p.getMessageLength()));
            }
        }.start();
    }
}
