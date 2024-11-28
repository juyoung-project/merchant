package com.kjy.merchant.exception;

import com.kjy.merchant.common.Code;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private Code code;
    private String customErrorMsg;


    BizException( Code code ) {
        super(code.getResMsg());
    }

    BizException(String customErrorMsg) {
        super(customErrorMsg);
    }

}
