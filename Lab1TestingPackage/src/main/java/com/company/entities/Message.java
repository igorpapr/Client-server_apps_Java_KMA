package com.company.entities;

import com.company.utils.Encryptor;
import org.json.JSONObject;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

public class Message {
    private int cType;
    private int bUserId;
    private JSONObject jsonMessage;
    private static SecretKey secretKey; //Secret in future, now just to test, before having any web connections

    public Message(final int cType, final int bUserId, final String message) {//Serializable message
        this.cType = cType;
        this.bUserId = bUserId;
        try {
            Encryptor encryptor = new Encryptor();
            jsonMessage = new JSONObject();
            jsonMessage.put("data", Encryptor.encrypt(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SecretKey getSecretKey() {
        return secretKey;
    }

    public byte[] getBytes() {
        byte[] messageBytes = jsonMessage.toString().getBytes(StandardCharsets.UTF_16BE);
        ByteBuffer bb = ByteBuffer.allocate(8 + messageBytes.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(this.cType);
        bb.putInt(this.bUserId);
        bb.put(messageBytes);
        return bb.array();
    }

    public int getcType() {
        return cType;
    }

    public int getbUserId() {
        return bUserId;
    }

    public JSONObject getJsonMessage() {
        return jsonMessage;
    }

}

