package com.licheedev.handleimedemo;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by John on 2015/10/21.
 */
public class MsgGenerator {
    private static final String TEXT =
        "是地方啦涉及到法律上的风景爱上了的积分啦圣诞节疯了啊受入围区瑞强为融洽额我扔了进去玩了" 
            + "软禁起来温柔热情为了让犬瘟热犬瘟热日期为热情为热情温柔人情味人情味让我去日期为热情为" 
            + "热情温柔认为让我去额4认为人情味热情韦尔奇为发撒的发生的发父亲为人扑去我陪U日期金额温" 
            + "柔日期为人情味如铺车自行车自行车2发撒的发生的发父亲为人扑去我陪U日期金额温柔但发撒都" 
            + "发生地方发撒的发生的发父亲为人扑去我陪U日期金额温柔到了房间爱上了对方家发撒旦法撒发撒" 
            + "的发生的发我去而且我而且为热情为热情为融洽热情文如其人确认为武器个地方广东省分公司的" 
            + "故事的故事公司的分公司的风格多少个";

    public static ArrayList<Msg> getMsgs() {

        Random random = new Random(System.currentTimeMillis());
        ArrayList<Msg> list = new ArrayList<>();
        int readed = 0;
        while (TEXT.length() - readed > 0) {
            int i = random.nextInt(10) + 1;
            int end = readed + i;
            end = end > TEXT.length() ? TEXT.length() : end;
            Msg msg = new Msg();
            msg.setContent(TEXT.substring(readed, end));
            msg.setWho(i % 2);
            list.add(msg);
            readed += i;
        }
        return list;
    }


}
