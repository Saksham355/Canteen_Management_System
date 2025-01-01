package com.example.canteen.ap_canteen;
import java.io.*;
import java.util.ArrayList;

public class getPastOrders {
    public static ArrayList<order> getPastOrders(String id) {
        ArrayList<order> orders = new ArrayList<>();
        try {FileInputStream fileIn = new FileInputStream("pastorders.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn);
            while (true) {
                try {
                    order ord = (order) in.readObject();


                    if (ord.getId().equals(id)) {
                        orders.add(ord);

                    }

                } catch (ClassNotFoundException | IOException e) {

                    break;
                }
            }
            return orders.size()==0?null:orders;
        } catch (IOException e) {
            System.err.println("IOException while loading customers: " + e.getMessage());
        }

        return null;}

    }

