package com.xxh.web.util.http;

import com.alibaba.fastjson.JSONObject;
import com.xxh.web.util.format.JsonResponseParser;
import com.xxh.web.util.format.ResponseParser;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient助手类，主要实现了请求响应数据的格式化，方便开发调用
 * Created by wulongtao on 2017/5/15.
 */
public class HttpClientHelper {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientHelper.class);
    private static ResponseParser responseParser = new JsonResponseParser();

    /**
     * 以表单形式发送http post请求，Map形式返回响应结果
     * @param url
     * @param reqParams
     * @return
     */
    public static Map<String, Object> get(String url, Map<String, String> reqParams) {
        String resp = get2Str(url, reqParams);

        return responseParser.parse(resp);
    }

    /**
     * 以表单形式发送http post请求，字符串形式返回响应结果
     * @param url
     * @param reqParams
     * @return
     */
    public static String get2Str(String url, Map<String, String> reqParams) {
        String resp = "";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;

        try {
            URI uri = new URIBuilder()
                    .setPath(url)
                    .setCharset(Consts.UTF_8)
                    .setParameters(map2NameValuePairs(reqParams))
                    .build();

            HttpGet httpGet = new HttpGet(uri);
            response = httpClient.execute(httpGet);

            //logger
            logger.info("Do post raw data,status line=" + response.getStatusLine());


            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, Consts.UTF_8);
            EntityUtils.consume(entity);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resp;
    }

    /**
     * 以rawData形式发送http post请求，Map集合方式返回
     * @param url url
     * @param reqParams 请求参数
     * @return
     */
    public static Map<String, Object> postRawData(String url, Map<String, Object> reqParams) {
        String resp = postRawData2Str(url, reqParams);
        return responseParser.parse(resp);
    }

    /**
     * * 以rawData形式发送http post请求，Map集合方式返回
     * @param url url
     * @param reqParams 请求参数
     * @param responseParser ResponseParser的实现类，实现parse方法
     * @return
     */
    public static Map<String, Object> postRawData(String url, Map<String, Object> reqParams, ResponseParser responseParser) {
        String resp = postRawData2Str(url, reqParams);
        return responseParser.parse(resp);
    }

    /**
     * 以rawData形式发送http post请求，字符串形式返回响应结果
     * @param url url
     * @param reqParams 请求参数
     * @return
     */
    public static String postRawData2Str(String url, Map<String, Object> reqParams) {
        String resp = "";

        StringEntity entityStr = new StringEntity(JSONObject.toJSONString(reqParams), ContentType
                .create("text/plain", Consts.UTF_8));

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(entityStr);
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpPost);

            //logger
            logger.info("Do post raw data,status line=" + response.getStatusLine());


            HttpEntity entity = response.getEntity();
            resp = EntityUtils.toString(entity, "UTF-8");
            EntityUtils.consume(entity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (resp == null || resp.equals("")) {
            return "";
        }

        return resp;
    }

    private static List<NameValuePair> map2NameValuePairs(Map<String, String> map) {
        List<NameValuePair> lstPrams = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            lstPrams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        return lstPrams;
    }
}
