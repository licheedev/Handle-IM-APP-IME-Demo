package com.licheedev.handleimedemo.wiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import com.licheedev.handleimedemo.R;

/**
 * Created by John on 2015/10/21.
 */
public class AddMediaView extends FrameLayout {
    public AddMediaView(Context context) {
        super(context);
        init(context);
    }

    public AddMediaView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddMediaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.custom_add_media_view, this, true);
    }
}
