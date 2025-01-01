package com.example.canteen.ap_canteen;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.EOFException;
import java.util.ArrayList;

public class Loadcustomers {
    public static ArrayList<customer> loadCustomers() {
        ArrayList<customer> customers = new ArrayList<customer>();
        try (FileInputStream fileIn = new FileInputStream("customers.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            while (true) {
                try {
                    customer c = (customer) in.readObject();
                    customers.add(c);
                } catch (EOFException e) {
                    break;
                }
            }
        }catch(Exception e){
                e.printStackTrace();
            }


        return customers;
    }

}
