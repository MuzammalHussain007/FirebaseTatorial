package com.example.firebasetatorial.Modal;

public class User {
    private String firstname,lastname,email,password,picture,phone;

    public User()
    {

    }

    public User(String firstname, String lastname, String email, String password, String picture,String phone) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.phone=phone;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPicture() {
        return picture;
    }
}
