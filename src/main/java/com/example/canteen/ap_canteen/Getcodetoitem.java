package com.example.canteen.ap_canteen;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Getcodetoitem {
    public static Map<String,items>getcodetoitems() {
        Map<String,items> codetoitems= null;
        try {
            FileInputStream fileIn = new FileInputStream("codetoitems.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);

            try {
                codetoitems = (HashMap<String,items>) in.readObject();


            } catch (ClassNotFoundException | IOException e) {
//                System.out.println("End of file");

            }
//            for(items item: codetoitems.values()) {
//                System.out.println(item.getCode());
//            }

            return codetoitems.size()==0?null:codetoitems;
        } catch (IOException e) {
            System.err.println("IOException while loading customers: " + e.getMessage());
        }

        return null;
    }
}
