package com.xxh.web.util.http;

import com.xxh.web.util.format.XmlResponseParser;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wulongtao on 2017/5/15.
 */
public class HttpCientHelperTest {

    @Test
    public void testPostRawData2Str() {
        String sessionId = UUID.randomUUID().toString();
        Map<String, Object> reqParams = new HashMap<>();
        reqParams.put("id", UUID.randomUUID().toString());
        reqParams.put("clientId", "2OWS");
        reqParams.put("test", "1");
        reqParams.put("sessionId", sessionId);
        reqParams.put("source", "");

//        Map<String, Object> res = HttpClientHelper.postRawData("http://192.168.100.244:18081/accessService", reqParams);
        Map<String, Object> res = HttpClientHelper.postRawData("http://192.168.100.244:18081/accessService"
                , reqParams, new XmlResponseParser());
        System.out.println(res);
    }
}
