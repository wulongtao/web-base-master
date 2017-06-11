package com.xxh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wulongtao on 2017/5/24.
 */
@Controller
public class IndexController {

    @RequestMapping("/login")
    public String index(ModelMap map, String name, String age) {
        map.addAttribute("host", "http:www....");
        map.addAttribute("name", name);
        map.addAttribute("age", age);
        return "demo/testBasicView";
    }


}
