package com.example.common.util;

import java.util.Random;

/**
 * @author zk
 * @Description:生成唯一的主键id
 * @date 2019-09-18 9:38
 */
public class KeyUtil {

    /**
     * 时间+随机数
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        int i = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(i);
    }


}
