package com.company.managers;

import com.company.utils.CRC16;
import com.company.utils.Cryptor;
import com.company.utils.ProtocolInfo;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class PacketReceiver {
    private byte[] bytearray;
    private byte[] messageData;
    public PacketReceiver(byte[] packet) {
        this.bytearray = packet;

        this.messageData = Cryptor.decrypt(Arrays.copyOfRange(bytearray,ProtocolInfo.O_MESSAGE+8,
                ProtocolInfo.O_MESSAGE +getMessageLength()));

        String s = new String(messageData, StandardCharsets.UTF_16BE);
        System.out.println(s);
    }

    public boolean checkSums() {
        return isCorrectPackageMeta() && isCorrectMessage();
    }

    private boolean isCorrectCRC(int from, int crcOffset){
        int afterCRC_16 = CRC16.get_CRC16(Arrays.copyOfRange(bytearray, from, crcOffset));
        int beforeCRC_16 = decode(Arrays.copyOfRange(bytearray,crcOffset,crcOffset + 4));
        System.out.println("After CRC = " + afterCRC_16);
        System.out.println("Before CRC = " + beforeCRC_16);
        return (afterCRC_16 == beforeCRC_16);
    }

    public boolean isCorrectPackageMeta() {
        return isCorrectCRC(0, ProtocolInfo.O_CRC_0_13);
    }

    public boolean isCorrectMessage() {
        return isCorrectCRC(ProtocolInfo.O_MESSAGE, ProtocolInfo.O_MESSAGE + getMessageLength());
    }

    public int getMessageLength() {
        byte[] messageLengthBytes = Arrays.copyOfRange(bytearray, ProtocolInfo.O_LEN, ProtocolInfo.O_CRC_0_13);
        return decode(messageLengthBytes);
    }

    private static int decode(byte[] bi) {
        return bi[3] & 0xFF | (bi[2] & 0xFF) << 8 |
                (bi[1] & 0xFF) << 16 | (bi[0] & 0xFF) << 24;
    }

    public byte[] getBytearray() {
        return bytearray;
    }

    public byte[] getMessageData() {
        return messageData;
    }

    public int getCType(){
        return decode(Arrays.copyOfRange(messageData,0,5));
    }

    public int getBUserId(){
        return decode(Arrays.copyOfRange(messageData,5,9));
    }

    public String getMessageContent(){
        return new String(Arrays.copyOfRange(messageData,0,messageData.length), StandardCharsets.UTF_16BE);
    }
}
