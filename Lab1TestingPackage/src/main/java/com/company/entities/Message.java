package com.company.entities;

import com.company.utils.Cryptor;
import com.company.utils.Decriptor;
import com.company.utils.Encriptor;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
            System.out.println("CREATED JSON: "+ jsonMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Message(int cType, int bUserId, JSONObject message) {
        this.cType = cType;
        this.bUserId = bUserId;
        this.jsonMessage = message;
    }

    public byte[] getBytes() {
        byte[] res;
        byte[] payloadBytes = jsonMessage.toString().getBytes(StandardCharsets.UTF_16BE);
        ByteBuffer bb = ByteBuffer.allocate(8 + payloadBytes.length);
        bb.order(ByteOrder.BIG_ENDIAN);
        bb.putInt(this.cType);
        bb.putInt(this.bUserId);
        bb.put(payloadBytes);
        //System.out.println(Arrays.toString(bb.array()));
        //System.out.println(ByteBuffer.wrap(bb.array(),0,4).getInt());
        //System.out.println(ByteBuffer.wrap(bb.array(),4,4).getInt());
        //System.out.println(new String(Arrays.copyOfRange(bb.array(),9,bb.array().length),StandardCharsets.UTF_8));
        if (toEncrypt) {
            res = Encriptor.getInstance().getEncrypted(bb.array());
        }else {
            res = bb.array();
        }
        return res;
    }

    public boolean isToEncrypt() {
        return toEncrypt;
    }

    private static int decode(byte[] bi) {
        return bi[3] & 0xFF | (bi[2] & 0xFF) << 8 |
                (bi[1] & 0xFF) << 16 | (bi[0] & 0xFF) << 24;
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

