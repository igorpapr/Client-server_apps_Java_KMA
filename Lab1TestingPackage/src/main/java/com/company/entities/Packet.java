package com.company.entities;

public class Packet {

    //OFFSETS ("o" stands for "offset")
//    private static final int oMagic = 0;
//    private static final int oSrc = 1;
//    private static final int oPktId = 2;
//    private static final int oLen = 10;
//    private static final int oCRC_0_13 = 14;
//    private static final int oMessage = 16;
//    private final int oCRC_16_end = 16;

    private static long pcktId;
    private byte[] data;

    /**
     * @param src - unique number of client, must be 1 byte (This is a task)
     * @param message - message to send
     */
    public Packet(final int src, final String message){
        try{
            if (src > 127)
                throw new IllegalArgumentException("Unique number of client must be less than 1 byte");
        }catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        int packetLength = 16;
        byte bMessage[] = message.getBytes();
        packetLength += bMessage.length;
        System.out.println(packetLength);
    }

    public static long getPcktId() {
        return pcktId;
    }
}
