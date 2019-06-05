package com.company.entities.storage;

import java.util.ArrayList;

public class GoodsStorage {
    private ArrayList<GoodsGroup> list;

    public GoodsStorage(){
        this.list = new ArrayList<GoodsGroup>();
    }

    public ArrayList<GoodsGroup> getList() {
        return list;
    }
}
