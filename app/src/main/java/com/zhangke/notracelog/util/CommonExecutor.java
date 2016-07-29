package com.zhangke.notracelog.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommonExecutor {
    private static ExecutorService service = Executors.newFixedThreadPool(5);

    public static void executor(Runnable command) {
        service.execute(command);
    }
}
