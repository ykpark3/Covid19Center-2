package org.androidtown.covid19center.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClinicDAO {

    @Insert
    void insert(Clinic clinic);

}

