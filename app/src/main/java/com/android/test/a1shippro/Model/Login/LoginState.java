package com.android.test.a1shippro.Model.Login;

/**
 * Created by NamNgo on 25/05/2016.
 */
public class LoginState extends Login {
    private String state;

    public LoginState() {
    }

    public LoginState(String username, String password, String state) {
        super(username, password);
        setState(state);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
