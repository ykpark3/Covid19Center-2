package org.androidtown.covid19center.Search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.MaterialCalendar;

import org.androidtown.covid19center.R;

public class ReservationActivity extends AppCompatActivity {

    private TextView clinicName;
    private TextView clinicDate;
    private CalendarView calendarView;
    private String stringTemp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayoutElement();
        setIntentInfomation();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void setLayoutElement(){
        setContentView(R.layout.activity_reservation);
        clinicName = findViewById(R.id.reservation_clinicName);
        calendarView = findViewById(R.id.reservation_calendarView);
        clinicDate = findViewById(R.id.reservation_date_text);
        setCalenderView();
    }

    private void setCalenderView(){

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;
                clinicDate.setText(String.format("%d년 %d월 %d일", year, month, dayOfMonth));
            }
        });
    }

    private void setIntentInfomation() {
        Intent intent = getIntent(); // 데이터 수신

        stringTemp = intent.getExtras().getString("clinicName");
        clinicName.setText(stringTemp);
    }


}
