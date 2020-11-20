package org.androidtown.covid19center.Search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
    private ArrayList<String> copyListView;
    private ArrayList<View> views;
    private View tmpView;
    private MyAdapter adapter;
    private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        views = new ArrayList<>();

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
        itemList.add("09:00");
        itemList.add("10:00");
        itemList.add("11:00");
        itemList.add("12:00");
        itemList.add("13:00");
        itemList.add("14:00");
        itemList.add("15:00");
        itemList.add("16:00");
        itemList.add("17:00");
        itemList.add("18:00");

        adapter = new MyAdapter(this, itemList, onClickItem);
        listView.setAdapter(adapter);

        TimeListDecoration decoration = new TimeListDecoration();
        listView.addItemDecoration(decoration);
    }

    private View.OnClickListener onClickItem = new View.OnClickListener(){


        @Override
        public void onClick(View v) {

            int str = v.hashCode();

            if(mSelectedItems.get(str,false)){
                mSelectedItems.put(str, false);
                Log.d("0131", String.valueOf(str));
                v.setBackgroundResource(R.drawable.round_button_background);
            } else {
                mSelectedItems.put(str, true);

                views.add(v);

//                for (int i=0; i<views.size(); i++){
//                    views.get(i).setBackgroundResource(R.drawable.round_button_background);
//                }

                views.get(0).setBackgroundResource(R.drawable.round_button_background);

                if(views.size() > 1){
                    views.remove(0);
                }
                Log.d("0131", String.valueOf(views.size()));
                v.setBackgroundResource(R.drawable.on_round_button_background);
            }
            mSelectedItems.clear();
        }
    };

}
