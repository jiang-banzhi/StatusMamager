package com.banzhi.statusmanager.utils;

import android.os.Handler;
import android.view.View;

/**
 * <pre>
 * author : No.1
 * time : 2017/3/28.
 * desc :
 * </pre>
 */

public class ViewUtils {

    /**
     * 设置view多少毫秒后可以再次点击
     *
     * @param v
     * @param delayMillis
     */
    public static void setDelayedClickable(final View v, int delayMillis) {
        v.setClickable(false);
        setDelayedClickable(v, true, delayMillis);
    }

    private static void setDelayedClickable(final View v, final boolean clickable, int delayMillis) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                v.setClickable(clickable);
                v.setEnabled(true);
            }
        }, delayMillis);
    }
}
