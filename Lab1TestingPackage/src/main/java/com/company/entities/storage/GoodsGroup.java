package com.company.entities.storage;

import java.util.ArrayList;

public class GoodsGroup {
    private ArrayList<Goods> list;
    private String title;

    public GoodsGroup(String title){
        this.title = title;
        list = new ArrayList<Goods>();
    }

    public void addGoods(Goods g){
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getTitle().equals(g.getTitle())){
                list.get(i).setAmount(list.get(i).getAmount() + g.getAmount());
                return;
            }
        }
        list.add(g);
    }

    public void deleteGoods(Goods g){
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).getTitle().equals(g.getTitle())){
                if(list.get(i).getAmount() < g.getAmount()){
                    throw new IllegalArgumentException();
                    //list.remove(i);
                }
                list.get(i).setAmount(list.get(i).getAmount() - g.getAmount());
                return;
            }
        }
    }

    public ArrayList<Goods> getList() {
        return list;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
