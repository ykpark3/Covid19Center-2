package org.androidtown.covid19center.DataBase;

import android.content.Context;

        import androidx.room.Database;
        import androidx.room.Room;
        import androidx.room.RoomDatabase;

@Database(entities = {Clinic.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ClinicDao clinicDao();


}
