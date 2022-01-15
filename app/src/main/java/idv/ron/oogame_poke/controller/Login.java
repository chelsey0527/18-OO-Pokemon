package idv.ron.oogame_poke.controller;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;


import idv.ron.oogame_poke.R;

// 首頁
public class Login extends AppCompatActivity {
    private ImageView ivLoginPic;
    private EditText etName, etPassword;
    private TextView tvResult;
    private Intent intent;

    @Override
    protected void onCreate(@Nullable  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        etName = findViewById(R.id.etName);
        etPassword = findViewById(R.id.etPassword);
        tvResult = findViewById(R.id.tvResult);
        findViews();
        ivLoginPic.setImageResource(R.drawable.loginpicture);
    }

    private void findViews() {
        ivLoginPic = findViewById(R.id.ivLoginPic);
    }

    public void onLoginClick(View v) {
        String name = etName.getText().toString();
        String password = etPassword.getText().toString();
        String text = "Name: "+name+"\nPassword: "+ password+"\n\nLOGIN SUCCESS!";
        tvResult.setText(text);

        if(tvResult.getText().toString()!=null){
            intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);
        }
    }




}