package entity;

import types.ProductType;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private ProductType productType;

    public Product(int id, String name, double price, int quantity, ProductType productType) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.productType = productType;
    }

    public Product(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public String toString(){
        return
                id + " | \t" + name + " | \t" + price + " | \t" + quantity + " | \t" + productType;


    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
