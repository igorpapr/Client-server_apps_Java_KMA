package com.company.managers;

import com.company.utils.CRC16;
import com.company.utils.Encryptor;
import com.company.utils.ProtocolInfo;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PacketReceiver {
    private byte[] bytearray;
    private String message;

    public PacketReceiver(final byte[] packet) throws IllegalBlockSizeException, InvalidKeyException,
            BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        this.bytearray = packet;
        this.message = Arrays.toString(Encryptor.decrypt(Arrays.copyOfRange(bytearray,ProtocolInfo.O_MESSAGE,
                ProtocolInfo.O_MESSAGE +getMessageLength())));
        //System.out.println(message);
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

    private boolean isCorrectPackageMeta() {
        return isCorrectCRC(0, ProtocolInfo.O_CRC_0_13);
    }

    private boolean isCorrectMessage() {
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
}
