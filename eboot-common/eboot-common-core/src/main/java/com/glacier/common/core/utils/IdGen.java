package com.glacier.common.core.utils;

import java.util.UUID;

/**
 * @author glacier
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
