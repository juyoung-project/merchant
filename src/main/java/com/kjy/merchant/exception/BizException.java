package com.kjy.merchant.exception;

import com.kjy.merchant.common.Code;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BizException extends RuntimeException {


    private Code code;
    private String customErrorMsg;


    public BizException( Code code ) {
        super(code.getResMsg());
        this.code = code;
    }

    public BizException(Code code,  String customErrorMsg) {
        super(customErrorMsg);
        this.code = code;
        this.customErrorMsg = customErrorMsg;
    }

}
