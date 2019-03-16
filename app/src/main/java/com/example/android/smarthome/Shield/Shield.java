package com.example.android.smarthome.Shield;

public class Shield {

    private long MicroControllerID;
    private String Name;
    private int type;
    private int shieldImage;


    public Shield(String name , int Image , int type) {
        this.Name = name;
        this.shieldImage = Image;
        this.type = type;
    }


    public Shield() {

    }

    public void setMicroControllerID(long microControllerID) {
        this.MicroControllerID = microControllerID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setShieldImage(int shieldImage) {this.shieldImage = shieldImage;}

    public void setType(int type) {
        this.type = type;
    }

    public long getMicroControllerID() {
        return MicroControllerID;
    }

    public String getName() {
        return Name;
    }

    public int getType() {
        return type;
    }

    public int getShieldImage() {return shieldImage;}
}
