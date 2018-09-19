package com.banzhi.emptylibrary.view;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.banzhi.emptylibrary.interfaces.ViewLoader;

/**
 * <pre>
 * @author : No.1
 * @time : 2018/9/14.
 * @desciption :
 * @version :
 * </pre>
 */

public class SimpleLoadingView extends ViewLoader {

    public SimpleLoadingView(Context context) {
        super(context);
    }

    @Override
    protected View createView() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        linearLayout.setBackgroundColor(Color.WHITE);
        layoutParams.gravity = Gravity.CENTER;
        linearLayout.setLayoutParams(layoutParams);
        ProgressBar progressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyle);
        LinearLayout.LayoutParams barParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        barParams.gravity = Gravity.CENTER;
        progressBar.setBackgroundColor(Color.WHITE);
        progressBar.setLayoutParams(barParams);
        linearLayout.addView(progressBar, barParams);
        return linearLayout;
    }
}
