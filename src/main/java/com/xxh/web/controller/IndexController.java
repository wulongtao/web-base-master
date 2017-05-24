package com.xxh.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wulongtao on 2017/5/24.
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index() {
        System.out.println("vvvvv");
        return "index";
    }
}
