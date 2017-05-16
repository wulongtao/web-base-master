package com.xxh.web.util.format;

import java.util.Map;

/**
 * Created by wulongtao on 2017/5/16.
 */
public interface ResponseParser {
    /**
     * 解析http响应消息，可以扩展解析JSON、XML等格式
     * @param resp
     * @return
     */
    Map<String, Object> parse(String resp);
}
