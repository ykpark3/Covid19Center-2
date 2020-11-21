package org.androidtown.covid19center.Search;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

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
import org.androidtown.covid19center.Search.List.ClinicItem;
import org.androidtown.covid19center.Search.Time.MyAdapter;
import org.androidtown.covid19center.Search.Time.SubTimeAdapter;
import org.androidtown.covid19center.Search.Time.SubTimeItem;
import org.androidtown.covid19center.Search.Time.SubTimeItemView;
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
    private ArrayList<View> timeViews;
    private View tmpView;
    private MyAdapter adapter;
    private SubTimeAdapter subTimeAdapter;
    private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);
    private SparseBooleanArray mSelectedTimeItems = new SparseBooleanArray(0);
    private ArrayList<String> listTime;
    private StringBuffer timeStringBuffer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        views = new ArrayList<>();
        timeViews = new ArrayList<>();
        setLayoutElement();
        setIntentInfomation();
        subTimeAdapter = new SubTimeAdapter(getApplicationContext());
        timeStringBuffer = new StringBuffer();
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
                setTimeListView();
            }
        });
    }

    private void setIntentInfomation() {
        Intent intent = getIntent(); // 데이터 수신

        stringTemp = intent.getExtras().getString("clinicName");
        clinicName.setText(stringTemp);

        listView = findViewById(R.id.time_listView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listView.setLayoutManager(layoutManager);

        TimeListDecoration decoration = new TimeListDecoration();
        listView.addItemDecoration(decoration);
    }

    private void setTimeListView(){

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
    }

    private View.OnClickListener onClickItem = new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            int str = v.hashCode();

            String time = String.valueOf(v.getTag());

            int passTime;
            time = time.replace(":", "");

            if(time.substring(0,1).contains("0")){
                time = time.substring(1,4);
                passTime = Integer.parseInt(time.substring(0,1));
            } else{
                passTime = Integer.parseInt(time.substring(0,2));
            }


            if(mSelectedItems.get(str,false)){
                mSelectedItems.put(str, false);
                Log.d("0131", String.valueOf(str));
                v.setBackgroundResource(R.drawable.round_button_background);
            } else {
                mSelectedItems.put(str, true);
                views.add(v);

                views.get(0).setBackgroundResource(R.drawable.round_button_background);

                if(views.size() > 1){
                    views.remove(0);
                }
                v.setBackgroundResource(R.drawable.on_round_button_background);
            }

            mSelectedItems.clear();

            setGridView(passTime);
        }
    };

    private void setGridView(int time){
        GridView gridView = (GridView) findViewById(R.id.reservation_gridView);
        listTime = new ArrayList<>();
        listTime.clear();

        setListTime(time, listTime);

        gridView.setAdapter(subTimeAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundResource(R.color.colorMain);
                timeViews.add(view);

                if(timeViews.size() > 1){
                    timeViews.get(0).setBackgroundResource(R.color.colorLightGray);
                    timeViews.clear();
                }
            }
        });
    }

    private void setListTime(int num, ArrayList<String> stringTime){

        int[] plusTime = new int[]{0,15,30,45};
        int time = num * 100;
        String timeString = null;


        subTimeAdapter.clearItem();

        for(int i=0; i<plusTime.length; i++) {

            timeString = String.valueOf(time + plusTime[i]);
            timeStringBuffer.append(timeString);

            if(Integer.parseInt(timeString) < 1000){
                timeStringBuffer.insert(0,"0");
            }

            timeStringBuffer.insert(2, ":");

            subTimeAdapter.addItem(new SubTimeItem(String.valueOf(timeStringBuffer)));

            stringTime.add(timeString);
            timeStringBuffer.delete(0,5);
        }
    }
}