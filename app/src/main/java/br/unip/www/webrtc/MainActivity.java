package br.unip.www.webrtc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

        btnCameraOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("org.videolan.vlc.VLCApplication.gui.video.VideoPlayerActivity");
                i.setAction(Intent.ACTION_VIEW);
                i.setDataAndType(Uri.parse("http://192.168.43.56:8080"), "video/h264");
                startActivity(i);
            }
        });
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

        if (id == R.id.menu_main_exit){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
        return false;
    }

}

