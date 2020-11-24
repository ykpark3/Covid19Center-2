package org.androidtown.covid19center.SelfCheck;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Server.QuestionnaireData;
import org.androidtown.covid19center.Server.ReservationData;
import org.androidtown.covid19center.Server.RetrofitClient;
import org.androidtown.covid19center.Server.ServiceApi;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionnaireActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private EditText countryEditText;
    private TextView entranceDateTextView;
    private EditText relationEditText;
    private TextView termTextView;
    private TextView startVirusDateTextView;
    private final Calendar myCalender = Calendar.getInstance();
    private String clinicName;
    private String clinicReservationTime;
    private String symptom_start_date;
    private String visitedDetail;
    private String contact_relationship;
    private String contact_period;
    private String entrance_date;
    private ImageButton backButton;
    private Button nextButton;
    private RadioGroup visitedCheck_radioGroup, contact_radioGroup;
    private Boolean isVisited, isContacted, hasFever, hasMuscle_ache, hasCough, hasSputum, hasRunnyNose, hasDyspnea, hasSoreThroat;
    private CheckBox fever_checkBox, muscle_ache_checkBox, cough_checkBox, sputum_checkBox, runny_nose_checkBox, dyspnea_checkBox, sore_throat_checkBox;

    private ServiceApi serviceApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("~~~~~","questionnaire activity oncreate");

        serviceApi = RetrofitClient.getClient().create(ServiceApi.class);

        setContentView(R.layout.activity_questionnaire);
        setLayoutElement();
        setIntentInfo();
    }

    private void setLayoutElement(){
        countryEditText = findViewById(R.id.questionnarie_editText_country);
        entranceDateTextView = findViewById(R.id.questionnarie_textView_date);
        relationEditText = findViewById(R.id.questionnarie_editText_relation);
        termTextView = findViewById(R.id.questionnarie_textView_term);
        startVirusDateTextView = findViewById(R.id.questionnarie_textView_start_date);
        nextButton = findViewById(R.id.questionnarie_button);
        backButton = findViewById(R.id.questionnarie_backButton);
        contact_radioGroup = findViewById(R.id.questionnarie_contact_radioGroup);
        contact_radioGroup.setOnCheckedChangeListener(contactRadioGroupButtonChangeListener);
        visitedCheck_radioGroup = findViewById(R.id.questionnarie_visited_radioGroup);
        visitedCheck_radioGroup.setOnCheckedChangeListener(visitedCheckRadioGroupButtonChangeListener);


        fever_checkBox = findViewById(R.id.questionnarie_fever_radioButton_true);
        muscle_ache_checkBox = findViewById(R.id.questionnarie_muscle_ache_radioButton_true);
        cough_checkBox = findViewById(R.id.questionnarie_cough_radioButton_true);
        sputum_checkBox = findViewById(R.id.questionnarie_sputum_radioButton_true);
        runny_nose_checkBox = findViewById(R.id.questionnarie_runny_nose_radioButton_true);
        dyspnea_checkBox = findViewById(R.id.questionnarie_dyspnea_radioButton_true);
        sore_throat_checkBox = findViewById(R.id.questionnarie_sore_throat_radioButton_true);

        clinicReservationTime = null;
        symptom_start_date = null;
        visitedDetail = null;
        contact_relationship = null;
        contact_period = null;
        entrance_date = null;

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckedInfo();

                //
                // 서버에 전송하는 코드 작성
                //
                Log.d("~~~~","클릭");
                sendReservationData(new ReservationData("userid",222,"hospital","time","date",false));


                Log.d("~~~~~",isVisited+"  "+ visitedDetail+"  "+isContacted+"  "+contact_relationship+"  "+contact_period+"  "+ hasFever+"  "+ hasMuscle_ache+"  "+ hasSputum+"  "+ hasRunnyNose+"  "+ hasDyspnea+"  "+hasSoreThroat+"  "+symptom_start_date+"  "+ entrance_date);
                //sendQuestionnaireData(new QuestionnaireData("user",false,"visitedDetail",false,"contact_relationship","period",false,false,false,false,false,false,"startdate","entrance"));


                //Log.d("1607", entrance_date+"\n"+isVisited+"\n"+visitedDetail+"\n"+isContected+"\n"+contact_relationship+"\n"+contact_period+"\n"+hasFever+"\n"+hasMuscle_ache+"\n"+hasCough+"\n"+hasSputum+"\n"+hasRunnyNose+"\n"+hasDyspnea+"\n"+ hasSoreThroat+"\n"+symptom_start_date+"\n"+clinicName+"\n"+clinicReservationTime);
                Toast.makeText(getApplicationContext(), "눌림", Toast.LENGTH_SHORT).show();

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR,year);
                myCalender.set(Calendar.MONTH, month);
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        DatePickerDialog.OnDateSetListener virusStartDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                myCalender.set(Calendar.YEAR,year);
                myCalender.set(Calendar.MONTH, month);
                myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateVirusStartLabel();
            }
        };

        entranceDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(QuestionnaireActivity.this, date, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        termTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNumberPicker(v, "접촉기간", "1", 30, 0,1, 1);
            }
        });

        startVirusDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(QuestionnaireActivity.this, virusStartDate, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

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
                Log.i("~~~~~", "result: "+result);

                sendQuestionnaireData(new QuestionnaireData("user",false,"visitedDetail",false,"contact_relationship","period",false,false,false,false,false,false,"startdate","entrance"));
               // sendQuestionnaireData(new QuestionnaireData("userid",isVisited, visitedDetail, isContacted, contact_relationship, contact_period, hasFever, hasMuscle_ache, hasSputum, hasRunnyNose, hasDyspnea, hasSoreThroat, symptom_start_date, entrance_date));

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

    private void sendQuestionnaireData(QuestionnaireData questionnaireData) {
        Log.d("~~~~~","sendQuestionnaireData");

        Call<ResponseBody> dataCall = serviceApi.sendQuestionnaireData(questionnaireData);
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


    RadioGroup.OnCheckedChangeListener contactRadioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.questionnarie_contact_radioButton_true){
                isContacted = true;
            } else if(checkedId == R.id.questionnarie_contact_radioButton_false){
                isContacted = false;
            }
        }
    };

    RadioGroup.OnCheckedChangeListener visitedCheckRadioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            if(checkedId == R.id.questionnarie_visited_radioButton_true){
                isVisited = true;
            } else if(checkedId == R.id.questionnarie_visited_radioButton_false){
                isVisited = false;
            }


        }
    };

    private void setCheckedInfo(){

        if(fever_checkBox.isChecked() == true){
            hasFever = true;
        } else{
            hasFever = false;
        }

        if(muscle_ache_checkBox.isChecked() == true){
            hasMuscle_ache = true;
        } else{
            hasMuscle_ache = false;
        }

        if(cough_checkBox.isChecked() == true){
            hasCough = true;
        } else{
            hasCough = false;
        }

        if(runny_nose_checkBox.isChecked() == true){
            hasRunnyNose = true;
        } else{
            hasRunnyNose = false;
        }

        if(dyspnea_checkBox.isChecked() == true){
            hasDyspnea = true;
        } else{
            hasDyspnea = false;
        }

        if(sputum_checkBox.isChecked() == true){
            hasSputum = true;
        } else{
            hasSputum = false;
        }

        if(sore_throat_checkBox.isChecked() == true){
            hasSoreThroat = true;
        } else{
            hasSoreThroat = false;
        }

        contact_relationship = String.valueOf(relationEditText.getText());
        visitedDetail = String.valueOf(countryEditText.getText());
    }

    private void setIntentInfo(){
        clinicName = getIntent().getExtras().getString("clinicName");
        clinicReservationTime = getIntent().getExtras().getString("clinicTime");
    }

    private void updateLabel(){
        String myFormat = "yy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        entranceDateTextView.setText(sdf.format(myCalender.getTime()));
        entranceDateTextView.setTextColor(Color.BLACK);
        entrance_date = sdf.format(myCalender.getTime());
    }

    private void updateVirusStartLabel(){
        String myFormat = "yy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        startVirusDateTextView.setText(sdf.format(myCalender.getTime()));
        startVirusDateTextView.setTextColor(Color.BLACK);
        symptom_start_date = sdf.format(myCalender.getTime());
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        termTextView.setText(String.valueOf(newVal)+ "일");
        termTextView.setTextColor(Color.BLACK);
        contact_period = String.valueOf(newVal);
    }

    public void showNumberPicker(View view, String title, String subtitle, int maxvalue, int minvalue, int step, int defvalue){
        NumberpickerDialog newFragment = new NumberpickerDialog();

        //Dialog에는 bundle을 이용해서 파라미터를 전달한다
        Bundle bundle = new Bundle(6); // 파라미터는 전달할 데이터 개수
        bundle.putString("title", title); // key , value
        bundle.putString("subtitle", subtitle); // key , value
        bundle.putInt("maxvalue", maxvalue); // key , value
        bundle.putInt("minvalue", minvalue); // key , value
        bundle.putInt("step", step); // key , value
        bundle.putInt("defvalue", defvalue); // key , value
        newFragment.setArguments(bundle);
        //class 자신을 Listener로 설정한다
        newFragment.setValueChangeListener(this);
        newFragment.show(getSupportFragmentManager(), "null");
    }

}
