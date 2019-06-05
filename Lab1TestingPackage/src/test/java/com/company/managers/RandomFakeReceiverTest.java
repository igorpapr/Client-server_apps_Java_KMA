package com.company.managers;

import com.company.managers.impl.RandomFakeReceiver;
import org.junit.Test;

public class RandomFakeReceiverTest {

    @Test
    public void checkThreadWork(){
        for (int i = 0; i < 5; i++){
            new Thread(){
                @Override
                public void run(){
                    try {
                        RandomFakeReceiver r = new RandomFakeReceiver();
                        r.receiveMessage();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}


