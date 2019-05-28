package com.company.utils;

import com.company.entities.Message;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;

public class Decriptor{
    private static volatile Decriptor instance;
    private Cipher cipher;
    private Key key;

    private Decriptor() {
        try {
            byte[] key_b = "Hi_iAm_IhoR'sKey".getBytes();
            this.key  = new SecretKeySpec(key_b, "AES");
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public byte[] encrypt(byte[] bytes) {
//        try {
//            this.cipher.init(Cipher.ENCRYPT_MODE, this.key);
//            return this.cipher.doFinal(bytes);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public void decrypt(byte[] input) {
        try {
            this.cipher.init(Cipher.DECRYPT_MODE, this.key, this.cipher.getParameters());
            byte[] decrypted = this.cipher.doFinal(input);
            int cType = decode(Arrays.copyOfRange(decrypted,0,5));
            int bUserId = decode(Arrays.copyOfRange(decrypted,5,9));
            Message message = new Message(cType,bUserId,new String(Arrays.copyOfRange(decrypted,9,decrypted.length), StandardCharsets.UTF_16BE));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static int decode(byte[] bi) {
        return bi[3] & 0xFF | (bi[2] & 0xFF) << 8 |
                (bi[1] & 0xFF) << 16 | (bi[0] & 0xFF) << 24;
    }

    public static Decriptor getInstance() {
        Decriptor localInstance = instance;
        if (localInstance == null) {
            synchronized (Decriptor.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Decriptor();
                }
            }
        }
        return localInstance;
    }

}
