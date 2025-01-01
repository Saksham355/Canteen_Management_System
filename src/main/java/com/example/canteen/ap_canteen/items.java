package com.example.canteen.ap_canteen;

import java.io.Serializable;

public class items implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private int price;
    private int quantity;
    private String addons;
    private String category;
    private boolean availability;
    private String review;
    public int count = 0;
    public void setReview(String review) {
        this.review = review;
    }
    public String getReview() {
        return review;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAddons() {
        return addons;
    }
    public String stringAvailabilty(){
        return availability ? "Yes" : "No";
    }
    public String getCategory() {
        return category;
    }
    public static void printItem(String code){
        items item = canteen.code_to_item.get(code);
        String a = item.availability ? "Available" : "Unavailable";
        System.out.println(item.getCode()+": "+item.getPrice()+", "+a);
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean getAvailability() {
        return availability;
    }
    public void setAddons(String addons) {
        this.addons = addons;
    }
    public items(String code,int price, String addons, String category, boolean availability) {
        this.code = code;
        this.price = price;
        this.addons = addons;
        this.category = category;
        this.availability = availability;
    }

}
