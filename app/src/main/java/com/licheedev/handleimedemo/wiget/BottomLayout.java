package com.licheedev.handleimedemo.wiget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.licheedev.handleimedemo.R;

/**
 * Created by John on 2015/10/21.
 */
public class BottomLayout extends FrameLayout {

    private AddMediaView mAddMediaView;
    private View mAddEmojiView;
    private View mAddVoiceView;

    private Area mCurrentArea = Area.GONE;
    
    private int mDefaultHeight;
    
    public enum Area {
        GONE,MEDIA,EMOJI,VOICE,NONE
    }

    public BottomLayout(Context context) {
        super(context);
        init(context);
    }

    public BottomLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BottomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.custom_bottom_layout, this, true);
        mAddMediaView = (AddMediaView) v.findViewById(R.id.addMediaView);
        mAddEmojiView = v.findViewById(R.id.addEmojiView);
        mAddVoiceView = v.findViewById(R.id.addVoiceView);
        mDefaultHeight = getResources().getDimensionPixelSize(R.dimen.default_bottom_area_height);
    }

    public void setLayoutHeight(int height) {
        height = height > mDefaultHeight ? height : mDefaultHeight;
        getLayoutParams().height = height;
    }

    public int getLayoutHeight() {
        return getLayoutParams().height;
    }

    public Area getCurrentArea() {
        return mCurrentArea;
    }

    public void showArea(Area area) {
        if (area == null) {
            area = Area.NONE;
        }
        mCurrentArea = area;
        switch (area) {
            case GONE:
                this.setVisibility(GONE);
                break;
            case MEDIA:
                this.setVisibility(VISIBLE);
                mAddMediaView.setVisibility(VISIBLE);
                mAddEmojiView.setVisibility(GONE);
                mAddVoiceView.setVisibility(GONE);
                break;
            case EMOJI:
                this.setVisibility(VISIBLE);
                mAddMediaView.setVisibility(GONE);
                mAddEmojiView.setVisibility(VISIBLE);
                mAddVoiceView.setVisibility(GONE);
                break;
            case VOICE:
                this.setVisibility(VISIBLE);
                mAddMediaView.setVisibility(GONE);
                mAddEmojiView.setVisibility(GONE);
                mAddVoiceView.setVisibility(VISIBLE);
                break;
            case NONE:
                this.setVisibility(VISIBLE);
                mAddMediaView.setVisibility(GONE);
                mAddEmojiView.setVisibility(GONE);
                mAddVoiceView.setVisibility(GONE);
                break;
        }
    }

    public AddMediaView getAddMediaView() {
        return mAddMediaView;
    }

    public View getAddEmojiView() {
        return mAddEmojiView;
    }

    public View getAddVoiceView() {
        return mAddVoiceView;
    }
}
