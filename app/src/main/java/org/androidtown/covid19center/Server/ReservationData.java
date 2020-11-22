package org.androidtown.covid19center.Server;

import com.google.gson.annotations.SerializedName;

public class ReservationData {

    @SerializedName("user_id")
    String user_id;
    @SerializedName("questionnaire_seq")
    int password;
    @SerializedName("hospital_name")
    String hospital_name;
    @SerializedName("time")
    String time;
    @SerializedName("date")
    String date;


    public String getUser_id() {return user_id;}
    public void setUser_id(String id) {
        this.user_id = user_id;
    }

    public int getPassword() {return password;}
    public void setPassword(int password) {this.password = password;}

    public String getHospital_name() {return hospital_name;}
    public void setHospital_name(String hospital_name) {this.hospital_name = hospital_name;}

    public String getTime() {return time;}
    public void setTime(String time) {this.time = time;}

    public String getDate() {return hospital_name;}
    public void setDate(String date) {this.date = date;}

}
