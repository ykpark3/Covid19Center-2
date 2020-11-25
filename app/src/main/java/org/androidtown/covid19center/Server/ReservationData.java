package org.androidtown.covid19center.Server;

import com.google.gson.annotations.SerializedName;

public class ReservationData {

    @SerializedName("user_id")
    String user_id;
    @SerializedName("questionnaire_seq")
    int questionnaire_seq;
    @SerializedName("hospital_name")
    String hospital_name;
    @SerializedName("time")
    String time;
    @SerializedName("date")
    String date;
    @SerializedName("visited")
    boolean visited;
    @SerializedName("completion_time")
    String completion_time;


    public ReservationData(String user_id, int questionnaire_seq, String hospital_name, String time, String date, boolean visited, String completion_time) {
        this.user_id = user_id;
        this.questionnaire_seq = questionnaire_seq;
        this.hospital_name = hospital_name;
        this.time = time;
        this.date = date;
        this.visited = visited;
        this.completion_time = completion_time;
    }


    public String getUser_id() {return user_id;}
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getQuestionnaire_seq() {return questionnaire_seq;}
    public void setQuestionnaire_seq(int questionnaire_seq) {this.questionnaire_seq = questionnaire_seq;}

    public String getHospital_name() {return hospital_name;}
    public void setHospital_name(String hospital_name) {this.hospital_name = hospital_name;}

    public String getTime() {return time;}
    public void setTime(String time) {this.time = time;}

    public String getDate() {return hospital_name;}
    public void setDate(String date) {this.date = date;}

    public boolean getVisited() {return visited;}
    public void setVisited(boolean visited) {this.visited = visited;}

    public String getCompletion_time() {return completion_time;}
    public void setCompletion_time(String completion_time) {this.completion_time = completion_time;}
}
