package org.androidtown.covid19center.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "clinics")
public class Clinic {
    @PrimaryKey
    public int cid; // 병원 id

    @ColumnInfo(name = "clinic_name")
    public String clinicName; // 병원 명

    @ColumnInfo(name = "clinic_address")
    public String clinicAddress; // 병원 주소

    @ColumnInfo(name = "clinic_call")
    public String clinicCallNumber; // 병원 전화번호
}
