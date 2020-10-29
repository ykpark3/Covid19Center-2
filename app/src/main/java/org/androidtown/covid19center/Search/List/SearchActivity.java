package org.androidtown.covid19center.Search.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.androidtown.covid19center.R;

import java.util.ArrayList;

public class SearchActivity extends Activity {

    ArrayList<ClinicItem> clinicDataList; // 진료소 담을 리스트 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.addClinicList(); // 진료소 리스트 추가

        ListView listView = (ListView)findViewById(R.id.clinicListView); // 리스트뷰 생성
        final ClinicAdapter myAdapter = new ClinicAdapter(this,clinicDataList); // 진료소 리스트 관리할 어뎁터 생성

        listView.setAdapter(myAdapter); // 리스트뷰에 어뎁터 탑제

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

    public void addClinicList()
    {
        // 영화 담을 리스트 생성
        clinicDataList = new ArrayList<ClinicItem>();

        clinicDataList.add(new ClinicItem("성모병원","010-5721-0882", "300m"));
        clinicDataList.add(new ClinicItem("박모병원","010-5721-0882", "300m"));
        clinicDataList.add(new ClinicItem("김모병원","010-5721-0882", "300m"));
        clinicDataList.add(new ClinicItem("강모병원","010-5721-0882", "300m"));
        clinicDataList.add(new ClinicItem("미모병원","010-5721-0882", "300m"));
        clinicDataList.add(new ClinicItem("비모병원","010-5721-0882", "300m"));
        clinicDataList.add(new ClinicItem("모모병원","010-5721-0882", "300m"));
    }

}