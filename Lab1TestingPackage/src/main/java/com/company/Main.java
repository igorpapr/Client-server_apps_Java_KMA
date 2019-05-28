package com.company;

import com.company.managers.impl.RandomFakeReceiver;

public class Main {

    public static void main(String[] args)
    {
        RandomFakeReceiver rfr = new RandomFakeReceiver();
        rfr.receiveMessage();
    }
}
