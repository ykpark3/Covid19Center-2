package org.androidtown.covid19center.Server;

import com.google.gson.annotations.SerializedName;

public class UsersVO {

   // @SerializedName("id")
    String id;
   // @SerializedName("password")
    String password;



    public String getId() {return id;}
    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    /*
    public UsersData(String id, String password) {

        this.id = id;
        this.password = password;
    }

     */
}
