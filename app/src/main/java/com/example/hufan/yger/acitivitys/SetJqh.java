package com.example.hufan.yger.acitivitys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hufan.yger.R;
import com.example.hufan.yger.utils.DialogUtil;

/**
 * Created by hufan on 2019/4/1.
 */

public class SetJqh extends BaseActivity implements View.OnClickListener {
    TextView tv;
    EditText ed;
    SharedPreferences share;
    @Override
    public int getLayout() {
        return R.layout.setjqh;
    }

    @Override
    public void initView() {
        share = this.getSharedPreferences("URL", MODE_PRIVATE);
        tv = (TextView) findViewById(R.id.textView2);
        ed = (EditText) findViewById(R.id.editText1);
        tv.setText(share.getString("jqh", "暂未设置机器号"));
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.button1) {
            if(ed.getText().toString().trim().length() == 0) {
                DialogUtil.showCommonAlertDialog(this, "请输入机器号");
            }else {
                SharedPreferences.Editor editor = share.edit();
                editor.putString("jqh",ed.getText().toString().trim());
                editor.commit();
                Toast.makeText(SetJqh.this, "设置成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        }else if(view.getId() == R.id.button2) {
            finish();
        }
    }
}
