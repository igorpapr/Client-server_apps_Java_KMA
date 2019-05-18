package com.company;

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

    /*
    * src - unique number of client
    * message - message to send
    */
    public Packet(final int src, final String message){

        int packetLength = 16;


    }

    public static long getPcktId() {
        return pcktId;
    }
}
