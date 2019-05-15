package com.banzhi.statusmanager.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.banzhi.statusmanager.interfaces.ViewLoader;

/**
 * <pre>
 * @author : No.1
 * @time : 2018/9/14.
 * @desciption :
 * @version :
 * </pre>
 */

public class SimpleEmptyView extends ViewLoader {
    public SimpleEmptyView(Context context) {
        super(context);
    }

    @Override
    protected View createView() {
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.GRAY);
        textView.setTextSize(16f);
        textView.setBackgroundColor(Color.WHITE);
        textView.setText("未获取到数据，请点击重试!");
        return textView;
    }
}
