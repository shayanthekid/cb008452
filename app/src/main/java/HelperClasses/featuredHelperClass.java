package HelperClasses;

import java.io.Serializable;

public class featuredHelperClass implements Serializable {

    int image;
    String title,price;

    public featuredHelperClass(int image, String title, String price) {
        this.image = image;
        this.title = title;
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }


}
