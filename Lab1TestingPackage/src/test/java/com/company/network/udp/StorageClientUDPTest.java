package com.company.network.udp;

import com.company.managers.impl.RandomFakeReceiver;
import org.junit.Before;
import org.junit.Test;

public class StorageClientUDPTest {

    @Before
    public void setup(){
        StoreServerUDP server = new StoreServerUDP();
    }

    @Test
    public void checkThreadWork(){
        for (int i = 0; i < 5; i++){
            new Thread(){
                @Override
                public void run(){
                    try {
                        StoreClientUDP client = new StoreClientUDP();

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
