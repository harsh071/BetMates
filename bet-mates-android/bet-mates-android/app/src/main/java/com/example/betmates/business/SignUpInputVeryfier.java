package com.example.betmates.business;

public class SignUpInputVeryfier {
    String userName;
    String password;
    String age;
    String confirmPassword;
    public SignUpInputVeryfier(String userName, String password, String age, String confirmPassword)
    {
        this.userName = userName;
        this.password = password;
        this.age = age;
        this.confirmPassword = confirmPassword;
    }

    public boolean isInputEmpty()
    {
        return (userName.isEmpty()||password.isEmpty()||age.isEmpty()||confirmPassword.isEmpty());
    }
}
