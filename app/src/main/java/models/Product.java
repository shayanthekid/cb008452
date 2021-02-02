package models;

import android.net.Uri;

import java.io.Serializable;
import java.util.HashMap;

public class Product implements Serializable {

String Name,Description,Category,imageString;
Uri imageuri;
int price;

HashMap<String, String> Type = new HashMap<>();

    public Product(){

    }
    public Product(String name, String description, String category, String imageString, Uri imageuri, int price, HashMap<String, String> type) {
        Name = name;
        Description = description;
        Category = category;
        this.imageString = imageString;
        this.imageuri = imageuri;
        this.price = price;
        Type = type;
    }

    public Product(String name, String description, String category, String imageString, Uri imageuri, int price) {
        Name = name;
        Description = description;
        Category = category;
        this.imageString = imageString;
        this.imageuri = imageuri;
        this.price = price;
    }

    public Product(String name, String description, String category, String imageString, int price, HashMap<String, String> type) {
        Name = name;
        Description = description;
        Category = category;
        this.imageString = imageString;
        this.price = price;
        Type = type;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }

    public Uri getImageuri() {
        return imageuri;
    }

    public void setImageuri(Uri imageuri) {
        this.imageuri = imageuri;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public HashMap<String, String> getType() {
        return Type;
    }

    public void setType(HashMap<String, String> type) {
        Type = type;
    }
}


