package io.lance.boot.common.core.listener;

import io.lance.boot.common.core.annotation.A;
import io.lance.boot.common.core.annotation.Apv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @desc: spring容器初始化后执行
 * @author: lance
 * @time: 2017-10-20 15:33
 */
@Component
public class ApplicationInitListener implements ApplicationListener<ContextRefreshedEvent>, A {

    private final static Logger logger = LogManager.getLogger();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.info("容器初始化后执行...");

        ApplicationContext context = event.getApplicationContext();

       /* List<String> list = Arrays.asList(context.getBeanDefinitionNames());
        logger.info("获取所有bean");
        list.forEach(beanName -> {

        });*/
        Map<String, Object> beanMap = context.getBeansWithAnnotation(Apv.class);
        System.out.println(beanMap);

    }
}
