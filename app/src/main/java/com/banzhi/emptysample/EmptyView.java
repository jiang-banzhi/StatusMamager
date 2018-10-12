package com.banzhi.emptysample;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.banzhi.emptylibrary.interfaces.ViewLoader;

/**
 * <pre>
 * @author : No.1
 * @time : 2018/9/14.
 * @desciption :
 * @version :
 * </pre>
 */

public class EmptyView extends ViewLoader {
    String text;

    public EmptyView(Context context, String text) {
        super(context);
        this.text = text;
    }

    @Override
    protected View createView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        TextView textView = new TextView(getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.GRAY);
        textView.setTextSize(16f);
        textView.setText("自定义"+text+"界面");
        frameLayout.addView(textView);
        return frameLayout;
//        return LayoutInflater.from(getContext()).inflate(R.layout.empty,  null);
    }
}
