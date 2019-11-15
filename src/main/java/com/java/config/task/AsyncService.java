package com.java.config.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步任务
 *
 * @author yupan@yijiupi.cn
 * @date 2019/2/13 14:03
 */
@Service
public class AsyncService {

    @Async
    public void test() {
        System.out.println(Thread.currentThread() + "调用异步任务。。。");
    }
}
