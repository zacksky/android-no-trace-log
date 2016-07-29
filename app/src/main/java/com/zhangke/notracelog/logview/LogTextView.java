package com.zhangke.notracelog.logview;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zhangke.notracelog.MyApplication;
import com.zhangke.notracelog.base.BaseActivity;
import com.zhangke.notracelog.base.BaseFragment;
import com.zhangke.notracelog.util.AutoLogUtil;
import com.zhangke.notracelog.util.CommonExecutor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Created by zhangke on 16/6/14.
 */
public class LogTextView extends TextView {
    private String mLogName;
    private Object mObject;


    public LogTextView(Context context) {
        super(context);
    }

    public LogTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setOnClickListener(OnClickListener l) {
        CommonExecutor.executor(new Runnable() {
            @Override
            public void run() {
                Activity activity = MyApplication.getInstance().getCurrentTopActivity();
                if (activity instanceof BaseActivity) {
                    setComponentsName(activity);
                    List<Fragment> fragmentList = AutoLogUtil.getAllFragments((BaseActivity) activity);
                    for (Fragment fragment : fragmentList) {
                        setComponentsName(fragment);
                    }
                }
            }
        });
        super.setOnClickListener(l);
    }

    /**
     * 如果mLogName为空
     * 通过反射给activity或者fragment里面的所有类型是LogTextView
     * 的变量的mLogName赋值
     *
     * @param f
     */
    private void setComponentsName(Object f) {
        // 获取f对象对应类中的所有属性域
        Field[] fields = f.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            try {
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);
                Object oo = fields[i].get(f);
                if (fields[i].getType() == this.getClass()) {
                    ((LogTextView) oo).setObject(f);
                    if (TextUtils.isEmpty(((LogTextView) oo).getLogName())) {
                        if (f instanceof BaseActivity) {
                            ((LogTextView) oo).setLogName(((BaseActivity) f).getClass().getSimpleName() + "-" + varName);
                        } else if (f instanceof BaseFragment) {
                            ((LogTextView) oo).setLogName(((BaseFragment) f).getClass().getSimpleName() + "-" + varName);
                        }
                        ((LogTextView) oo).setLogName(f.getClass().getSimpleName() + "-" + varName);
                    }
                }
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public boolean performClick() {
        boolean result = super.performClick();
        Map<String, String> extendLogMap = null;
        if (mObject != null) {
            if (mObject instanceof BaseActivity) {
                extendLogMap = ((BaseActivity) mObject).getExtendLogMap();
            } else if (mObject instanceof BaseFragment) {
                extendLogMap = ((BaseFragment) mObject).getExtendLogMap();
            }
        }
        //发送log数据
        if (TextUtils.isEmpty(mLogName)) {
            if (extendLogMap != null && extendLogMap.size() > 0) {
//                LogUtil.sendLog(mLogName,extendLogMap);
            } else {
//                LogUtil.sendLog(mLogName);
            }
        }
        //发送完后把extendLogMap清空
        if (extendLogMap != null) {
            extendLogMap.clear();
        }
        return result;
    }

    public void setLogName(String name) {
        mLogName = name;
    }

    public String getLogName() {
        return mLogName;
    }

    public void setObject(Object o) {
        mObject = o;
    }

    public Object getObject() {
        return mObject;
    }

}
