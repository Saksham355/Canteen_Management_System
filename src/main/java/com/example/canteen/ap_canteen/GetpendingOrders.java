package com.example.canteen.ap_canteen;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class GetpendingOrders {
    public static void removePendingOrders(String id) {
        PriorityQueue<order> pendingorders = null;
        try {
            FileInputStream fileIn = new FileInputStream("pendingorders.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
                try {
                  pendingorders  = (PriorityQueue<order>) in.readObject();

                } catch (ClassNotFoundException | IOException e) {

                }


        } catch (IOException e) {
            System.err.println("IOException while loading customers: " + e.getMessage());
        }

        for(order ord : pendingorders) {
            if(ord.getId().equals(id)) {
                pendingorders.remove(ord);
            }
        }
        ObjectSaver.saveObject(pendingorders, "pendingorders.ser");

    }
    public static PriorityQueue<order> getPendingorders() {
        PriorityQueue<order> pendingorders = null;
        try {
            FileInputStream fileIn = new FileInputStream("pendingorders.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

                try {
                    pendingorders = (PriorityQueue<order>) in.readObject();
                    return (pendingorders.size()==0)?null:pendingorders;

                } catch (ClassNotFoundException | IOException e) {
                    System.out.println("End of file"); // End of file or error
                }


        } catch (IOException e) {
            System.err.println("IOException while loading customers: " + e.getMessage());
        }
    return null;}
}
