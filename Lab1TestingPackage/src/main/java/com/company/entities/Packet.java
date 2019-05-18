package com.company.entities;

import com.company.utils.CRC16;
import com.company.utils.ProtocolInfo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Packet {
    private static final byte bMagic = 0x13;
    private static long pcktId;
    private byte[] data;
    private Message message;
    private int packetLength;

    /**
     * @param src     unique number of client app, must be 1 byte (This is a task)
     * @param cType   code of command
     * @param bUserId user id
     * @param message payload to send
     */
    public Packet(final int src, final int cType, final int bUserId, final Serializable message) {
        //in future there will be Message created by PacketCreator class
        try {
            if (src > 255)
                throw new IllegalArgumentException("Unique number of client must be less than 1 byte");
            packetLength = 18; // + message length in future

            this.message = new Message(cType, bUserId, message); //in future there will be Message created by PacketCreator class

            data = fillData(src);
            pcktId++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private byte[] fillData(final int src) throws IOException, BufferOverflowException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ByteBuffer bb = ByteBuffer.allocate(2);
        baos.write(bb.put(bMagic).array());
        baos.write(bb.put((byte) src).array());

        bb = ByteBuffer.allocate(8);
        baos.write(bb.putLong(pcktId).array());

        bb = ByteBuffer.allocate(4);
        byte[] messageBytes = this.message.getBytes();
        int messageLength = messageBytes.length;
        packetLength += messageLength;
        baos.write(bb.putInt(packetLength).array());

        bb = ByteBuffer.allocate(4);
        byte[] arr_0_13 = Arrays.copyOfRange(baos.toByteArray(), 0, ProtocolInfo.O_CRC_0_13);
        baos.write(bb.putInt(CRC16.get_CRC16(arr_0_13)).array());

        bb = ByteBuffer.allocate(messageLength);
        baos.write(bb.put(messageBytes).array());

        bb = ByteBuffer.allocate(4);
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
}
