package com.example.groccery_app;

public class ListItem {

    private int _id;
    private String _name;
    private String _bought;



    //constructor
    ListItem(String name, String bought) {
        this._name = name;
        this._bought = bought;
    }

    ListItem(int ID, String name, String bought) {
        this._id = ID;
        this._name = name;
        this._bought = bought;
    }

    ListItem( String name){
        this._name = name;
        this._bought = "False";
    }

    ListItem(){

    }


    public int getID() {
        return _id;
    }

    public void setID(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_bought() {
        return _bought;
    }

    public void set_bought(String _bought) {
        this._bought = _bought;
    }
}






