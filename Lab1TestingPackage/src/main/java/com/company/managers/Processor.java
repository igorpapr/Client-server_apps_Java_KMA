package com.company.managers;

import com.company.entities.Message;
import com.company.entities.Packet;
import com.company.utils.Encriptor;

public class Processor {

    private boolean isOk;

    public Processor(){

    }

    public void process(Message message) {
        int command = message.getcType();
        //something herewith commands

        Message m = new Message(command, message.getbUserId(),"OK");
        m.setToEncrypt(false);
        Encriptor.getInstance().encrypt(m.getBytes());
    }
}
