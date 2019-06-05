package com.company.utils;

import com.company.managers.Sender;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class Encriptor {
    private static volatile Encriptor instance;
    private Cipher cipher;
    private Key key;

    private Encriptor() {
        try {
            byte[] key_b = "Hi_iAm_IhoR'sKey".getBytes(StandardCharsets.UTF_16BE);
            this.key = new SecretKeySpec(key_b, "AES");
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void encryptAndSend(byte[] bytes) {
        try {
            //this.cipher.init(Cipher.ENCRYPT_MODE, this.key);

            Sender sender = new Sender(CipherObject.getInstance().encrypt(bytes));
            //sender.send(this.cipher.doFinal(bytes), InetAddress.getLoopbackAddress()); //FAKE ADDRESS!!!!!!!!!!!!!!!!!!!!
            //sender.send(InetAddress.getLoopbackAddress(), PORT); //TODO address FIX

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] getEncrypted(byte[] bytes) {
        return CipherObject.getInstance().encrypt(bytes);
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