package com.company.entities;

import com.company.ProtocolInfo;

public class Packet {

    private static long pcktId;
    private byte[] data;


    /**
     * @param src - unique number of client, must be 1 byte (This is a task)
     * @param message - message to send
     */
    public Packet(final int src, final String message){
        try{
            if (src > 255)
                throw new IllegalArgumentException("Unique number of client must be less than 1 byte");
            int packetLength = 18;
            byte bMessage[] = message.getBytes();
            packetLength += bMessage.length;

            data = new byte[packetLength];
            fillData(bMessage, src, packetLength);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

    }

    private void fillData(final byte bMess[], final int src, final int length) {

    }

    public static long getPcktId() {
        return pcktId;
    }
    public byte[] getData() {
        return data;
    }
}
