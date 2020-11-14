package org.androidtown.covid19center.SelfCheck;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.R;

public class SelfCheckResultActivity extends AppCompatActivity implements FragmentSelfCheck.OnCheckNumListener {

    public int mCheckNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_self_check_result);

        mCheckNum = 999;

        Intent intent = getIntent();
        mCheckNum = intent.getExtras().getInt("checkNum");

        changeView(0);
    }

    private void changeView(int index) {
        // LayoutInflater 초기화.
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        FrameLayout frame = (FrameLayout) findViewById(R.id.self_result_frame) ;
        if (frame.getChildCount() > 0) {
            // FrameLayout에서 뷰 삭제.
            frame.removeViewAt(0);
        }

        Toast.makeText(this, "checknum : " + mCheckNum, Toast.LENGTH_SHORT).show();


        // XML에 작성된 레이아웃을 View 객체로 변환.
        View view = null ;
        switch (index) {
            case 0 :
                view = inflater.inflate(R.layout.self_result_page1, frame, false) ;
                break ;
            case 1 :
                view = inflater.inflate(R.layout.self_result_page2, frame, false) ;
                break ;
            case 2 :
                view = inflater.inflate(R.layout.self_result_page2, frame, false) ;
                break ;
        }

        // FrameLayout에 뷰 추가.
        if (view != null) {
            frame.addView(view) ;
        }
    }

    @Override
    public void onCheckNumSet(int check_num) {
        mCheckNum = check_num;
    }
}
