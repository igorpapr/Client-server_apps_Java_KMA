package com.company.network.udp;

public class StoreClientUDPCreator {
    public static void main(String[] args){

        new Thread(){
            @Override
            public void run(){
                for (int i = 0; i < 4; i++){
                    new StoreClientUDP();
                }
            }
        }.start();
    }
}
