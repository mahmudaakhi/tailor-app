package com.example.doorsteptailors;

public class ProductClass {

    String  name, price, discount, catagory, approved;

    public ProductClass()
    {

    }

    public ProductClass(String name, String price, String discount, String catagory, String approved) {
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.catagory = catagory;
        this.approved = approved;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getApproved() {
        return approved;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }
}
