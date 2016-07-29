package com.zhangke.notracelog.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhangke.notracelog.MyApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangke on 16/7/28.
 * 所有activity的基类
 */
public class BaseActivity extends AppCompatActivity {
    protected Map<String, String> extendLogMap = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
    }

    public Map<String, String> getExtendLogMap() {
        return extendLogMap;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().removeActivity(this);
    }
}
