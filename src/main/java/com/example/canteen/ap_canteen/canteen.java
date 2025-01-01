package com.example.canteen.ap_canteen;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class canteen {
    public static menu m;
    public static Map<String, items> code_to_item = new HashMap<>();
    public static Map<String, customer> id_to_customer = new HashMap<>();
    public static PriorityQueue<order> orders = new PriorityQueue<>(new orderScheduler());
    public static int account = 0;
    public static items popular = null;
    public static int sales = 0;

    public static void addItems(String code, items item) {
        code_to_item.put(code, item);
    }
    public static boolean Logintest(String username, String password) {
        boolean flag = true;
        System.out.println("!!!Welcome to Byte Me!!!");
        System.out.println("1.Customer Login");
        System.out.println("2.Customer Sign-up");
        System.out.println("3.Admin Login");
        System.out.println("4.Exit");
        String choice;
        Scanner sc = new Scanner(System.in);
        choice = "1";
        switch (choice) {
            case "1":

//
                customer c  = Authenticator.authenticator(username,password);


                if (c != null) {
                    System.out.println("Login successful");
                    System.out.println(c.getId());
//                    c.customer_menu();
                  flag = true;

                } else {
                    System.out.println("Invalid credentials.");
//                    main_menu();
                    flag = false;
                }
                break;
//


            case "2":
                System.out.println("Enter username");
                String name = sc.nextLine();
                System.out.println("Enter password");
                String passw = sc.nextLine();
                customer new_customer = new customer(name, passw);
                ObjectSaver.saveObject(new_customer,"customers.ser",true);
                id_to_customer.put(name, new_customer);
                System.out.println("Sign-up successful");
                main_menu();
                break;
            case "3":
                System.out.println("Enter password:");
                String p = sc.nextLine();
                if (p.equals("admin")) {
                    System.out.println("Welcome admin");
                    admin a = new admin("admin@123", "p");
                    a.admin_menu();
                }
                break;
            case "4":
                System.out.println("!!!See you soon!!!");
                System.exit(0);
            case "5":
                for (order o : orders) {
                    System.out.println(o.getId());
                }
                main_menu();
                break;
            default:
//                main_menu();
                break;
        }
    return flag;}
    public static menu initialize(Map<String,ArrayList<String>>menuList){
        menu m = new menu(menuList);
        return m;
    }
    public static void main_menu() {
        System.out.println("!!!Welcome to Byte Me!!!");
        System.out.println("1.Customer Login");
        System.out.println("2.Customer Sign-up");
        System.out.println("3.Admin Login");
        System.out.println("4.Exit");
        String choice;
        Scanner sc = new Scanner(System.in);
        choice = sc.nextLine();
        switch (choice) {
            case "1":
                System.out.println("Enter username");
                String username = sc.nextLine();
                System.out.println("Enter password");
                String pass = sc.nextLine();
//
                    customer c  = Authenticator.authenticator(username,pass);


                    if (c != null) {
                        System.out.println("Login successful");
                        System.out.println(c.getId());
                        c.customer_menu();

                    } else {
                        System.out.println("Invalid credentials.");
                        main_menu();
                    }
                    break;
//


            case "2":
                System.out.println("Enter username");
                String name = sc.nextLine();
                System.out.println("Enter password");
                String passw = sc.nextLine();
                customer new_customer = new customer(name, passw);
                ObjectSaver.saveObject(new_customer,"customers.ser",true);
                id_to_customer.put(name, new_customer);
                System.out.println("Sign-up successful");
                main_menu();
                break;
            case "3":
                System.out.println("Enter password:");
                String p = sc.nextLine();
                if (p.equals("admin")) {
                    System.out.println("Welcome admin");
                    admin a = new admin("admin@123", "p");
                    a.admin_menu();
                }
                break;
            case "4":
                System.out.println("!!!See you soon!!!");
                System.exit(0);
            case "5":
                for (order o : orders) {
                    System.out.println(o.getId());
                }
                main_menu();
                break;
            default:
                main_menu();
                break;
        }
    }

    public static void main(String[] a) throws IOException {
        if(new File("pendingorders.ser").exists()) {
            PriorityQueue<order> previous = GetpendingOrders.getPendingorders();
            if(previous != null) {
                for (order o : previous) {
                    orders.add(o);
                }
            }
        }


        canteen.m = canteen.initialize(GetMenu.getMenu());
        code_to_item = Getcodetoitem.getcodetoitems();
        ArrayList<customer> customers =Loadcustomers.loadCustomers();
        for(customer c : customers) {
            id_to_customer.put(c.getId(), c);
        }
        // Initialize items
//        items i1 = new items("Paneer", 4, "N/A", "Indian", false);
//        items i2 = new items("Burger", 2, "N/A", "Continental", true);
//        items i3 = new items("Noodles", 8, "N/A", "Chinese", false);
//        items i4 = new items("Tea", 5, "N/A", "Beverages", true);
//        items i5 = new items("Samosa", 9, "N/A", "Snacks", false);
//        items i6 = new items("Jalebi", 0, "N/A", "Sweets", true);
//        addItems("Paneer", i1);
//        addItems("Burger", i2);
//        addItems("Noodles", i3);
//        addItems("Tea", i4);
//        addItems("Samosa", i5);
//        addItems("Jalebi", i6);
//        m.getIndian().add(i1.getCode());
//        m.getContinental().add(i2.getCode());
//        m.getChinese().add(i3.getCode());
//        m.getBeverages().add(i4.getCode());
//        m.getSnacks().add(i5.getCode());
//        m.getSweets().add(i6.getCode());
//        customer c1 = new customer("c1", "c1");
//        customer c2 = new customer("c2", "c2");
//        customer c3 = new customer("c3", "c3");
//        c1.setVip(true);
//        c2.setVip(false);
//        c3.setVip(false);
//        ObjectSaver.saveObject(c1,"customers.ser",true);
//        ObjectSaver.saveObject(c2,"customers.ser",true);
//        ObjectSaver.saveObject(c3,"customers.ser",true);

//        id_to_customer.put("c2", c2);
//        id_to_customer.put("c3", c3);
//        ObjectSaver.saveObject(canteen.m.getMenu(),"menu.ser");
//        ObjectSaver.saveObject(code_to_item,"codetoitems.ser");
        // Launch JavaFX screen
        launchJavaFXScreen();

        // After JavaFX, return to main menu
        main_menu();
    }

    private static void launchJavaFXScreen() {
        new Thread(() -> Application.launch(HelloApplication.class)).start();

        try {
            // Wait briefly to ensure the JavaFX application loads
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
