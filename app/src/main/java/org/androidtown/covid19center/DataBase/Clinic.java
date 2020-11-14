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
<<<<<<< HEAD
    @ColumnInfo(name = "clinic_x")
    private String x; // 병원 x좌표
    @ColumnInfo(name = "clinic_y")
    private String y; // 병원 y좌표
=======
>>>>>>> 24cf7a1044cc358d297d13b496581957c0c86d0e

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

<<<<<<< HEAD
    public Clinic(String clinicName, String clinicAddress, String clinicCallNumber, String x, String y){
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.clinicCallNumber = clinicCallNumber;
        this.x = x;
        this.y = y;
=======
    public Clinic(String clinicName, String clinicAddress, String clinicCallNumber){
        this.clinicName = clinicName;
        this.clinicAddress = clinicAddress;
        this.clinicCallNumber = clinicCallNumber;
>>>>>>> 24cf7a1044cc358d297d13b496581957c0c86d0e
    }

    public Clinic() {
    }

<<<<<<< HEAD
    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

=======
>>>>>>> 24cf7a1044cc358d297d13b496581957c0c86d0e
    @Override
    public String toString() {
        return "Clinic{" +
                "cid=" + String.valueOf(cid) +
                ", clinicName='" + clinicName + '\'' +
                ", clinicAddress='" + clinicAddress + '\'' +
                ", clinicCallNumber='" + clinicCallNumber + '\'' +
<<<<<<< HEAD
                ", x='" + x + '\'' +
                ", y='" + y + '\'' +
=======
>>>>>>> 24cf7a1044cc358d297d13b496581957c0c86d0e
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
