package com.company.utils;

public class ProtocolInfo {
    //OFFSETS
    /**
     * Offset of magic number (13h)
     */
    public static final int O_MAGIC = 0;
    /**
     * Offset of Unique number of user app
     * */
    public static final int O_SRC = 1;
    /**
     * Offset of Packet ID
     * */
    public static final int O_PKTID = 2;
    /**
     * Offset of Length of the message
     * */
    public static final int O_LEN = 10;
    /**
     * Offset of CRC_16 from the start of the packet to the end of meta info (before itself)
     * */
    public static final int O_CRC_0_13 = 14;
    /**
     * Offset of message start
     * */
    public static final int O_MESSAGE = 18;



    //COMMANDS
    /**
     * Get amount of goods on the storage
     */
    public static final int C_GET_AMOUNT = 0;
    /**
     * Delete goods from storage
     * */
    public static final int C_DEL_GOODS = 1;
    /**
     * Add amount of goods to the storage
     * */
    public static final int C_ADD_GOODS = 2;
    /**
     * Add group of goods to the storage
     * */
    public static final int C_ADD_GROUP = 3;
    /**
     * Add group title
     * */
    public static final int C_ADD_GROUP_TITLE = 4;
    /**
     * Set price of the goods
     * */
    public static final int C_SET_PRICE = 5;
}
