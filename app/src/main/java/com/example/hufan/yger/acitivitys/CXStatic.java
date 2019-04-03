package com.example.hufan.yger.acitivitys;

import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hufan.yger.R;
import com.lzy.okgo.OkGo;

/**
 * Created by hufan on 2019/4/3.
 */

public class CXStatic extends BaseActivity implements TextView.OnEditorActionListener {
    private TextView textView;
    private EditText editText;
    @Override
    public int getLayout() {
        return R.layout.cx;
    }

    @Override
    public void initView() {
        textView = findViewById(R.id.tvmx);
        editText = findViewById(R.id.edht);
        editText.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){

        }
        return false;
    }
}
