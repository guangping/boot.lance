package io.lance.boot.web.test.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc: 心跳检测
 * @author: lance
 * @time: 2017-10-13 17:15
 */
@RestController
public class HealthController {

    private static final Logger logger = LogManager.getLogger();

    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    public String info() {
        logger.info("check heart....");
        return "success";
    }
}