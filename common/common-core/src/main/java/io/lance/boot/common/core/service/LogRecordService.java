package io.lance.boot.common.core.service;

import io.lance.boot.common.core.bean.LogRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @desc: 日志记录
 * @author: lance
 * @time: 2017-10-23 15:12
 */
@Service
public class LogRecordService {

    private static final Logger logger = LogManager.getLogger();


    /**
     * @desc: 记录操作日志
     * @author: lance
     * @time: 2017-10-23 15:16:39
     */
    public void save(LogRecord record) {
        //TODO 调用日志记录服务
    }
}
