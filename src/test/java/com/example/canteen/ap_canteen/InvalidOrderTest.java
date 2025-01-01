package com.example.canteen.ap_canteen;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
public class InvalidOrderTest {
   customer c =  new customer("","");
    @Test
    public void itemUnavailable() {
        Scanner sc = new Scanner(System.in);
        assertEquals("Sorry,item is not available",c.placeOrderTest("Paneer","N",sc));
    }
    @Test
    public void itemnotinmenu() {
        Scanner sc = new Scanner(System.in);
        assertEquals("Sorry,item is not available",c.placeOrderTest("Unknown","N",sc));
    }
}