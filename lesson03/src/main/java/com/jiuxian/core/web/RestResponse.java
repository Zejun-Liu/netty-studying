package com.jiuxian.core.web;

import lombok.Data;

@Data
public class RestResponse<T> {

    private Integer code;
    private String message;
    private T data;

    public RestResponse() {
        this.code = 200;
    }

    public RestResponse(T data) {
        this.code = 200;
        this.data = data;
    }

    public RestResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestResponse(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static RestResponse build() {
        return new RestResponse();
    }
}
