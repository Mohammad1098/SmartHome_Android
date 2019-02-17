package com.example.android.smarthome.Operation;

public class Operation {

    private String Name;
    private String implementation;



    public void setName(String name) {

        this.Name = name;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    public String getName() {
        return Name;
    }

    public String getImplementation() {
        return implementation;
    }
}
