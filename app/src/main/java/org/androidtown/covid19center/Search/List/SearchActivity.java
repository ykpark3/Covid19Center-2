package org.androidtown.covid19center.Search.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import org.androidtown.covid19center.DataBase.AppDatabase;
import org.androidtown.covid19center.R;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    ArrayList<ClinicItem> clinicDataList; // 진료소 담을 리스트 생성
    ArrayList<ClinicItem> copyList; // 복사리스트
    EditText clinicSearch; // 진료소 검색
    ClinicAdapter myAdapter;
    Handler mHandler = null;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = AppDatabase.getInstance(getBaseContext());
        mHandler = new Handler();
        Log.d("순서","1");

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("순서","1");
                        addClinicList();
                        Log.d("순서","1");
                        copyList.addAll(clinicDataList);
                    }
                });
            }
        });
        Log.d("순서","1");
        t.start();

        // 검색 이벤트
    }


    // 검색을 수행하는 메소드


    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_search);
        clinicSearch = findViewById(R.id.editClinicSearch);
        clinicDataList = new ArrayList<ClinicItem>();
        copyList = new ArrayList<ClinicItem>();

        Log.d("순서", "2");

        ListView listView = (ListView)findViewById(R.id.clinicListView); // 리스트뷰 생성
        myAdapter = new ClinicAdapter(this,clinicDataList); // 진료소 리스트 관리할 어뎁터 생성

        listView.setAdapter(myAdapter); // 리스트뷰에 어뎁터 탑제

        clinicSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String text = clinicSearch.getText().toString();
                search(text);
            }

        });

        Log.d("리스트", String.valueOf(clinicDataList.size()));

        // 리스트 아이템 클릭 시 발생 이벤트 콜백함수
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                // Intent intent = new Intent(getApplicationContext(), MoviePageActivity.class);
                // intent.putExtra("group", position);
                // startActivity(intent);
            }
        });

    }

    public void search(String charText) {

        clinicDataList.clear();

        Log.d("사이즈", String.valueOf(copyList.size()));

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            clinicDataList.addAll(copyList);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < copyList.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (copyList.get(i).getClinicName().toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    clinicDataList.add(copyList.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        myAdapter.notifyDataSetChanged();
    }

    public void addClinicList()
    {
        db.clinicDao().getAll().observe(this, clinics -> {
            if(clinicDataList.isEmpty())
            {
                Log.d("리스트", String.valueOf(clinicDataList.size()));
                for(int i=0; i<clinics.size();i++)
                {
                    clinicDataList.add(new ClinicItem(clinics.get(i).getClinicName(),clinics.get(i).getClinicCallNumber(), clinics.get(i).getClinicAddress()));
                    Log.d("리스트", String.valueOf(clinicDataList.size()));
                }

            }
        });
        copyList.addAll(clinicDataList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}