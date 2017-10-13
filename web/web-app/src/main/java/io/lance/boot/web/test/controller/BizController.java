package io.lance.boot.web.test.controller;


import io.lance.boot.common.core.util.Constants;
import io.lance.boot.common.core.util.JsonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @desc: 服务提供者
 * @author: lance
 * @time: 2017-09-19 15:26
 */
@RestController
public class BizController {

    private static final Logger logger = LogManager.getLogger();




    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public JsonResult<String> getUser() {
        logger.info("getUser=========");
        JsonResult<String> jsonResult = new JsonResult<String>();
        jsonResult.setSuccess(Constants.YES);
        jsonResult.setMessage("Success");
        jsonResult.setData("1");

        return jsonResult;
    }
}
