package com.example.hufan.yger.acitivitys;


import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hufan.yger.R;

/**
 * Created by hufan on 2019/4/1.
 */

public class Input extends BaseActivity implements View.OnClickListener {
    private EditText ed1;
    @Override
    public int getLayout() {
        return R.layout.input;
    }

    @Override
    public void initView() {
        ed1 = findViewById(R.id.editText1);
        findViewById(R.id.button1).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button1) {
            if(ed1.getText().toString().trim().length()<=0) {
                Toast.makeText(Input.this, "请输入订单号", Toast.LENGTH_SHORT).show();
            }else {
                Intent intent = new Intent(Input.this,Scan.class);
                intent.putExtra("ddh",ed1.getText().toString());
                startActivity(intent);
            }
        }
    }
}
