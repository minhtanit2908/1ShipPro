package com.android.test.a1shippro.Model.Login;

/**
 * Created by NamNgo on 25/05/2016.
 */
public class Login {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Login(String username, String password) {
        setUsername(username);
        setPassword(password);
    }

    public Login() {
    }

    @Override
    public String toString() {
        return username;
    }
}//END
