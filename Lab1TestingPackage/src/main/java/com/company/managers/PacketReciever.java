package com.company.managers;

import com.company.utils.CRC16;
import com.company.utils.ProtocolInfo;

import java.util.Arrays;

public class PacketReciever {
    private byte[] bytearray;

    public PacketReciever(final byte[] packet) {
        this.bytearray = packet;
    }

    public boolean checkSums() {
        return isCorrectMessage() && isCorrectPackageMeta();
    }

    private boolean isCorrectPackageMeta() {
        byte[] packageMeta = Arrays.copyOfRange(bytearray, 0, ProtocolInfo.O_CRC_0_13);
        int CRC_16 = CRC16.get_CRC16(packageMeta);

        return true;
    }

    private boolean isCorrectMessage() {
        return true;
    }


    public byte[] getBytearray() {
        return bytearray;
    }
}
