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
    //private String message;

    public Message(int cType,int bUserId,String message) {//Serializable message
        this.cType = cType;
        this.bUserId = bUserId;
        try {
            jsonMessage = new JSONObject();
            jsonMessage.put("data", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //this.message = message;
    }

    public byte[] getBytes() {
        //byte[] messageBytes = Cryptor.getInstance().encrypt(this.message.getBytes(StandardCharsets.UTF_16BE));
        //String s = new String(messageBytes, StandardCharsets.UTF_16BE);
        //System.out.println(s);
        byte[] messageBytes = jsonMessage.toString().getBytes(StandardCharsets.UTF_16BE);
        ByteBuffer bb = ByteBuffer.allocate(8 + messageBytes.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(this.cType);
        bb.putInt(this.bUserId);
        bb.put(messageBytes);
        return Cryptor.encrypt(bb.array());
    }

    public int getcType() {
        return cType;
    }

    public int getbUserId() {
        return bUserId;
    }

//    public JSONObject getJsonMessage() {
//        return jsonMessage;
//    }

}

