package com.zhangke.notracelog;

import android.app.Activity;
import android.app.Application;

import java.util.LinkedList;

/**
 * Created by zhangke on 16/7/28.
 */
public class MyApplication extends Application {
    private static MyApplication _instance;
    private LinkedList<Activity> activities = new LinkedList<Activity>();

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
    }

    public static MyApplication getInstance() {
        return _instance;
    }

    public void addActivity(Activity activity) {
        if (activity != null) {
            activities.add(activity);
        }
    }

    public void removeActivity(Activity activity) {
        if (activity != null && activities.contains(activity)) {
            activities.remove(activity);
        }

    }

    /**
     * 得到当前activity
     *
     * @return
     */
    public Activity getCurrentTopActivity() {
        if (!activities.isEmpty()) {
            return activities.getLast();
        } else {
            return null;
        }
    }
}
