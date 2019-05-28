package com.company.utils;

import com.company.managers.Sender;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.net.InetAddress;
import java.security.Key;

public class Encriptor{
    private static volatile Encriptor instance;
    private Cipher cipher;
    private Key key;

    private Encriptor() {
        try {
            byte[] key_b = "Hi_iAm_IhoR'sKey".getBytes();
            this.key  = new SecretKeySpec(key_b, "AES");
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void encrypt(byte[] bytes) {
        try {
            this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
            Sender sender = new Sender();
            sender.send(this.cipher.doFinal(bytes), InetAddress.getLoopbackAddress()); //FAKE ADDRESS!!!!!!!!!!!!!!!!!!!!

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int decode(byte[] bi) {
        return bi[3] & 0xFF | (bi[2] & 0xFF) << 8 |
                (bi[1] & 0xFF) << 16 | (bi[0] & 0xFF) << 24;
    }

    public static Encriptor getInstance() {
        Encriptor localInstance = instance;
        if (localInstance == null) {
            synchronized (Decriptor.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Encriptor();
                }
            }
        }
        return localInstance;
    }
}