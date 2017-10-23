package io.lance.boot.web;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @desc: 容器启动之前执行
 * @author: lance
 * @time: 2017-10-23 14:50
 */
@Component
public class BeforeApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("before application start ...");
    }
}
