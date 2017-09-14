package io.lance.boot.common.web.controller;

import org.springframework.context.i18n.LocaleContextHolder;

/**
 * Author: Lance.
 * Date: 2017-09-14 13:38
 * Desc:
 */
public class BaseController {

    public void init() {
        //获取当前语言环境
        LocaleContextHolder.getLocale();
    }

}
