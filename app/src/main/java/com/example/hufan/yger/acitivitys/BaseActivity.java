package com.example.hufan.yger.acitivitys;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.hufan.yger.R;
import com.example.hufan.yger.utils.DialogUtil;


/**
 * Created by Administrator on 2018/9/3.
 */

public abstract class BaseActivity extends AppCompatActivity {
    private TextView tv;
    public Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        tv = findViewById(R.id.Title);
        toolbar = findViewById(R.id.toolbar);
        tv.setText(getTitle());
        initView();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showDialog(BaseActivity.this, "确定退出吗", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
            }
        });
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }
    //获取绑定布局
    public  abstract int getLayout();
    //获取控件监听
    public  abstract void initView();
    //监听回车键
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        DialogUtil.showDialog(this, "确定退出吗", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
    }

}
