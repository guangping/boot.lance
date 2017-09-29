package io.lance.boot.common.core.util;

import io.lance.boot.common.core.exception.EbsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.List;

/**
 * @desc: validator验证辅助
 * @author: lance
 * @time: 2017-09-28 15:32
 */
public class BindingResultUtil {

    public static void validator(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            ObjectError oe = list.get(0);
            //String fieldName = null;
            if (oe instanceof FieldError) {
                FieldError fieldError = (FieldError) oe;
                //fieldName = fieldError.getField();

                throw new EbsException(fieldError.getDefaultMessage());
            }
        }
    }
}
