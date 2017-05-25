package com.xxh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wulongtao on 2017/5/25.
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    //测试基本的传参，返回视图
    @RequestMapping("/testBasicView")
    public String testBasicView(ModelMap map, String name, String age) {
        map.addAttribute("host", "http:www....");
        map.addAttribute("name", name);
        map.addAttribute("age", age);
        return "demo/index";
    }


    @ResponseBody
    @RequestMapping("/testJson")
    public Map<String, Object> testJson(String id) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        map.put("result", 0);
        map.put("message", "success");
        map.put("data", data);

        return map;
    }

}
