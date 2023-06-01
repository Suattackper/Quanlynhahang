
package com.raven.model;

import javax.swing.Icon;


public class ModelItem {

   

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Icon getImage() {
        return image;
    }

    public void setImage(Icon image) {
        this.image = image;
    }
    
    
    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public ModelItem(String itemID, String nameItem, String description, int price, String brandName, Icon image) {
        this.itemID = itemID;
        this.nameItem = nameItem;
        this.description = description;
        this.price = price;
        this.brandName = brandName;
        this.image = image;
    }
    public ModelItem(String itemID, String nameItem, int price) {
        this.itemID = itemID;
        this.nameItem = nameItem;
        this.description = null;
        this.price = price;
        this.brandName = null;
        this.image = null;
    }

   
    

    public ModelItem() {
    }
    
    private String itemID;
    private String nameItem;
//    private String 
    private String description;
    private int price;
    private String brandName;
    private Icon image;
    
}
