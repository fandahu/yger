package com.example.hufan.yger.acitivitys;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hufan.yger.R;
import com.example.hufan.yger.utils.DialogUtil;

/**
 * Created by hufan on 2019/4/1.
 */

public class SetUrl extends BaseActivity implements View.OnClickListener {
    EditText ed1,ed2,ed3;
    SharedPreferences shared;
    @Override
    public int getLayout() {
        return R.layout.seturl;
    }

    @Override
    public void initView() {
        shared = this.getSharedPreferences("URL", MODE_PRIVATE);
        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        ed1.setText(shared.getString("url1", "http://122.225.255.213:8088/lm/pos/zx/getskudtl.json"));
        ed2.setText(shared.getString("url2","http://122.225.255.213:8088/lm/pos/zx/statistics.json"));
        ed3.setText(shared.getString("url3", "http://122.225.255.213:8088/lm/pos/zx/setStatus.json"));
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button1) {
            if(ed1.getText().toString().trim().length() == 0 || ed1.getText().toString().trim().length() == 0 || ed1.getText().toString().trim().length() == 0) {
                DialogUtil.showCommonAlertDialog(this, "请设置路径");
            }else {
                SharedPreferences.Editor editor = shared.edit();
                editor.putString("url1",ed1.getText().toString().trim());
                editor.putString("url2",ed2.getText().toString().trim());
                editor.putString("url3",ed3.getText().toString().trim());
                editor.commit();
                Toast.makeText(SetUrl.this, "设置成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        }else if(v.getId() == R.id.button2) {
            finish();
        }
    }
}
