package com.example.canteen.ap_canteen;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.EOFException;

public class Authenticator {
    public static customer authenticator (String id,String password){
        try (FileInputStream fileIn = new FileInputStream("customers.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn)) {

            while (true) {
                try {
                    customer c = (customer) in.readObject();
                    if(c.getId().equals(id) && c.getPassword().equals(password)){
                       return c;
                    }
                    else{
                        c=null;
                    }

                }
                catch (NullPointerException e){
                    return null;
                }
                catch (EOFException e) {

                    break;
                } catch (Exception e) {

                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    return null;}
}
