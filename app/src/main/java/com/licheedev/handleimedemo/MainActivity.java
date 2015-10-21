package com.licheedev.handleimedemo;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.licheedev.handleimedemo.wiget.BottomLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener, View.OnFocusChangeListener {

    private ListView mListView;
    private BottomLayout mBottomLayout;
    private Button mBtnAdd;
    private Button mBtnEmoji;
    private Button mBtnVoice;
    private EditText mEtContent;
    private InputMethodManager mInputMethodManager;

    private static final String CONFIG_IME_HEIGHT = "config_ime_height";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initService();
        initViews();
    }

    private void initService() {
        mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
    }

    private void initViews() {
        LinearLayout llParent = (LinearLayout) findViewById(R.id.llParent);
        llParent.getViewTreeObserver().addOnGlobalLayoutListener(new OnImeSizeChangedListener());
        // 消息列表
        mListView = (ListView) findViewById(R.id.lvMsgs);
        mListView.setOnTouchListener(new OnListViewTouchListener());
        ArrayList<Msg> msgs = MsgGenerator.getMsgs();
        mListView.setAdapter(new MsgAdapter(this, msgs));
        mListView.setSelection(msgs.size() - 1);
        // 底部功能区域
        mBottomLayout = (BottomLayout) findViewById(R.id.bottomLayout);
        int size = getPreferences(MODE_PRIVATE).getInt(CONFIG_IME_HEIGHT, 1);
        mBottomLayout.setLayoutHeight(size);
        // 添加按钮
        mBtnAdd = (Button) findViewById(R.id.btnAdd);
        mBtnAdd.setOnClickListener(this);
        // 表情按钮
        mBtnEmoji = (Button) findViewById(R.id.btnEmoji);
        mBtnEmoji.setOnClickListener(this);
        // 语音按钮
        mBtnVoice = (Button) findViewById(R.id.btnVoice);
        mBtnVoice.setOnClickListener(this);
        // 内容编辑框
        mEtContent = (EditText) findViewById(R.id.etContent);
        mEtContent.setOnFocusChangeListener(this);
    }

    private void showArea(BottomLayout.Area area) {
        hideIme();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        mBottomLayout.showArea(area);
    }

    private void hideIme() {
        mInputMethodManager.hideSoftInputFromWindow(mEtContent.getWindowToken(), 0);
        if (mEtContent.isFocused()) {
            mEtContent.clearFocus();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                showArea(BottomLayout.Area.MEDIA);
                break;
            case R.id.btnEmoji:
                showArea(BottomLayout.Area.EMOJI);
                break;
            case R.id.btnVoice:
                showArea(BottomLayout.Area.VOICE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            if (mBottomLayout.getVisibility() == View.GONE) {
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            } else {
                mBottomLayout.showArea(BottomLayout.Area.NONE);
                mBottomLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getWindow()
                            .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                        mBottomLayout.showArea(BottomLayout.Area.GONE);
                    }
                }, 500);
            }
        }
    }

    private class OnImeSizeChangedListener implements ViewTreeObserver.OnGlobalLayoutListener {

        int screenHeight = getResources().getDisplayMetrics().heightPixels;
        int lowerLimit = (int) (screenHeight * 1f / 3);
        int upperLimit = (int) (screenHeight * 2f / 3);
        int max;

        @Override
        public void onGlobalLayout() {
            Rect rect = new Rect();
            getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
            max = rect.bottom > max ? rect.bottom : max;
            int size = max - rect.bottom;
            if (size > lowerLimit && size < upperLimit) {
                if (size != mBottomLayout.getLayoutHeight()) {
                    mBottomLayout.setLayoutHeight(size);
                    getPreferences(MODE_PRIVATE).edit().putInt(CONFIG_IME_HEIGHT, size).apply();
                }
            }

        }
    }

    private class OnListViewTouchListener implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    hideIme();
                    if (mBottomLayout.getVisibility() == View.VISIBLE) {
                        mBottomLayout.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                mBottomLayout.showArea(BottomLayout.Area.GONE);
                            }
                        }, 200);
                    }
                    return true;
                default:
                    return false;
            }

        }
    }

    @Override
    public void onBackPressed() {
        if (mBottomLayout.getVisibility() == View.VISIBLE) {
            mBottomLayout.showArea(BottomLayout.Area.GONE);
        } else {
            super.onBackPressed();
        }
    }
}
