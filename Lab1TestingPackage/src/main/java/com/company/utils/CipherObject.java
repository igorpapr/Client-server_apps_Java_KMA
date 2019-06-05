package com.company.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class CipherObject {
    private Cipher cipher;
    private Key key;
    private static volatile CipherObject instance;


    private CipherObject() {

        try {
            byte[] keyBytes = "Hi_iAm_IhoR'sKey".getBytes();
            this.key  = new SecretKeySpec(keyBytes, "AES");
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public byte[] encrypt(byte[] bytes) {
        try {
            this.cipher.init(Cipher.ENCRYPT_MODE, this.key, this.cipher.getParameters());
            return this.cipher.doFinal(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] decrypt(byte[] bytes) {
        try {
            this.cipher.init(Cipher.DECRYPT_MODE, this.key, this.cipher.getParameters());
            return this.cipher.doFinal(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CipherObject getInstance() {
        CipherObject localInstance = instance;
        if (localInstance == null) {
            synchronized (CipherObject.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CipherObject();
                }
            }
        }
        return localInstance;
    }

}
