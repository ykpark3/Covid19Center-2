package org.androidtown.covid19center.Hospital;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.R;

//의료진용 예약 관리 페이지
public class ActivityReservationManagement extends AppCompatActivity {

    AppCompatActivity activity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reservation_management);

        CalendarView calendarView = findViewById(R.id.hospital_calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;

                Toast.makeText(activity, year + "." + month + "." + dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
