package com.company.utils;

import com.company.entities.Packet;

public class RandomPacketGenerator {

    public static Packet generate(){
        return generate(true);
    }

    public static Packet generate(boolean toCipher){
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
        Packet p = null;
        try {
            p = new Packet((int)(Math.random() * 100), command, (int)(Math.random() * 10), Integer.toString((int)(10 +
                    Math.random() * 40)), toCipher);
            System.out.println("= = = = = = = = = =\nGenerated src: " + p.getSrc());
            System.out.println("Generated command: " + command);
            System.out.println("Generated bUserId: " + p.getMessage().getbUserId());
            System.out.println("Generated message: " + p.getMessage().getJsonMessage().toString());
            System.out.println("= = = = = = = = = =");
        }catch (Exception e){
            e.printStackTrace();
        }
        return p;
    }
}
