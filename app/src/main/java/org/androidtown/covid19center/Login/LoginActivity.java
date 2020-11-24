package org.androidtown.covid19center.Login;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.Main.MainActivity;
import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Hospital.HospitalMainActivity;
import org.androidtown.covid19center.Server.AppManager;
import org.androidtown.covid19center.Server.RetrofitClient;
import org.androidtown.covid19center.Server.ServiceApi;
import org.androidtown.covid19center.Server.UsersVO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText idEditText, passwordEditText;
    Button loginButton;
    private ServiceApi service;
    private Context mcontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 네트워크가 켜져있으면 동작
        if(checkNetworkSetting() == 1){
            setLoginInfomation();
        }

    }

    public void setLoginInfomation(){
        service = RetrofitClient.getClient().create(ServiceApi.class);

        idEditText = findViewById(R.id.idEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = idEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                Log.d("~~~~~","id:" +id);
                Log.d("~~~~~~","password:"+password);

                //startLogin(new UsersData(id, password));
                startLogin(id, password);

            }
        });

    }

    public int checkNetworkSetting(){
        mcontext = getApplicationContext();

        if(getOnline_23() == 0){

            Intent intent = new Intent(getApplicationContext(), DataWarningActivity.class);
            startActivity(intent);
            finish();
        } else{
            return 1;
        }
        return 0;
    }

    public int getOnline_23(){
        int ret_code = 0;

        ConnectivityManager cm = (ConnectivityManager) mcontext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI && activeNetwork.isConnectedOrConnecting()) {
                // wifi 연결중
                return 1;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE && activeNetwork.isConnectedOrConnecting()) {
                // 모바일 네트워크 연결중
                return 2;
            }
            else {
                // 네트워크 오프라인 상태.
                return 0;
            }
        } else {
            //네트워크 없는 상태
            return 0;
        }

    }

    public void startLogin(String id, String password) {
        service.getUserData(id, password).enqueue(new Callback<List<UsersVO>>() {
            @Override
            public void onResponse(Call<List<UsersVO>> call, Response<List<UsersVO>> response) {
                boolean isLoginPossible = false;

                if(response.isSuccessful()) {
                    List<UsersVO> data = response.body();

                    Log.d("~~~~~","성공");

                    for(int i=0; i<data.size(); i++) {
                        if(data.get(i).getId().equals(id) && data.get(i).getPassword().equals(password)) {
                            Log.d("~~~~~","로그인 가능");
                            Toast toast = Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT);
                            toast.show();

                            AppManager.getInstance().setUserId(id);   // user id 저장하기


                            if(id.equals("hhh")) {
                                Intent intent = new Intent(LoginActivity.this, HospitalMainActivity.class);
                                startActivity(intent);

                                finish();
                            }
                            else {
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);

                                finish();
                            }
                            isLoginPossible = true;

                            break;
                        }
                    }
                    Log.d("~~~~~","isloginpossible:"+isLoginPossible);
                    if(!isLoginPossible) {

                        Log.d("~~~~~","로그인 정보 확인 필요");
                        Toast toast = Toast.makeText(LoginActivity.this, "로그인 정보를 확인해주세요.", Toast.LENGTH_SHORT);
                        toast.show();

                        idEditText.setText(null);
                        passwordEditText.setText(null);

                        idEditText.requestFocus();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<UsersVO>> call, Throwable t) {
                Log.d("~~~~~","실패: "+ t);
                t.printStackTrace();

            }
        });
    }

}