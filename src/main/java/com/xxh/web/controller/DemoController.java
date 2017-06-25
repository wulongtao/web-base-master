package com.xxh.web.controller;

import com.xxh.web.vo.demo.Animal;
import com.xxh.web.vo.demo.DemoVo;
import com.xxh.web.vo.ResVO;
import com.xxh.web.vo.demo.XmlDemoVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 所有controller的测试方法
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
        return "demo/testBasicView";
    }


    //测试返回json数据
    @ResponseBody
    @RequestMapping("/testJson")
    public Map<String, Object> testJson(String id) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        map.put("result", 0);
        map.put("message", "成功 success");
        map.put("data", data);

        return map;
    }

    //测试只接收post请求
    @ResponseBody
    @RequestMapping(value = "/testPostReq", method = RequestMethod.POST)
    public Map<String, Object> testPostReq() {
        Map<String, Object> map = new HashMap<>();
        map.put("result", 0);
        map.put("message", "");
        map.put("data", "");
        return map;
    }

    //params属性，request中必须包含此参数，才让方法处理
    @ResponseBody
    @RequestMapping(value = "/testParams", method = RequestMethod.GET, params = "p=p")
    public String testParams() {
        return "testParams";
    }

    //vo对象映射到视图
    @RequestMapping(value = "/testVoView")
    public String testVoView(ModelMap map, Integer id, String name) {
        DemoVo demoVo = new DemoVo(id, name);
        map.addAttribute("demoVo", demoVo);
        return "demo/demoVo";
    }

    //获取动态uri中的参数
    @ResponseBody
    @RequestMapping("/testPathVariable/{id}")
    public String testPathVariable(@PathVariable String id) {
        return id;
    }

    //获取cookie中的值测试
    @ResponseBody
    @RequestMapping(value = "/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID", defaultValue = "no value") String sessionId) {
        return sessionId;
    }

    //RequestBody用法——将json对象映射成Vo对象。（注意这里content-type为：application/json，body中参数是json对象）
    //如果返回值是string，会出现中文乱码，未解决
    @ResponseBody
    @RequestMapping("/testRequestBody")
    public Map<String, Object> testRequestBody(@RequestBody DemoVo demoVo) {
        demoVo.setName("修改后的demoVo");
        Map<String, Object> mRet = new HashMap<>();
        mRet.put("result", demoVo);
        return mRet;
    }


    @RequestMapping("/testXml")
    public @ResponseBody XmlDemoVo testXml() {
        XmlDemoVo xmlDemoVo = new XmlDemoVo(1, "name", "eee");
        return  xmlDemoVo;
    }

    @RequestMapping("/testXmlReq")
    public @ResponseBody Animal testXmlReq(@RequestBody Animal animal) {
        System.out.println(animal);
        animal.setName("abc");
        System.out.println(animal);
        return  animal;
    }

    @RequestMapping("/testXmlReq2")
    public @ResponseBody XmlDemoVo testXmlReq2(@RequestBody XmlDemoVo xmlDemoVo) {
        System.out.println(xmlDemoVo);
        xmlDemoVo.setName("abc");
        System.out.println(xmlDemoVo);
        return  xmlDemoVo;
    }

    @ResponseBody
    @RequestMapping("/testResVO")
    public ResVO testResVO() {
        ResVO resVO = new ResVO(1, "test", "test data");

        return resVO;
    }
}
