package com.example.canteen.ap_canteen;

import java.io.Serializable;

public class user implements Serializable {
    private String id;
    private String password;
    public user(){
        this.id = "";
        this.password = "";
    }
    public user(String id, String password) {
        this.id = id;
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public String getId() {
        return id;
    }
}
