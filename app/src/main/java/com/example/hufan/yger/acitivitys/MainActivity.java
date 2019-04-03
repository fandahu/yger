package com.example.hufan.yger.acitivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hufan.yger.R;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.Button3).setOnClickListener(this);
        findViewById(R.id.Button4).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button1) {
            Intent intent = new Intent(this,SetUrl.class);
            startActivity(intent);
        }else if(view.getId() == R.id.button2) {
            Intent intent = new Intent(this,Input.class);
            startActivity(intent);
        }else if(view.getId() == R.id.Button3){
            Intent intent = new Intent(this,SetJqh.class);
            startActivity(intent);
        }else if(view.getId() == R.id.Button4){
            Intent intent = new Intent(this,SetJqh.class);
            startActivity(intent);
        }
    }
}
