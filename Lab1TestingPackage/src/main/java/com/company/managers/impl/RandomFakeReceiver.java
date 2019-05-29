package com.company.managers.impl;

import com.company.entities.Packet;
import com.company.managers.IReceiver;
import com.company.utils.Decriptor;
import com.company.utils.ProtocolInfo;

import java.util.Arrays;

public class RandomFakeReceiver implements IReceiver {

    @Override
    public void receiveMessage() {
        int command;
        switch ((int)(Math.random() * 6)) {
            case 0:
                command = ProtocolInfo.C_GET_AMOUNT;
                break;
            case 1:
                command = ProtocolInfo.C_DEL_GOODS;
                break;
            case 2:
                command = ProtocolInfo.C_ADD_GOODS;
                break;
            case 3:
                command = ProtocolInfo.C_ADD_GROUP;
                break;
            case 4:
                command = ProtocolInfo.C_ADD_GROUP_TITLE;
                break;
            default:
                command = ProtocolInfo.C_SET_PRICE;
                break;
        }
        Packet p = new Packet((int)(Math.random() * 100), command, (int)(Math.random() * 10),Integer.toString((int)(10 + Math.random() * 40)));
        System.out.println("= = = = = = = = = =\nGenerated src: " + p.getSrc());
        System.out.println("Generated command: " + command);
        System.out.println("Generated bUserId: " + p.getMessage().getbUserId());
        System.out.println("Generated message: " + p.getMessage().getJsonMessage().toString());
        System.out.println("= = = = = = = = = =");
        new Thread(){
            @Override
            public void run(){
                System.out.println("Start of " + this.getName());
                Decriptor.getInstance().decrypt(Arrays.copyOfRange(p.getData(), ProtocolInfo.O_MESSAGE,
                        ProtocolInfo.O_MESSAGE + p.getMessageLength()));
            }
        }.start();
    }
}
