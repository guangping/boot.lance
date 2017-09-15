package io.lance.boot.common.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.google.common.collect.Lists;
import io.lance.boot.common.core.util.Constants;
import io.lance.boot.common.web.freemarker.template.JsonDirectiveModel;
import io.lance.boot.common.web.freemarker.template.SubStringCn;
import io.lance.boot.common.web.interceptor.GuestPageInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Author Lance.
 * Date: 2017-09-01 17:27
 * Desc: mvc配置
 */
@Configuration
public class SpringWebConfig extends WebMvcConfigurerAdapter {
    private static final Logger logger = LogManager.getLogger();

    @Primary
    @Bean
    public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        return adapter;
    }

    @Primary
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        return mapping;
    }

    /**
     * 使用fastjson作为json解析
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteSlashAsSpecial,
                SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteEnumUsingToString,
                SerializerFeature.BrowserCompatible,
                SerializerFeature.WriteNullStringAsEmpty
        );
        fastJsonConfig.setDateFormat(Constants.DATETIME_FORMAT);
        fastConverter.setFastJsonConfig(fastJsonConfig);

        //设置支持类型
        List<MediaType> types = Lists.newArrayList();
        MediaType plain = new MediaType("text", "plain", Charset.forName("UTF-8"));
        types.add(plain);

        MediaType html = new MediaType("text", "html", Charset.forName("UTF-8"));
        types.add(html);

        MediaType xml = new MediaType("text", "xml", Charset.forName("UTF-8"));
        types.add(xml);

        MediaType json = new MediaType("application", "json", Charset.forName("UTF-8"));
        types.add(json);

        fastConverter.setSupportedMediaTypes(types);
        return new HttpMessageConverters(fastConverter);
    }

    @Bean
    public HttpMessageConverter<String> stringHttpMessageConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        List<MediaType> types = Lists.newArrayList();
        MediaType plain = new MediaType("text", "plain", Charset.forName("UTF-8"));
        types.add(plain);

        MediaType html = new MediaType("text", "html", Charset.forName("UTF-8"));
        types.add(html);

        MediaType xml = new MediaType("text", "xml", Charset.forName("UTF-8"));
        types.add(xml);

        converter.setSupportedMediaTypes(types);
        return converter;
    }

    /**
     * @desc: 配置拦截器
     * @author: lance
     * @time: 2017-09-14 14:25:36
     */
    public void addInterceptors(InterceptorRegistry registry) {
        logger.info("拦截器配置 start ......");
        registry.addInterceptor(new LocaleChangeInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(new GuestPageInterceptor()).addPathPatterns("/**");

        super.addInterceptors(registry);
    }

    /**
     * @desc: 国际化配置
     * @author: lance
     * @time: 2017-09-14 14:50:00
     */
    @Bean
    public LocaleResolver localeResolver() {
        logger.info("国际化配置 start ......");
        CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return localeResolver;
    }

    /**
     * freemarker相关
     */
    @Autowired
    private freemarker.template.Configuration configuration;

    /**
     * @desc: 配置freemarker
     * @author: lance
     * @time: 2017-09-14 16:13:13
     */
    @PostConstruct
    public void freemarkerConfig() {
        logger.info("freemarker配置 start ......");
        configuration.addAutoImport("i18n", "ftl/spring.ftl");
        configuration.addAutoImport("c", "ftl/common.ftl");
        configuration.setLocale(Locale.CHINESE);
        configuration.setURLEscapingCharset(Constants.CHARSET);
        configuration.setDateTimeFormat(Constants.DATETIME_FORMAT);
        configuration.setDateFormat(Constants.DATE_FORMAT);
        configuration.setTimeFormat(Constants.TIME_FORMAT);
        configuration.setNumberFormat("0.######");
        configuration.setDefaultEncoding(Constants.CHARSET);

        configuration.setSharedVariable("SubStringCn", new SubStringCn());
        configuration.setSharedVariable("Json", new JsonDirectiveModel());
    }

    /**
     * @desc: 自定义线程池
     * @author: lance
     * @time: 2017-09-15 10:35:59
     */
    @Bean
    public Executor taskExecutor() {
        logger.info("线程池配置 start ......");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(50);
        executor.setQueueCapacity(1024);
        executor.setThreadNamePrefix("Executor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

}
