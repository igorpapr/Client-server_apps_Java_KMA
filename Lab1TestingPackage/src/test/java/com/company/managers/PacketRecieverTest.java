package com.company.managers;

import com.company.entities.Packet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PacketRecieverTest {
    private Packet p;
    private Packet p1;
    private PacketReceiver pr;
    private PacketReceiver pr1;

    @Before
    public void setup(){
         p = new Packet(2,2,2,"Hello world!");
         p1 = new Packet(1,1,1,"Привіт, світ!");
         pr = new PacketReceiver(p.getData());
         pr1 = new PacketReceiver(p1.getData());
    }

    @Test
    public void checkLengths(){
        Assert.assertEquals(p.getMessageLength(), pr.getMessageLength());
    }

    @Test
    public void checkMessageContent(){
        Assert.assertTrue(pr.getMessageContent().contains("Hello world!"));
        Assert.assertTrue(pr1.getMessageContent().contains("Привіт, світ!"));
    }

    @Test
    public void checkCrcs(){
        Assert.assertTrue(pr.checkSums());
        Assert.assertTrue(pr1.checkSums());
    }
}
