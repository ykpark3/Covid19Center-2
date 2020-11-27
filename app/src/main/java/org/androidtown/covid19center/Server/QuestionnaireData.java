package org.androidtown.covid19center.Server;

import com.google.gson.annotations.SerializedName;

public class QuestionnaireData {

    @SerializedName("user_id")
    String user_id;
    @SerializedName("visited")
    boolean visited;
    @SerializedName("visited_detail")
    String visited_detail;
    @SerializedName("entrance_date")
    String entrance_date;
    @SerializedName("contact")
    boolean contact;
    @SerializedName("contact_relationship")
    String contact_relationship;
    @SerializedName("contact_period")
    String contact_period;
    @SerializedName("fever")
    boolean fever;
    @SerializedName("muscle_ache")
    boolean muscle_ache;
    @SerializedName("sputum")
    boolean sputum;
    @SerializedName("runny_nose")
    boolean runny_nose;
    @SerializedName("dyspnea")
    boolean dyspnea;
    @SerializedName("sore_throat")
    boolean sore_throat;
    @SerializedName("symptom_start_date")
    String symptom_start_date;
    @SerializedName("toDoctor")
    String toDoctor;


    public QuestionnaireData(String user_id,
                             boolean visited,
                             String visited_detail,
                             String entrance_date,
                             boolean contact,
                             String contact_relationship,
                             String contact_period,
                             boolean fever,
                             boolean muscle_ache,
                             boolean sputum,
                             boolean runny_nose,
                             boolean dyspnea,
                             boolean sore_throat,
                             String symptom_start_date,
                             String toDoctor) {

        //this.sequence = sequence;
        this.user_id = user_id;
        this.visited = visited;
        this.visited_detail = visited_detail;
        this.entrance_date = entrance_date;
        this.contact = contact;
        this.contact_relationship = contact_relationship;
        this.contact_period = contact_period;
        this.fever = fever;
        this.muscle_ache = muscle_ache;
        this.sputum = sputum;
        this.runny_nose = runny_nose;
        this.dyspnea = dyspnea;
        this.sore_throat = sore_throat;
        this.symptom_start_date = symptom_start_date;
        this.toDoctor = toDoctor;
    }


    public String getUser_id() {return user_id;}
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public boolean getVisited() {return visited;}
    public void setVisited(boolean visited) {this.visited = visited;}

    public String getVisited_detail() {return visited_detail;}
    public void setVisited_detail(String visited_detail) {this.visited_detail = visited_detail;}

    public String getEntrance_date() {return entrance_date;}
    public void setEntrance_date(String entrance_date) {
        this.entrance_date = entrance_date;
    }

    public boolean getContact() {return contact;}
    public void setContact(boolean contact) {this.contact = contact;}

    public String getContact_relationship() {return contact_relationship;}
    public void setContact_relationship(String contact_relationship) {this.contact_relationship = contact_relationship;}

    public String getContact_period() {return contact_period;}
    public void setContact_period(String contact_period) {this.contact_period = contact_period;}

    public boolean getFever() {return fever;}
    public void setFever(boolean fever) {this.fever = fever;}

    public boolean getMuscle_ache() {return muscle_ache;}
    public void setMuscle_ache(boolean muscle_ache) {this.muscle_ache = muscle_ache;}

    public boolean getSputum() {return sputum;}
    public void setSputum(boolean sputum) {this.sputum = sputum;}

    public boolean getRunny_nose() {return runny_nose;}
    public void setRunny_nose(boolean runny_nose) {this.runny_nose = runny_nose;}

    public boolean getDyspnea() {return dyspnea;}
    public void setDyspnea(boolean dyspnea) {this.dyspnea = dyspnea;}

    public boolean getSore_throat() {return sore_throat;}
    public void setSore_throat(boolean sore_throat) {this.sore_throat = sore_throat;}

    public String getSymptom_start_date() {return symptom_start_date;}
    public void setSymptom_start_date(String symptom_start_date) {
        this.symptom_start_date = symptom_start_date;
    }

    public String getToDoctor() {return toDoctor;}
    public void setToDoctor(String toDoctor) {this.toDoctor = toDoctor;}

}
