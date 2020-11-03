package org.androidtown.covid19center.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClinicDao {

    @Query("SELECT * FROM Clinic")
    LiveData<List<Clinic>> getAll();

    @Insert
    void insert(Clinic clinic);

    @Update
    void update(Clinic clinic);

    @Delete
    void delete(Clinic clinic);
}

