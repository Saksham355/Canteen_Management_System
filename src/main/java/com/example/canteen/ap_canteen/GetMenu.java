package com.example.canteen.ap_canteen;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetMenu {

    public static Map<String,ArrayList<String>> getMenu()  {
        Map<String,ArrayList<String>> menu = null;
        try {
            FileInputStream fileIn = new FileInputStream("menu.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

                try {
                    menu = (Map<String,ArrayList<String>>) in.readObject();


                } catch (ClassNotFoundException | IOException e) {
                    System.out.println("File ended");

                }


            return menu.size()==0?null:menu;
        } catch (IOException e) {
            System.err.println("IOException while loading customers: " + e.getMessage());
        }

        return menu;
        }
}
