package org.androidtown.covid19center.Mypage;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import org.androidtown.covid19center.R;
import org.androidtown.covid19center.SelfCheck.NumberpickerDialog;
import org.androidtown.covid19center.Server.AppManager;
import org.androidtown.covid19center.Server.QuestionnaireData;
import org.androidtown.covid19center.Server.QuestionnaireVO;
import org.androidtown.covid19center.Server.ReservationVO;

import org.androidtown.covid19center.Server.RetrofitClient;
import org.androidtown.covid19center.Server.ServiceApi;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionnaireModificationActivity extends AppCompatActivity implements NumberPicker.OnValueChangeListener {

    private RadioGroup visitedCheck_radioGroup;
    private RadioButton visitedTrue_radioButton;
    private RadioButton visitedFalse_radioButton;
    private EditText countryEditText;
    private TextView entranceDateTextView;

    private RadioGroup contact_radioGroup;
    private RadioButton contactTrue_radioButton;
    private RadioButton contactFalse_radioButton;
    private EditText relationEditText;
    private TextView termTextView;

    private CheckBox none_checkBox;
    private CheckBox fever_checkBox, muscle_ache_checkBox, cough_checkBox, sputum_checkBox, runny_nose_checkBox, dyspnea_checkBox, sore_throat_checkBox;
    private TextView startVirusDateTextView;

    private final Calendar myCalender = Calendar.getInstance();
    private ImageButton backButton;
    private Button nextButton;

    private Boolean isVisited;
    private String visitedDetail;
    private String entrance_date;
    private boolean isContacted;
    private String contact_relationship;
    private String contact_period;
    private boolean hasFever, hasMuscle_ache, hasCough, hasSputum, hasRunnyNose, hasDyspnea, hasSoreThroat;
    private String symptom_start_date;
    private String toDoctor;

    /*
    private String clinicName;
    private String clinicReservationTime;
     */
    private ServiceApi serviceApi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Log.d("~~~~~","onCreate");

        serviceApi = RetrofitClient.getClient().create(ServiceApi.class);

        setContentView(R.layout.activity_questionnaire_modification);

        setLayoutElement();

        getQuesionnaire();
    }


    private void setLayoutElement(){
        Log.d("~~~~~","setLayoutElement");

        visitedCheck_radioGroup = findViewById(R.id.questionnarie_visited_radioGroup);
        visitedCheck_radioGroup.setOnCheckedChangeListener(visitedCheckRadioGroupButtonChangeListener);
        visitedTrue_radioButton = findViewById(R.id.questionnarie_visited_radioButton_true);
        visitedFalse_radioButton = findViewById(R.id.questionnarie_visited_radioButton_false);
        countryEditText = findViewById(R.id.questionnarie_editText_country);
        entranceDateTextView = findViewById(R.id.questionnarie_textView_date);


        contact_radioGroup = findViewById(R.id.questionnarie_contact_radioGroup);
        contact_radioGroup.setOnCheckedChangeListener(contactRadioGroupButtonChangeListener);
        contactTrue_radioButton = findViewById(R.id.questionnarie_contact_radioButton_true);
        contactFalse_radioButton = findViewById(R.id.questionnarie_contact_radioButton_false);
        relationEditText = findViewById(R.id.questionnarie_editText_relation);
        termTextView = findViewById(R.id.questionnarie_textView_term);

        none_checkBox = findViewById(R.id.questionnarie_radioButton_false);
        fever_checkBox = findViewById(R.id.questionnarie_fever_radioButton_true);
        muscle_ache_checkBox = findViewById(R.id.questionnarie_muscle_ache_radioButton_true);
        cough_checkBox = findViewById(R.id.questionnarie_cough_radioButton_true);
        sputum_checkBox = findViewById(R.id.questionnarie_sputum_radioButton_true);
        runny_nose_checkBox = findViewById(R.id.questionnarie_runny_nose_radioButton_true);
        dyspnea_checkBox = findViewById(R.id.questionnarie_dyspnea_radioButton_true);
        sore_throat_checkBox = findViewById(R.id.questionnarie_sore_throat_radioButton_true);
        startVirusDateTextView = findViewById(R.id.questionnarie_textView_start_date);

        nextButton = findViewById(R.id.questionnarie_button);
        backButton = findViewById(R.id.questionnarie_backButton);

       // clinicReservationTime = null;
        symptom_start_date = null;
        visitedDetail = null;
        contact_relationship = null;
        contact_period = null;
        entrance_date = null;

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckedInfo();

                /** toDoctor 내용 추가해주기
                 *
                 */
                /*
                updateQuestionnaireData(new QuestionnaireData(AppManager.getInstance().getUserId(),
                        isVisited,
                        visitedDetail,
                        entrance_date,
                        isContacted,
                        contact_relationship,
                        contact_period,
                        hasFever,
                        hasMuscle_ache,
                        hasSputum,
                        hasRunnyNose,
                        hasDyspnea,
                        hasSoreThroat,
                        symptom_start_date,
                        "toDoctor"));


                 */
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
                new DatePickerDialog(QuestionnaireModificationActivity.this, date, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH)).show();
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
                new DatePickerDialog(QuestionnaireModificationActivity.this, virusStartDate, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH), myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }



    private void updateQuestionnaireData(QuestionnaireData questionnaireData) {
        Log.d("~~~~~", "updateQuestionnaireData");

        Call<ResponseBody> dataCall = serviceApi.sendQuestionnaireData(questionnaireData);
        dataCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String result = null;
                try {
                    Log.d("~~~~~", "response" + response.code());
                    result = response.body().string();

                } catch (IOException e) {
                    Log.d("~~~~~", String.valueOf(e));
                    e.printStackTrace();
                }
                Log.i("~~~~~", "result: " + result);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("~~~~~", "fail");
                if (t instanceof IOException) {
                    // Handle IO exception, maybe check the network and try again.
                    Log.i("~~~~~", "t" + t);
                }
            }
        });
    }



    protected void getQuesionnaire() {
        Log.d("~~~~~", " getQuesionnaire");

        serviceApi.getQuesionnaireVO().enqueue(new Callback<List<QuestionnaireVO>>() {
            @Override
            public void onResponse(Call<List<QuestionnaireVO>> call, Response<List<QuestionnaireVO>> response) {

                int sequence;
                String user_id;

                int visited;
                int contact;
                int fever, muscle_ache, sputum, runny_nose, dyspnea, sore_throat;

                /*
                String visited_detail, entrance_date;
                String contact_relationship, contact_period;
                String symptom_start_date, toDoctor;
                 */


                if (response.isSuccessful()) {
                    List<QuestionnaireVO> data = response.body();

                    Log.d("~~~~~", "questionnaireList 가져오기 성공");

                    sequence = data.get(data.size() -1).getSequence();
                    user_id = data.get(data.size() - 1).getUser_id();
                    visited = data.get(data.size() - 1).getVisited();
                    visitedDetail = data.get(data.size() - 1).getVisited_detail();
                    entrance_date = data.get(data.size() - 1).getEntrance_date();
                    contact = data.get(data.size() - 1).getContact();
                    contact_relationship = data.get(data.size() - 1).getContact_relationship();
                    contact_period = data.get(data.size() - 1).getContact_period();
                    fever = data.get(data.size() - 1).getFever();
                    muscle_ache = data.get(data.size() - 1).getMuscle_ache();
                    sputum = data.get(data.size() - 1).getSputum();
                    runny_nose = data.get(data.size() - 1).getRunny_nose();
                    dyspnea = data.get(data.size() - 1).getDyspnea();
                    sore_throat = data.get(data.size() - 1).getSore_throat();
                    symptom_start_date = data.get(data.size() - 1).getSymptom_start_date();
                    toDoctor = data.get(data.size() - 1).getToDoctor();

                    Log.d("~~~~~","sequence: "+sequence +"\n"+
                            "user_id: "+user_id +"\n"+
                            "visited: "+visited +"\n"+
                            "visitedDetail: "+visitedDetail +"\n"+
                            "entrance_date: "+entrance_date +"\n"+
                            "contact: "+contact +"\n"+
                            "contact_relationship: "+contact_relationship +"\n"+
                            "fever: "+fever +"\n"+
                            "muscle_ache: "+muscle_ache +"\n"+
                            "sputum: "+sputum +"\n"+
                            "runny_nose: "+runny_nose +"\n"+
                            "dyspnea: "+dyspnea +"\n"+
                            "sore_throat: "+sore_throat +"\n"+
                            "symptom_start_date: "+symptom_start_date +"\n"+
                            "toDoctor: "+toDoctor +"\n");


                    /** int -> boolean 형 변환
                     *
                     */
                    isVisited = visited != 0;
                    isContacted = contact != 0;
                    hasFever = fever != 0;
                    hasMuscle_ache = muscle_ache != 0;
                    hasSputum = sputum != 0;
                    hasRunnyNose = runny_nose != 0;
                    hasDyspnea = dyspnea != 0;
                    hasSoreThroat = sore_throat != 0;

                    Log.d("~~~~~","isVisited: "+isVisited +"\n"+
                                    "isContacted: "+isContacted +"\n"+
                                    "hasFever: "+hasFever +"\n"+
                                    "hasMuscle_ache: "+hasMuscle_ache +"\n"+
                                    "hasSputum: "+hasSputum +"\n"+
                                    "hasRunnyNose: "+hasRunnyNose +"\n"+
                                    "hasDyspnea: "+hasDyspnea +"\n"+
                                    "hasSoreThroat: "+hasSoreThroat +"\n" );

                    setQuestionnaire();
                }
            }

            @Override
            public void onFailure(Call<List<QuestionnaireVO>> call, Throwable t) {
                Log.d("~~~~~", "실패: " + t);
                t.printStackTrace();
            }
        });

    }



    private void setQuestionnaire() {
        Log.d("~~~~~","setQuestionnaire");

        if(isVisited){
            visitedTrue_radioButton.setChecked(true);
        }
        else {
            visitedFalse_radioButton.setChecked(true);
        }
        countryEditText.setText(visitedDetail);
        entranceDateTextView.setText(entrance_date);
        entranceDateTextView.setTextColor(Color.BLACK);

        if(isContacted){
            contactTrue_radioButton.setChecked(true);
        }
        else {
            contactFalse_radioButton.setChecked(true);
        }
        relationEditText.setText(contact_relationship);
        termTextView.setText(contact_period);


        if(hasFever){
            fever_checkBox.setChecked(true);
        }
        if(hasMuscle_ache){
            muscle_ache_checkBox.setChecked(true);
        }
        if(hasCough){
            cough_checkBox.setChecked(true);
        }
        if(hasSputum){
            sputum_checkBox.setChecked(true);
        }
        if(hasRunnyNose){
            runny_nose_checkBox.setChecked(true);
        }
        if(hasDyspnea){
            dyspnea_checkBox.setChecked(true);
        }
        if(hasSoreThroat){
            sore_throat_checkBox.setChecked(true);
        }

        if(!hasFever && !hasMuscle_ache && !hasCough && !hasSputum && !hasRunnyNose && !hasDyspnea && !hasSoreThroat) {
            none_checkBox.setChecked(true);
        }

        startVirusDateTextView.setText(symptom_start_date);
        startVirusDateTextView.setTextColor(Color.BLACK);
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

        Log.d("~~~~~","setCheckedInfo");

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

    /*
    private void setIntentInfo(){
        clinicName = getIntent().getExtras().getString("clinicName");
        clinicReservationTime = getIntent().getExtras().getString("clinicTime");
    }

     */

    private void updateLabel(){
        Log.d("~~~~~","updateLabel");
        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        entranceDateTextView.setText(sdf.format(myCalender.getTime()));
        entranceDateTextView.setTextColor(Color.BLACK);
        entrance_date = sdf.format(myCalender.getTime());
    }

    private void updateVirusStartLabel(){
        Log.d("~~~~~","updateVirusStartLabel");

        String myFormat = "yyyy/MM/dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.KOREA);
        startVirusDateTextView.setText(sdf.format(myCalender.getTime()));
        startVirusDateTextView.setTextColor(Color.BLACK);
        symptom_start_date = sdf.format(myCalender.getTime());
    }

    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

        Log.d("~~~~~","onValueChange");
        termTextView.setText(String.valueOf(newVal)+ "일");
        termTextView.setTextColor(Color.BLACK);
        contact_period = String.valueOf(newVal);
    }

    public void showNumberPicker(View view, String title, String subtitle, int maxvalue, int minvalue, int step, int defvalue){

        Log.d("~~~~~","showNumberPicker");
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