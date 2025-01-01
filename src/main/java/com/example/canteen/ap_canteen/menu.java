package com.example.canteen.ap_canteen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class menu {
    public void setMenu(Map<String, ArrayList<String>> menu) {
        this.menu = menu;
    }

    private Map<String, ArrayList<String>> menu ;
    private ArrayList<String> indian;
    private ArrayList<String> continental;
    private ArrayList<String> chinese;
    private ArrayList<String> beverages;
    private ArrayList<String> sweets;
    private ArrayList<String> snacks;
    public Map<String, ArrayList<String>> getMenu() {
        return menu;
    }

    public ArrayList<String> getIndian() {
        return indian;
    }

    public ArrayList<String> getContinental() {
        return continental;
    }

    public ArrayList<String> getChinese() {
        return chinese;
    }

    public ArrayList<String> getBeverages() {
        return beverages;
    }

    public ArrayList<String> getSweets() {
        return sweets;
    }

    public ArrayList<String> getSnacks() {
        return snacks;
    }
    public menu(Map<String, ArrayList<String>> menu) {
        this.menu = menu;
        indian = menu.get("indian");
        continental = menu.get("continental");
        chinese = menu.get("chinese");
        beverages = menu.get("beverages");
        sweets = menu.get("sweets");
        snacks = menu.get("snacks");
    }
    public void print_indian(){
        System.out.println("Indian");
        for(String s : indian){
            items.printItem(s);
        }
    }
    public void print_continental(){
        System.out.println("Continental");
        for(String s : continental){
            items.printItem(s);
        }
    }
    public void print_chinese(){
        System.out.println("Chinese");
        for(String s : chinese){
            items.printItem(s);
        }
    }
    public void print_beverages(){
        System.out.println("Beverages");
        for(String s : beverages){
            items.printItem(s);
        }
    }
    public void print_snacks(){

        System.out.println("Snacks");
        for(String s : snacks){
            items.printItem(s);
        }
    }
    public void print_sweets(){
        System.out.println("Sweets");
        for(String s : sweets){
            items.printItem(s);
        }
    }
    public void printfull() {
        System.out.println("__Full Menu__");
        print_indian();
        print_continental();
        print_chinese();
        print_snacks();
        print_beverages();
        print_sweets();

    }
    public items search(String code) {
        if (canteen.code_to_item.get(code)!=null) {
            return canteen.code_to_item.get(code);
        }
        return null;
    }
}
