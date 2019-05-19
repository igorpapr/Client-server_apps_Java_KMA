package com.company.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Cryptor {
    private static SecretKey secretKey;
    private static Cipher cipher;

    public static byte[] encrypt(byte[] data) {
        try {
            processCipher();
            getKey();

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            return cipher.doFinal(data);
        } catch (BadPaddingException |InvalidKeyException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] data) {
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, cipher.getParameters());
            return cipher.doFinal(data);
        } catch (InvalidKeyException | InvalidAlgorithmParameterException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void getKey() {
        if (secretKey == null)
            try {
                byte[] keyBytes = "Hi_iAm_IhoR'sKey".getBytes();
                secretKey = new SecretKeySpec(keyBytes, "AES");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void processCipher(){
        if (cipher == null) {
            try {
                cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }
}