package com.longjunwang.wchatcommon.util;

import lombok.Getter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadPoolUtil {

    @Getter
    private static final ThreadPoolExecutor sendMailExecutor = new ThreadPoolExecutor(2, 2, 30L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
}
