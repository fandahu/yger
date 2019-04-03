package com.example.hufan.yger.acitivitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hufan.yger.R;
import com.example.hufan.yger.adapters.MyBoxAdapter;
import com.example.hufan.yger.db.BaseDao;
import com.example.hufan.yger.db.BaseDaoImpl;
import com.example.hufan.yger.model.Box;
import com.example.hufan.yger.model.Boxs;
import com.example.hufan.yger.utils.DialogUtil;
import com.example.hufan.yger.utils.TimeUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by hufan on 2019/4/1.
 */

public class Scan extends BaseActivity implements TextView.OnEditorActionListener, Toolbar.OnMenuItemClickListener {
    private EditText ed1;
    private ListView listView;
    private EditText spinner;
    private String orderid;//订单号
    private ArrayList<Box> boxdata;//接收下载数据
    private ArrayList<Boxs> boxsdata;//页面显示
    private BaseDao<Box, Integer> daobox;
    private Gson gson;
    private Box box;
    private MyBoxAdapter adapter;
    SharedPreferences share;
    @Override
    public int getLayout() {
        return R.layout.smmx;
    }

    @Override
    public void initView() {
        ed1 = findViewById(R.id.editText1);
        listView = findViewById(R.id.listView1);
        spinner = findViewById(R.id.spinner);
        Intent intent = getIntent();
        orderid = intent.getStringExtra("ddh");
        spinner.setOnEditorActionListener(this);
        ed1.setOnEditorActionListener(this);
        boxsdata = new ArrayList<Boxs>();
        boxdata = new ArrayList<Box>();
        daobox = new BaseDaoImpl<>(this,Box.class);
        toolbar.inflateMenu(R.menu.menu);
        toolbar.setOnMenuItemClickListener(this);
        share = this.getSharedPreferences("URL", MODE_PRIVATE);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
            if (textView.getId() == R.id.spinner) {
                if (spinner.getText().toString().trim().length() <= 0) {
                    Toast.makeText(Scan.this, "请扫描箱号", Toast.LENGTH_SHORT).show();
                } else {
                    OkGo.<String>post("http://122.225.255.213:8088/lm/pos/zx/getskudtl.json")
                            .tag(this)
                            .params("orderid", "Y01190300005-1")
                            .params("xh", spinner.getText().toString().trim())
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    try {
                                        boxdata.clear();
                                        boxsdata.clear();
                                        //下载信息存在list
                                        JSONArray ja = new JSONArray(response.body());
                                        Log.d("Tet",response.body());
                                        for (int i = 0; i < ja.length(); i++) {
                                            gson = new Gson();
                                            box = gson.fromJson(ja.getJSONObject(i).toString(), Box.class);
                                            boxdata.add(box);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    splsh();
                                    Log.d("Tet",boxdata.size()+"|" + boxsdata.size()+","+boxdata.get(2).getXm().toString());
                                    adapter = new MyBoxAdapter(boxsdata, Scan.this);
                                    listView.setAdapter(adapter);
                                    ed1.setText("");
                                    ed1.requestFocus();
                                }
                                @Override
                                public void onError(Response<String> response) {
                                    super.onError(response);
                                    Toast.makeText(Scan.this, "失败：" + response.body(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            } else if (textView.getId() == R.id.editText1) {
                boolean boo = false;
               for (int j = 0;j<boxdata.size();j++){
                   if(boxdata.get(j).getSku().equals(ed1.getText().toString().trim())&&boxdata.get(j).getQty() <= boxdata.get(j).getYs()){
                            boxdata.get(j).setYs(boxdata.get(j).getYs()+1);
                            boxdata.get(j).setCjq_id(share.getString("jqh","未设置机器号"));
                            boxdata.get(j).setRq(TimeUtil.getCurrentDate());
                            boxsdata.clear();
                            boo = true;
                            splsh();
                            adapter.notifyDataSetChanged(boxsdata);
                            sc(boxdata.get(j));
                            ed1.setText("");
                            ed1.requestFocus();
                            break;
                       }
                   }
                   if (boo == true)
                   {
                       DialogUtil.showCommonAlertDialog(Scan.this,"次条码不符合或数量超出");
                       ed1.setText("");
                       ed1.requestFocus();
                   }

            }
            return true;
        }
        return false;
    }
    void sc(Box box){
        OkGo.<String>post("http://122.225.255.213:8088/lm/pos/zx/getskudtl.json")
                .tag(this)
                .params("xm",box.getXm())
                .params("orderid",box.getOrdno())
                .params("xh",spinner.getText().toString().trim())
                .params("sku",ed1.getText().toString().trim())
                .params("ys",box.getYs()+"")
                .params("cjq_id",box.getCjq_id())
                .params("rq",box.getRq())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if(response.body().equals("fail")){
                            DialogUtil.showDialog(Scan.this, "上传失败", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                }
                            });
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        DialogUtil.showDialog(Scan.this, "上传失败", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });
                    }
                });
    }
    //刷新
    void splsh(){
        boolean a = false;
        for (int i = 0; i < boxdata.size(); i++) {
            a = false;
            for (int j = 0; j < boxsdata.size(); j++) {
                if(boxsdata.get(j).getName().trim().equals(boxdata.get(i).getXm().trim())){
                    boxsdata.get(j).setZs(boxsdata.get(j).getZs() + boxdata.get(i).getQty());
                    boxsdata.get(j).setYs(boxsdata.get(j).getYs() + boxdata.get(i).getYs());
                    a = true;
                }
            }
            if(a == false){
                boxsdata.add(new Boxs(boxdata.get(i).getXm().trim(), boxdata.get(i).getQty(), boxdata.get(i).getYs()));
            }
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        if(item.getItemId() == R.id.title){
            boolean a = false;
            for (int i =0;i<boxdata.size();i++){
                if(boxdata.get(i).getQty() != boxdata.get(i).getYs()){
                    a = true;
                }
            }
            if(a == true){
                DialogUtil.showCommonAlertDialog(Scan.this,"该箱未完成，不可修改状态");
            }else {
                OkGo.<String>post("")
                        .tag(this)
                        .params("orderid", orderid)
                        .params("xh", spinner.getText().toString().trim())
                        .params("i", "3")
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                if (response.body().equals("fail")) {
                                    DialogUtil.showCommonAlertDialog(Scan.this, "提交失败");
                                } else if (response.body().equals("success")) {
                                    DialogUtil.showCommonAlertDialog(Scan.this, "提交成功");
                                }
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                                DialogUtil.showCommonAlertDialog(Scan.this, "提交失败");
                            }
                        });
            }
        }
        return false;
    }
}
