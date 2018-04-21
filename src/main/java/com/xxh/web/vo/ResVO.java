package com.xxh.web.vo;

import com.xxh.web.util.SpringUtil;

/**
 * Created by 小小黑 on 2017/6/25.
 * JSON数据统一使用这个对象包装后返回
 * result：返回状态码（无错误返回0）
 * message：信息（无错误一般返回空字符串）
 * data：附带的额外信息，可以是另外的VO对象或者Map集合
 */
public class ResVO {
    Integer result;
    String message;
    Object data;

    public ResVO() {
    }

    public ResVO(Integer result, String message) {
        this.result = result;
        setMessage(message);
    }

    public ResVO(Integer result, String message, Object data) {
        this.result = result;
        this.data = data;
        setMessage(message);
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = SpringUtil.getMessage(message);
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
