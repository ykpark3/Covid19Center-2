package org.androidtown.covid19center.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClinicDAO {

    @Query("SELECT * FROM clinic")
    List<Clinic> getAll();

    @Query("SELECT * FROM clinic WHERE cid IN (:clinicIds) ")
    List<Clinic> loadAllByIds(int[] clinicIds);

    @Query("SELECT * FROM clinic WHERE clinic_name LIKE :name LIMIT 1")
    Clinic findByName(String name);

    @Query("SELECT * FROM clinic WHERE clinic_address LIKE :address LIMIT 1")
    Clinic findByAddress(String address);

    @Query("SELECT * FROM clinic WHERE clinic_call LIKE :call LIMIT 1")
    Clinic findByCall(String call);

    @Insert
    void insertAll(Clinic... clinics);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertClinics(Clinic... clinics);

    @Insert
    public void insertBothClinics(Clinic clinic1, Clinic clinic2);

    @Delete
    void delete(Clinic clinic);
}

