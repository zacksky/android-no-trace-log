package com.zhangke.notracelog.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangke on 16/6/24.
 */
public class AutoLogUtil {

    /**
     * 得到activity里所有的fragment，无论fragment是否可见，只要创建即可
     * 这个activity必须继承自FragmentActivity
     * @return
     */
    public static List<Fragment> getAllFragments(FragmentActivity activity) {
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && fragments.size() > 0) {
            fragmentList.addAll(fragments);
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fragment = fragments.get(i);
                if (fragment.getHost() != null) {
                    fragmentList.addAll(getAllFragments(fragment));
                }
            }
        }
        return fragmentList;
    }

    /**
     * 得到fragment里面嵌套的所有fragment
     *
     * @param frag
     * @return
     */
    public static List<Fragment> getAllFragments(Fragment frag) {
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        FragmentManager fragmentManager = frag.getChildFragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments != null && fragments.size() > 0) {
            fragmentList.addAll(fragments);
            for (int i = 0; i < fragments.size(); i++) {
                Fragment fragment = fragments.get(i);
                if (fragment.getHost() != null) {
                    fragmentList.addAll(getAllFragments(fragment));
                }
            }
        }
        return fragmentList;
    }
}
