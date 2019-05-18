package com.company;

public class ProtocolInfo {

    //OFFSETS ("o" stands for "offset")
    public static final int O_MAGIC = 0;
    public static final int O_SRC = 1;
    public static final int O_PKTID = 2;
    public static final int O_LEN = 10;
    public static final int O_CRC_0_13 = 14;
    public static final int O_MESSAGE = 16;
    //public final int oCRC_16_end = 16+packetLength;

}
