package com.example.canteen.ap_canteen;

import java.util.ArrayList;
import java.util.Scanner;

public class cart {
    private ArrayList <items>cartItems;
    private order order;
    public cart(order order) {
        this.cartItems = new ArrayList<items>();
        cartItems.addAll(order.getOrderList());
        this.order = order;

    }
    public ArrayList<items> getCartItems() {
        return cartItems;
    }
    public void remove(customer c,String item){
        c.getMy_order().getOrderList().remove(canteen.code_to_item.get(item));
    }
    public void cart_menu(customer c){
        ArrayList<items> cart_items = c.getCart().getCartItems();
        System.out.println("My Cart:");
        int total = 0;
        int index = 1;
        if(cart_items.size()==0) {
            System.out.println("Cart empty!");
            c.customer_menu();
        }
        for (items item : cart_items) {
            System.out.println(index + ". " + item.getCode() + ",Qty:" + item.getQuantity() + ",Price:" + item.getPrice() + ",Sub-total:" + item.getPrice() * item.getQuantity()+",add-ons:"+item.getAddons());
            total += item.getQuantity() * item.getPrice();
            index++;
        }
        System.out.println("Total Price:" + total);
        System.out.println("Select from the following options");
        System.out.println("1. Modify Quantities");
        System.out.println("2. Remove items");
        System.out.println("3. checkout");
        System.out.println("4. Exit");
        int choice;
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        switch(choice) {
            case 1:
                System.out.println("Enter item:");
                String item = sc.next();
                if(!c.getMy_order().getOrderList().contains(canteen.code_to_item.get(item))){
                    System.out.println("Item not found in your order!");
                    cart_menu(c);
                    return;
                }
                System.out.println("Enter new quantity:");
                int q = sc.nextInt();
                canteen.code_to_item.get(item).setQuantity(q);
                cart_menu(c);
                break;
            case 2:
                System.out.println("Enter item:");
                String item2 = sc.next();
                if(c.getMy_order().getOrderList().contains(canteen.code_to_item.get(item2))){
                remove(c, item2);
                cartItems.remove(canteen.code_to_item.get(item2));
                System.out.println("Item removed!");
                cart_menu(c);
            }
                else {
                    System.out.println("Item not found in your order!");
                }
                break;
            case 3:
                System.out.println("Enter Address:");
                String address = sc.next();
                System.out.println("Enter Payment method:");
                String method = sc.next();
                canteen.account+=this.order.getTotal();
                System.out.println("Checked out successfully!");

                canteen.orders.add(this.order);
                ObjectSaver.saveObject(canteen.orders,"pendingorders.ser");
                cartItems.clear();
                break;
            case 4:
                break;
        }

    }
}
