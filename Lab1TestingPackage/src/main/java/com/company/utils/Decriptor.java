package com.company.utils;

import com.company.entities.Message;
import com.company.managers.Processor;
import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import static java.nio.charset.StandardCharsets.UTF_16BE;

public class Decriptor{
    private static volatile Decriptor instance;
    private Cipher cipher;
    private Key key;

    private Decriptor() {
        try {
            byte[] key_b = "Hi_iAm_IhoR'sKey".getBytes(UTF_16BE);
            this.key  = new SecretKeySpec(key_b, "AES");
            this.cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message decryptAndProcess(byte[] input) {
        try {
            //this.cipher.init(Cipher.DECRYPT_MODE, this.key, this.cipher.getParameters());
            //byte[] decrypted = this.cipher.doFinal(input);

            //byte[] decrypted = CipherObject.getInstance().decrypt(input); //TODO in future!!!!
            byte[] decrypted = input;                                       //TODO

            int cType = ByteBuffer.wrap(decrypted,0,4).getInt();
            System.out.println("Decrypted cType: " + cType);

            int bUserId = ByteBuffer.wrap(decrypted,4,4).getInt();
            System.out.println("Decrypted bUserId: " + bUserId);

            String mess = StandardCharsets.UTF_16BE.decode(ByteBuffer.wrap(decrypted,8,decrypted.length-8)).toString();//.array(), StandardCharsets.UTF_16BE);
            JSONObject j = new JSONObject(mess);
            Message message = new Message(cType, bUserId,j);
            System.out.println("Decrypted payload: " + message.getJsonMessage().toString());

            return message;
//            Processor p = new Processor();
//            p.process(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getDecrypted(byte[] input) {
        try {
            this.cipher.init(Cipher.DECRYPT_MODE, this.key, this.cipher.getParameters());
            return this.cipher.doFinal(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
//        return CipherObject.getInstance().decrypt(input);
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
