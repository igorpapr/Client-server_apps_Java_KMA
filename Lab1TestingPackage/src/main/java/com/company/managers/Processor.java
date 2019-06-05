package com.company.managers;

import com.company.entities.storage.GoodsStorage;
import com.company.entities.Message;
import com.company.utils.Encriptor;

public class Processor {

    public static final GoodsStorage gs = new GoodsStorage();
    private boolean isOk;

    public Processor(){
    }

    public Message process(Message message) {
        int command = message.getcType();
        synchronized (gs){
            try{
                //something herewith commands
                System.out.println("PROCESSING MESSAGE");
                System.out.println("Message: " + message.getJsonMessage().toString());
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                //notifyAll();
            }
        }
        //Message m = new Message(command, message.getbUserId(), "OK");
        //TODO JUST TO CHECK THE WORK OF OUR SERVER WE WILL SEND BACK THE MESSAGE WE GOT
        //m.setToEncrypt(false);
        //message.setToEncrypt(false);
        //Encriptor.getInstance().encryptAndSend(message.getBytes()); //TODO
        return message;
    }
}
