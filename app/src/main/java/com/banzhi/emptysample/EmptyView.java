package com.banzhi.emptysample;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

    public EmptyView(Context context) {
        super(context);
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
        textView.setText("来看电视剧覅为了口袋接二胎");
        frameLayout.addView(textView);
        ProgressBar progressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyle);
        progressBar.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        frameLayout.addView(progressBar);
        return frameLayout;
//        return LayoutInflater.from(getContext()).inflate(R.layout.empty,  null);
    }
}
