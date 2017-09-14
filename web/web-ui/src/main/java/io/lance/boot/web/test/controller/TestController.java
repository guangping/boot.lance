package io.lance.boot.web.test.controller;

import io.lance.boot.common.core.exception.EbsException;
import io.lance.boot.common.core.util.Constants;
import io.lance.boot.common.core.util.JsonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

/**
 * Author: Lance.
 * Date: 2017-09-13 15:44
 * Desc:
 */
@Controller
@RequestMapping("/test")
public class TestController {

    private static final Logger logger = LogManager.getLogger();

    @RequestMapping(value = "/error3")
    public Callable<String> error3() {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                logger.info("test sync controller");
                return "error/404";
            }
        };
    }


    @ResponseBody
    @RequestMapping(value = "/index", produces = Constants.MEDIA_TYPE_JSON)
    public JsonResult index() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setMessage("中文");
        return jsonResult;
    }

    @ResponseBody
    @RequestMapping(value = "/error", produces = Constants.MEDIA_TYPE_JSON)
    public JsonResult error() {
        JsonResult jsonResult = new JsonResult();
        if (true) {
            throw new EbsException("测试错误");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/error2")
    public String error2(ModelMap model) {
        model.addAttribute("test", "test");
        return "test/index";
    }

    @RequestMapping(value = "/index2")
    public String index2() {
        if (true) {
            throw new EbsException("测试错误");
        }
        return "test/index";
    }

}
