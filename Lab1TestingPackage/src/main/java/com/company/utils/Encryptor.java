package com.company.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encryptor {
    private static SecretKey secretKey;
    private static Cipher cipher;

    public Encryptor() throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException,
            IllegalBlockSizeException {
        cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        secretKey = generateKey();
    }

    public static byte[] encrypt(final String data) throws NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] messagePlain = data.getBytes(StandardCharsets.UTF_16BE);
        return cipher.doFinal(messagePlain);
    }

    public static byte[] decrypt(final byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException,
            BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }


    public static SecretKey generateKey() throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        SecureRandom secureRandom = new SecureRandom();
//        int keyBitSize = 128;
//        SecretKey secretKey = keyGenerator.generateKey();

        //keyGenerator.init(keyBitSize, secureRandom);
        return secretKey;
    }

    public SecretKey getSecretKey() {
        return secretKey;
    }
}
