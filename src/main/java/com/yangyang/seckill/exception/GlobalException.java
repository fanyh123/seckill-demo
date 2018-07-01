package com.yangyang.seckill.exception;

import com.yangyang.seckill.result.CodeMsg;

public class GlobalException extends RuntimeException {
    private CodeMsg codeMsg;
    public GlobalException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg =codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
}
