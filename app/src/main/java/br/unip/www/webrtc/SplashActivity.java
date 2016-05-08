package br.unip.www.webrtc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

        if (!sharedPreferences.getString(LoginActivity.EDT_LOGIN, "").isEmpty()){
            startActivity(new Intent(this,MainActivity.class));
        }else{
            startActivity(new Intent(this, LoginActivity.class));
        }

        finish();
    }
}
