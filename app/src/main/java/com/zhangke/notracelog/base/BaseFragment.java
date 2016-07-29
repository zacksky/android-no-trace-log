package com.zhangke.notracelog.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangke on 16/7/28.
 * 所有fragment的基类
 */
public class BaseFragment extends Fragment {

    protected Map<String, String> extendLogMap = new HashMap<String, String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public Map<String, String> getExtendLogMap() {
        return extendLogMap;
    }
}
