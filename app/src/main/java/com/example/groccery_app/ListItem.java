package com.example.groccery_app;

public class ListItem {

    private int itemId;
    private String name;
    private int bought;
    private int productListId;


    //empty Constructor
    ListItem(){

    }

    //constructor
    ListItem(int itemId, String name, int bought, int productListId){
        this.itemId = itemId;
        this.name = name;
        this.bought = bought;
        this.productListId = productListId;
    }



    //getters and setters
    public int getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBought() {
        return bought;
    }

    public int getProductListId() {
        return productListId;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }



    public void setProductListId(int itemListId) {
        this.productListId = productListId;
    }
}
