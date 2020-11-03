package org.androidtown.covid19center.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "clinics_table")
public class Clinic {
    @PrimaryKey
    public int cid; // 병원 id
    public String clinicName; // 병원 명
    public String clinicAddress; // 병원 주소
    public String clinicCallNumber; // 병원 전화번호
}
