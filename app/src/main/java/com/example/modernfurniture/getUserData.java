package com.example.modernfurniture;

public class getUserData {


    //private String image;
    private String email,fName,phone;

    public getUserData() {
    }

    public getUserData(String email, String fName, String phone) {
        this.email = email;
        this.fName = fName;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public String getfName() {
        return fName;
    }

    public String getPhone() {
        return phone;
    }

    /*private String fName,email,phone;

    public getUserData() {}


    public getUserData(String Uname, String Uemail, String Uphone){
        fName = Uname;
        //image = Cimage;
        email = Uemail;
        phone = Uphone;

    }


    public String getName() {
        return fName;
    }

    public String getemail() {
        return email;
    }

    public String getphone() {
        return phone;
    }*/

    /*public String getImage() {
        return image;
    }*/
}
