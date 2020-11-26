package org.androidtown.covid19center.Server;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarActivity extends AppCompatActivity {

    private ServiceApi serviceApi;
    private ArrayList<ReservationVO> reservationVOArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        Log.d("~~~~~","calendarActivity oncreate");

        serviceApi = RetrofitClient.getClient().create(ServiceApi.class);

        getReservationList();
    }

    protected void getReservationList() {
        Log.d("~~~~~"," getReservationList");

         serviceApi.getReservationVO().enqueue(new Callback<List<ReservationVO>>() {
            @Override
            public void onResponse(Call<List<ReservationVO>> call, Response<List<ReservationVO>> response) {

                reservationVOArrayList = AppManager.getInstance().getReservationVOArrayList();
                reservationVOArrayList.clear();

                String user_id;
                int questionnaire_seq;
                String hospital_name;
                String time;
                String date;

                if(response.isSuccessful()) {
                    List<ReservationVO> data = response.body();

                    Log.d("~~~~~", "성공");

                    for(int i=0; i<data.size(); i++) {
                        user_id = data.get(i).getUser_id();
                        questionnaire_seq = data.get(i).getQuestionnaire_seq();
                        hospital_name = data.get(i).getHospital_name();
                        time = data.get(i).getTime();
                        date = data.get(i).getDate();

                        ReservationVO reservationVO = new ReservationVO(user_id,questionnaire_seq,hospital_name,time,date);
                        reservationVOArrayList.add(reservationVO);

                    }

                    AppManager.getInstance().setReservationVOArrayList(reservationVOArrayList);

//                    Log.d("~~~~~","0번째 userid:  " + reservationVOArrayList.get(0).user_id);
//                    Log.d("~~~~~","1번째 userid:  " + reservationVOArrayList.get(1).user_id);
//                    Log.d("~~~~~","list size:"+reservationVOArrayList.size());

                }
            }

            @Override
            public void onFailure(Call<List<ReservationVO>> call, Throwable t) {
                Log.d("~~~~~","실패: "+ t);
                t.printStackTrace();
            }
        });

    }
}