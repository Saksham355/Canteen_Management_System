package com.example.canteen.ap_canteen;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;
public class InvalidLoginTest {

    @Test
    public void logintest() {
        assertFalse(canteen.Logintest("wrongusername","wrongpassword"));
    }
}