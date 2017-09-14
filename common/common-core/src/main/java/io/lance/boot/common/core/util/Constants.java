package io.lance.boot.common.core.util;

/**
 * Author Lance.
 * Date: 2017-09-04 17:45
 * Desc: 常量类
 */
public interface Constants {

    String CHARSET = "UTF-8";

    String YES = "1";

    String NO = "0";

    boolean SUCCESS = true;

    boolean FALSE = false;

    /**
     * 日期格式化格式
     **/
    String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 时间格式化格式
     **/
    String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";

    String TIME_FORMAT = "HH:mm:ss";
    /**
     * 日期格式化格式
     **/
    String DATE_FORMAT_CN = "yyyy年MM月dd日";
    /**
     * 时间格式化格式
     **/
    String DATETIME_FORMAT_CN = "yyyy年MM月dd日 HH时mm分";


    /**
     * 常用符号
     */
    String COMMA = ",";
    String DOT = ".";
    String COMMA_CN = "，";


    /**
     * MediaType application/json;charset=UTF-8
     */
    String MEDIA_TYPE_JSON = "application/json;charset=UTF-8";

    /**
     * http status code
     */
    int STATUS_CODE_404 = 404; //not found

    int STATUS_CODE_500 = 500;

}
