package com.company.managers;

import com.company.utils.CRC16;
import com.company.utils.ProtocolInfo;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class PacketReceiver {
    private byte[] bytearray;

    public PacketReceiver(final byte[] packet) {
        this.bytearray = packet;
    }

    public boolean checkSums() {
        return isCorrectMessage() && isCorrectPackageMeta();
    }

    private boolean isCorrectPackageMeta() {
        byte[] packageMeta = Arrays.copyOfRange(bytearray, 0, ProtocolInfo.O_CRC_0_13);
        int afterCRC_16 = CRC16.get_CRC16(packageMeta);

        byte[] beforeCRC_16 = Arrays.copyOfRange(bytearray, ProtocolInfo.O_CRC_0_13, ProtocolInfo.O_CRC_0_13 + 4);
        return decode(beforeCRC_16) == afterCRC_16;
    }

    private static int decode(byte[] bi) {
        return bi[3] & 0xFF | (bi[2] & 0xFF) << 8 |
                (bi[1] & 0xFF) << 16 | (bi[0] & 0xFF) << 24;
    }

    private byte[] sliceOfBytes(byte[] src, int from, int to){
        return Arrays.copyOfRange(src, from, to);
    }

    private boolean isCorrectMessage() {
        return true;
    }


    public byte[] getBytearray() {
        return bytearray;
    }
}
