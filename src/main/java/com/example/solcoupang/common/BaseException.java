package com.example.solcoupang.common;

public class BaseException  extends RuntimeException{

    private BaseResponseStatus status;

    public BaseException(BaseResponseStatus status) {
        super(status.getMessage());
        this.printStackTrace();
        this.status = status;
    }

    public BaseResponseStatus getStatus() {
        return this.status;
    }

    public void setStatus(final BaseResponseStatus status) {
        this.status = status;
    }
}
