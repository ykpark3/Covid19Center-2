package org.androidtown.covid19center.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "clinic")
public class Clinic {
    @PrimaryKey(autoGenerate = true)
    private int cid; // 병원 id

    @ColumnInfo(name = "clinic_name")
    private String clinicName; // 병원 명
    @ColumnInfo(name = "clinic_address")
    private String clinicAddress; // 병원 주소
    @ColumnInfo(name = "clinic_call")
    private String clinicCallNumber; // 병원 전화번호

    public String getClinicName(){
        return clinicName;
    }

    public String getClinicCallNumber(){
        return clinicCallNumber;
    }

    public String getClinicAddress() {
        return clinicAddress;
    }

    public int getCid(){
        return cid;
    }

    public Clinic(String clinicName, String clinicAddress, String clinicCallNumber){
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.clinicCallNumber = clinicCallNumber;
    }

    public Clinic() {
    }

    @Override
    public String toString() {
        return "Clinic{" +
                "cid=" + String.valueOf(cid) +
                ", clinicName='" + clinicName + '\'' +
                ", clinicAddress='" + clinicAddress + '\'' +
                ", clinicCallNumber='" + clinicCallNumber + '\'' +
                '}';
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public void setClinicAddress(String clinicAddress) {
        this.clinicAddress = clinicAddress;
    }

    public void setClinicCallNumber(String clinicCallNumber) {
        this.clinicCallNumber = clinicCallNumber;
    }
}
