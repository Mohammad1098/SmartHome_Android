package com.example.android.smarthome.Shield;

public class ShieldCategory {


    private String ShieldName;
    private int ShieldImage;
    private int type;             // 0 Relay   , 1 IR
    private int pin;
    private long shieldID;

    public long getShieldID() {
        return shieldID;
    }

    public void setShieldID(long shieldID) {
        this.shieldID = shieldID;
    }

    public ShieldCategory(){

    }

    public ShieldCategory(String ShieldName , int ShieldImage , int type , int pin ){

        this.ShieldName = ShieldName;
        this.ShieldImage = ShieldImage;
        this.type = type;
        this.pin = pin;


    }

    public String getShieldName() {
        return ShieldName;
    }

    public void setShieldName(String shieldName) {
        ShieldName = shieldName;
    }

    public int getShieldImage() {
        return ShieldImage;
    }

    public void setShieldImage(int shieldImage) {
        ShieldImage = shieldImage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
