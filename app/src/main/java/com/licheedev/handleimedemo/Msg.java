package com.licheedev.handleimedemo;

/**
 * Created by John on 2015/10/21.
 */
public class Msg {
    
    public static final int ME = 0;
    public static final int OTHER = 1;
    
    private String mContent;
    private int who;

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getWho() {
        return who;
    }

    public void setWho(int who) {
        this.who = who;
    }
}
