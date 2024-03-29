package com.company.entities;

import com.company.utils.CRC16;
import com.company.utils.ProtocolInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Packet {
    private static final byte bMagic = 0x13;
    private static long pcktId;
    private byte[] data;
    private Message message;
    private int packetLength;
    private int src;
    private int messageLength;
    private boolean toCipher; //to use cypher or not

    /**
     * @param src     unique number of client app, must be 1 byte (This is a task)
     * @param cType   code of command
     * @param bUserId user id
     * @param message payload to send
     */
    public Packet(int src, int cType, int bUserId, String message, boolean toCipher) {//Serializable message
        try {
            if (src > 255)
                throw new IllegalArgumentException("Unique number of client must be less than 1 byte");
            this.src = src;
            this.message = new Message(cType, bUserId, message);
            this.message.setToEncrypt(toCipher);

            data = fillData();
            pcktId++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Packet(int src, int cType, int bUserId, String message){
        this(src,cType,bUserId,message,true);
    }

    /**
     * @return packed array of bytes of everything of our packet
     */
    private byte[] fillData() throws IOException, BufferOverflowException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteBuffer bb = ByteBuffer.allocate(2);
        bb.put(bMagic);
        baos.write(bb.put((byte) src).array());

        bb = ByteBuffer.allocate(8);
        baos.write(bb.putLong(pcktId).array());

        packetLength = baos.size();
        bb = ByteBuffer.allocate(4);
        byte[] messageBytes = this.message.getBytes();
        messageLength = messageBytes.length;
        System.out.println("Message Length = " + messageLength);
        packetLength += messageLength;
        packetLength += 12; //CRCs and size of packetLength by itself - in future
        baos.write(bb.putInt(messageLength).array());
        System.out.println("Packet Length = " + packetLength);

        bb = ByteBuffer.allocate(4);
        //bytes 0 - 13
        byte[] arr_0_13 = Arrays.copyOfRange(baos.toByteArray(), 0, ProtocolInfo.O_CRC_0_13);
        baos.write(bb.putInt(CRC16.get_CRC16(arr_0_13)).array());

        bb = ByteBuffer.allocate(messageLength);
        baos.write(bb.put(messageBytes).array());

        bb = ByteBuffer.allocate(4);
        //bytes 18 - endOfPacket
        byte[] arr_toEnd = Arrays.copyOfRange(baos.toByteArray(), ProtocolInfo.O_MESSAGE,
                ProtocolInfo.O_MESSAGE + messageLength);
        baos.write(bb.putInt(CRC16.get_CRC16(arr_toEnd)).array());

        return baos.toByteArray();
    }

    public static long getPcktId() {
        return pcktId;
    }

    public byte[] getData() {
        return data;
    }

    public int getPacketLength() {
        return packetLength;
    }

    public int getMessageLength() {
        return messageLength;
    }

    public Message getMessage() {
        return message;
    }

    public int getSrc() {
        return src;
    }
}
