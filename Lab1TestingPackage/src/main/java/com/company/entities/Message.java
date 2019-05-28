package com.company.entities;

import com.company.utils.Cryptor;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class Message {
    private int cType;
    private int bUserId;
    private JSONObject jsonMessage;
    private boolean toEncrypt = true;

    public Message(int cType, int bUserId, String message) {//Serializable message
        this.cType = cType;
        this.bUserId = bUserId;
        try {
            jsonMessage = new JSONObject();
            jsonMessage.put("data", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
/*
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    Json.createWriter(stream).write(obj);

    byte[] sendData = stream.toByteArray()

    System.out.println("Bytes array: " + sendData);
    System.out.println("As a string: " + stream.toString());
*/
    }

    public byte[] getBytes() {
        byte[] res;
        byte[] payloadBytes = jsonMessage.toString().getBytes(StandardCharsets.UTF_16BE);
        ByteBuffer bb = ByteBuffer.allocate(8 + payloadBytes.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(this.cType);
        bb.putInt(this.bUserId);
        bb.put(payloadBytes);
        if (toEncrypt) {
            res = Cryptor.encrypt(bb.array());
        }else {
            res = bb.array();
        }
        return res;
    }

    public boolean isToEncrypt() {
        return toEncrypt;
    }

    public void setToEncrypt(boolean toEncrypt) {
        this.toEncrypt = toEncrypt;
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

