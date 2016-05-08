package br.unip.www.webrtc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    public static String EDT_LOGIN = "login";
    public static String EDT_PASSWORD = "password";
    private static final String WEBRTC_LOGIN = "pi";
    private static final String WEBRTC_PASSWORD = "raspberry";

    @Bind(R.id.activity_login_login)
    Button btnLogin;

    @Bind(R.id.activity_login_exit)
    Button btnExit;

    @Bind(R.id.activity_login_edt_login)
    EditText edtLogin;

    @Bind(R.id.activity_login_edt_password)
    EditText edtPassword;

    @Bind(R.id.activity_login_progress_bar)
    ProgressBar progressBar;

    @Bind(R.id.activity_login_ll_body_fields)
    LinearLayout linearLayoutBodyFields;

    String login;
    String password;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);

        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login = edtLogin.getText().toString();
                password = edtPassword.getText().toString();

                if (!login.isEmpty() && !password.isEmpty()){
                   loginAccess(login, password);
                    saveUserName();
                }else{
                    new AlertDialog.Builder(LoginActivity.this)
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setTitle(R.string.label_attention)
                            .setMessage(getString(R.string.error_complete_all_fields))
                            .setPositiveButton(getString(android.R.string.ok), null)
                            .create()
                            .show();
                }
            }
        });
    }

    public void loginAccess(String login, String senha) {
        progressBar.setVisibility(View.VISIBLE);
        linearLayoutBodyFields.setVisibility(View.GONE);

        if (WEBRTC_LOGIN.equals(login) && WEBRTC_PASSWORD.equals(senha)) {

            startActivity(new Intent(this, MainActivity.class));

        } else {
            new AlertDialog.Builder(LoginActivity.this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.label_attention)
                    .setMessage(getString(R.string.error_login_access))
                    .setPositiveButton(getString(android.R.string.ok), null)
                    .create()
                    .show();
            progressBar.setVisibility(View.GONE);
            linearLayoutBodyFields.setVisibility(View.VISIBLE);
        }
    }

    private void saveUserName() {
        sharedPreferences = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(EDT_LOGIN, edtLogin.getText().toString());
        editor.putString(EDT_PASSWORD, edtPassword.getText().toString());
        editor.apply();
    }
}
