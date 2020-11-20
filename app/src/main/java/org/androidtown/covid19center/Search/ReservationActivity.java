package org.androidtown.covid19center.Search;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.datepicker.MaterialCalendar;

import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Search.Time.MyAdapter;
import org.androidtown.covid19center.Search.Time.TimeListDecoration;

import java.util.ArrayList;

public class ReservationActivity extends AppCompatActivity {

    private TextView clinicName;
    private TextView clinicDate;
    private CalendarView calendarView;
    private String stringTemp;
    private RecyclerView listView;
    private MyAdapter adapter;

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
        setTimeListView();
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

    private void setTimeListView(){
        listView = findViewById(R.id.time_listView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listView.setLayoutManager(layoutManager);

        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("08:00");
        itemList.add("10:00");
        itemList.add("12:00");
        itemList.add("14:00");
        itemList.add("16:00");
        itemList.add("18:00");
        itemList.add("20:00");

        adapter = new MyAdapter(this, itemList, onClickItem);
        listView.setAdapter(adapter);

        TimeListDecoration decoration = new TimeListDecoration();
        listView.addItemDecoration(decoration);
    }

    private View.OnClickListener onClickItem = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();
        }
    };

}
