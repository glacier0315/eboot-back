package com.glacier.common.utils;

import java.util.UUID;

/**
 * @author hebin
 * @version 1.0
 * @description ID工具类
 * @date 2019-10-25 16:18
 */
public class IdGen {

    private IdGen() {
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
