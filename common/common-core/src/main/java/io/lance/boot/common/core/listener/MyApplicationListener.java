package io.lance.boot.common.core.listener;

import io.lance.boot.common.core.annotation.Apv;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @desc: spring容器初始化后执行
 * @author: lance
 * @time: 2017-10-20 15:33
 */
@Apv
@Component
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private final static Logger logger = LogManager.getLogger();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();

       /* List<String> list = Arrays.asList(context.getBeanDefinitionNames());
        logger.info("获取所有bean");
        list.forEach(beanName -> {

        });*/
        Map<String,Object> beanMap=context.getBeansWithAnnotation(Apv.class);
        System.out.println(beanMap);

    }
}
