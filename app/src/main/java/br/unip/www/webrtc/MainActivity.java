package br.unip.www.webrtc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.activity_main_camera_one)
    Button btnCameraOne;

    @Bind(R.id.activity_main_camera_two)
    Button btnCameraTwo;

    @Bind(R.id.activity_main_video_config)
    Button btnVideoConfig;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_main_logout){

            sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();
            startActivity(new Intent(this, LoginActivity.class));

        }
        return false;
    }

}

