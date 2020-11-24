package org.androidtown.covid19center.Search;

import android.app.Dialog;

import android.content.Context;

import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.TextView;

import androidx.annotation.NonNull;

import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Server.AppManager;
import org.androidtown.covid19center.Server.ReservationData;
import org.androidtown.covid19center.Server.RetrofitClient;
import org.androidtown.covid19center.Server.ServiceApi;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EndDialog extends Dialog implements View.OnClickListener {

    private Context mContext;

    private String timeMessage;
    private String clinicMessage;
    private TextView textView_clinic_message;
    private TextView btn_cancel;
    private TextView textView_message;
    private TextView btn_ok;

    private ServiceApi serviceApi;

    public EndDialog(@NonNull Context context, String time, String clinic) {

        super(context);

        mContext = context;

        timeMessage = time;

        clinicMessage = clinic;

        serviceApi = RetrofitClient.getClient().create(ServiceApi.class);

    }



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.option_codetype_dialog);



        btn_cancel = (TextView) findViewById(R.id.btn_cancel);
        btn_ok = (TextView) findViewById(R.id.btn_ok);
        textView_message = (TextView) findViewById(R.id.message_textView);
        textView_clinic_message = (TextView) findViewById(R.id.message_clinic_textView);

        textView_message.setText("예약 일자 : " + timeMessage);
        textView_clinic_message.setText("진료소 : " + clinicMessage);
        btn_cancel.setOnClickListener(this);

        btn_ok.setOnClickListener(this);

    }





    @Override

    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_cancel:

                dismiss();

                break;



            case R.id.btn_ok:

                Log.d("~~~~~", "예약하기 버튼 누름");
                Log.d("~~~~~","clinic message: "+clinicMessage);
                Log.d("~~~~~","time message: "+timeMessage);

                //sendReservationData(new ReservationData(AppManager.getInstance().getUserId(), 111, "hospital", "time","date",false));
                sendReservationData(new ReservationData("userid",111,"hospital","time","date",false));

                dismiss();

                break;

        }

    }

    private void sendReservationData(ReservationData reservationData) {
        Log.d("~~~~~","sendReservationData");

        Call<ResponseBody> dataCall = serviceApi.sendReservationData(reservationData);
        dataCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String result = null;
                try {
                    Log.d("~~~~~","response"+response.code());
                    result = response.body().string();

                } catch (IOException e) {
                    Log.d("~~~~~", String.valueOf(e));
                    e.printStackTrace();
                }
                Log.i("~~~~~", result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("~~~~~","fail");
                if (t instanceof IOException) {
                    // Handle IO exception, maybe check the network and try again.
                    Log.i("~~~~~","t"+t);
                }
            }
        });
    }

}
