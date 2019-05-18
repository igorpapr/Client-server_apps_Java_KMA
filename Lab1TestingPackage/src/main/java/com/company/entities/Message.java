package com.company.entities;

import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

public class Message {
    private int cType;
    private int bUserId;
    private JSONObject jsonMessage;

    public Message(final int cType, final int bUserId, final Serializable message) {
        this.cType = cType;
        this.bUserId = bUserId;
        jsonMessage = new JSONObject();
        jsonMessage.put("data",message);
    }

    public byte[] getBytes() throws UnsupportedEncodingException {
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

