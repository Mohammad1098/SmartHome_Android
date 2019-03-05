package com.example.android.smarthome.MicroController;

public class MicroController_Category {

        private String categoryName;
        private int categoryImage;
        private int type;





    public MicroController_Category(String categoryName , int categoryImage , int type){

            this.categoryName = categoryName;
            this.categoryImage = categoryImage;
            this.type = type;

        }


         public void setType(int type) {this.type = type;}

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public void setCategoryImage(int categoryImage) {
            this.categoryImage = categoryImage;
        }

        public String getCategoryName() {return categoryName;}

        public int getCategoryImage() {
            return categoryImage;
        }

        public int getType() {return type;}


    }



