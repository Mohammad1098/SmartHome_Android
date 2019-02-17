package com.example.android.smarthome.DeviceCategory;

public class DeviceCategory {


    private String categoryName;
    private int categoryImage;
    private int type;
    private int id;





    public DeviceCategory(String categoryName , int categoryImage ){

        this.categoryName = categoryName;
        this.categoryImage = categoryImage;

    }

    public DeviceCategory( ){


    }


    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryImage(int categoryImage) {
        this.categoryImage = categoryImage;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCategoryName() {

        return categoryName;
    }

    public int getCategoryImage() {
        return categoryImage;
    }

    public int getType() {
        return type;
    }

    public int getId() {

        return id;
    }
}
