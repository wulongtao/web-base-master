package com.xxh.web.controller;

import com.xxh.web.vo.wechat.WxMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 小小黑 on 2017/6/25.
 */
@Controller
@RequestMapping("/wechat")
public class WeChatController {
    private final Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @ResponseBody
    @RequestMapping("")
    public String testJson(String signature, String timestamp, String nonce, String echostr, HttpServletRequest request/*, @RequestBody WxMessage wxMessage*/) throws IOException {

        logger.debug("signature=【"+signature+"】");
        logger.debug("timestamp=【"+timestamp+"】");
        logger.debug("nonce=【"+nonce+"】");
        logger.debug("echostr=【"+echostr+"】");
//        logger.debug("wxMessage=【"+wxMessage+"】");
        InputStream bis = request.getInputStream();
        byte[] bytes = new byte[50000];
        bis.read(bytes);
        logger.debug("message=【"+new String(bytes)+"】");

        return echostr;
    }

    @ResponseBody
    @RequestMapping("/index")
    public Map<String, Object> index(String id) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("id", 2);
        map.put("result", 0);
        map.put("message", "成功 success");
        map.put("data", data);

        return map;
    }

}
