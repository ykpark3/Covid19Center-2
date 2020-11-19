package org.androidtown.covid19center.Hospital;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Server.ReservationVO;

import java.util.ArrayList;
import java.util.List;

//의료진용 예약 관리 페이지
public class ReservationManagementActivity extends AppCompatActivity {

    AppCompatActivity activity = this;

    //임시 리스트
    ArrayList<ReservationVO> reservationList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //임시 예약 환자 리스트 생성
        initList();

        setContentView(R.layout.activity_reservation_management);

        CalendarView calendarView = findViewById(R.id.hospital_calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                month += 1;

                Toast.makeText(activity, year + "." + month + "." + dayOfMonth, Toast.LENGTH_SHORT).show();
            }
        });

        ListView listView = findViewById(R.id.reservation_listview);
        final ListAdapter listAdapter = new ListAdapter(this,reservationList);

        listView.setAdapter(listAdapter);
    }

    public void initList(){
        reservationList = new ArrayList<ReservationVO>();

        reservationList.add(0, new ReservationVO("user1", 1, "hospital_1", "11:30", "11.19"));
        reservationList.add(1, new ReservationVO("user2", 2, "hospital_2", "11:30", "11.19"));
        reservationList.add(2, new ReservationVO("user3", 3, "hospital_3", "11:30", "11.19"));
    }
}
