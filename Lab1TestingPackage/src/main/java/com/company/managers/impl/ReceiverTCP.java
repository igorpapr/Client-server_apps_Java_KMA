package com.company.managers.impl;

import com.company.entities.Message;
import com.company.managers.IReceiver;
import com.company.managers.Processor;
import com.company.managers.Sender;
import com.company.utils.CRC16;
import com.company.utils.Decriptor;
import com.company.utils.ProtocolInfo;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ReceiverTCP implements IReceiver {

    private byte[] data;
    private int src;
    private long pcktId;
    private int messageLength;
    private ObjectOutputStream out;

    public ReceiverTCP(byte[] data, ObjectOutputStream out){
        this.data = data;
        this.out = out;
        try {
            this.src = (int) ByteBuffer.wrap(data, ProtocolInfo.O_SRC,1).get();
            this.pcktId = ByteBuffer.wrap(data,ProtocolInfo.O_PKTID, 8).getLong();
            this.messageLength = ByteBuffer.wrap(data,ProtocolInfo.O_LEN,4).getInt();
            System.out.println("INSIDE RECEIVER: messageLength =" + messageLength);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void receiveMessage() {
        new Thread(){
            @Override
            public void run(){
                if(!checkSums()){
                    throw new ValueException("Bad checksums");
                }
                //System.out.println("Start of " + this.getName());
                Processor processor = new Processor();
                Message m = processor.process(Decriptor.getInstance().decryptAndProcess(Arrays.copyOfRange(data,
                        ProtocolInfo.O_MESSAGE,
                        ProtocolInfo.O_MESSAGE + messageLength)));
                m.setToEncrypt(false);
                try {
                    Sender sender = new Sender(m.getBytes());
                    sender.send(out);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public boolean checkSums() {
        return isCorrectPackageMeta() && isCorrectMessage();
    }

    private boolean isCorrectCRC(int from, int crcOffset){
        int afterCRC_16 = CRC16.get_CRC16(Arrays.copyOfRange(data, from, crcOffset));
        int beforeCRC_16 = decode(Arrays.copyOfRange(data,crcOffset,crcOffset + 4));
        //System.out.println("After CRC = " + afterCRC_16);
        //System.out.println("Before CRC = " + beforeCRC_16);
        return (afterCRC_16 == beforeCRC_16);
    }

    public boolean isCorrectPackageMeta() {
        return isCorrectCRC(0, ProtocolInfo.O_CRC_0_13);
    }

    public boolean isCorrectMessage() {
        return isCorrectCRC(ProtocolInfo.O_MESSAGE, ProtocolInfo.O_MESSAGE + getMessageLength());
    }

    public int getMessageLength() {
        return messageLength;
    }

    public byte[] getData() {
        return data;
    }

    public int getSrc() {
        return src;
    }

    public long getPcktId() {
        return pcktId;
    }

    private static int decode(byte[] bi) {
        return bi[3] & 0xFF | (bi[2] & 0xFF) << 8 |
                (bi[1] & 0xFF) << 16 | (bi[0] & 0xFF) << 24;
    }
}
