package com.xxh.web.util.format;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * Created by wulongtao on 2017/5/16.
 */
public class JsonResponseParser implements ResponseParser {

    @Override
    public Map<String, Object> parse(String resp) {
        JSONObject jsonObject = JSONObject.parseObject(resp);
        return parseFromJson(jsonObject);
    }

    /**
     * 根据JSONObject对象解析成Map对象
     * @param jsonObject
     * @return
     */
    private Map<String, Object> parseFromJson(JSONObject jsonObject) {
        Map<String, Object> mRes = new HashMap<>();
        Iterator<Map.Entry<String, Object>> it = jsonObject.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, Object> param = it.next();

            if (param.getValue() instanceof JSONObject) {
                mRes.put(param.getKey(), parseFromJson((JSONObject) param.getValue()));
            } else if (param.getValue() instanceof JSONArray) {
                mRes.put(param.getKey(), json2List(param.getValue()));
            } else {
                mRes.put(param.getKey(), param.getValue());
            }
        }
        return mRes;
    }

    private List<Object> json2List(Object json) {
        JSONArray arrJson = (JSONArray) json;

        List<Object> lst = new ArrayList<>();
        for (int i = 0; i < arrJson.size(); i++) {
            //解决JSON数组中的元素不是JSONObject的情况
            if (isJSONValid(arrJson.getString(i))) {
                lst.add(parseFromJson(arrJson.getJSONObject(i)));
            } else {
                lst.add(arrJson.getString(i));
            }
        }

        return lst;
    }

    private boolean isJSONValid(String json) {
        try {
            JSONObject.parseObject(json);
        } catch (JSONException e) {
            try {
                JSONArray.parse(json);
            } catch (Exception ex) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
