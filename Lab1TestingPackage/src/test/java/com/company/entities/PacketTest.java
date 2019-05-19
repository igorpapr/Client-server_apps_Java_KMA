package com.company.entities;
import org.junit.Assert;
import org.junit.Test;


public class PacketTest {
    @Test
    public void fillDataNull(){
        Packet p = new Packet(0, 0, 0, "hello");
        Assert.assertNotEquals(0,p.getData().length);//emptiness
        Assert.assertEquals(1,Packet.getPcktId());
    }
}
