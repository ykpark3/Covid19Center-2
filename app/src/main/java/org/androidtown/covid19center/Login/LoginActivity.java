package org.androidtown.covid19center.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.androidtown.covid19center.Main.MainActivity;
import org.androidtown.covid19center.R;
import org.androidtown.covid19center.Server.CalendarActivity;
import org.androidtown.covid19center.Server.RetrofitClient;
import org.androidtown.covid19center.Server.ServiceApi;
import org.androidtown.covid19center.Server.UsersData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText idEditText, passwordEditText;
    Button loginButton;
    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View view) {
                Log.d("~~~~~","data 받는 button click");
                Intent intent = new Intent(LoginActivity.this, CalendarActivity.class);
                startActivity(intent);
            }
        });

    }

    public void startLogin(String id, String password) {
        service.getUserData(id, password).enqueue(new Callback<List<UsersData>>() {
            @Override
            public void onResponse(Call<List<UsersData>> call, Response<List<UsersData>> response) {
                boolean isLoginPossible = false;

                if(response.isSuccessful()) {
                    List<UsersData> data = response.body();

                    Log.d("~~~~~","성공");

                    for(int i=0; i<data.size(); i++) {
                        if(data.get(i).getId().equals(id) && data.get(i).getPassword().equals(password)) {
                            Log.d("~~~~~","로그인 가능");
                            Toast toast = Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT);
                            toast.show();

                            if(id.equals("hhh")) {
                                Intent intent = new Intent(LoginActivity.this, CalendarActivity.class);
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
            public void onFailure(Call<List<UsersData>> call, Throwable t) {
                Log.d("~~~~~","실패: "+ t);
                t.printStackTrace();

            }
        });
    }
}