package com.xxh.web.controller;

import com.xxh.web.controller.bean.Cat;
import com.xxh.web.controller.bean.User;
import com.xxh.web.controller.validator.UserValidator;
import com.xxh.web.service.UserService;
import com.xxh.web.util.SpringUtil;
import com.xxh.web.util.UploadFile;
import com.xxh.web.vo.demo.Animal;
import com.xxh.web.vo.demo.DemoVo;
import com.xxh.web.vo.ResVO;
import com.xxh.web.vo.demo.XmlDemoVo;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 所有controller的测试方法
 *
 * @author wulongtao
 * @date 2017/5/25
 */
@Controller
@RequestMapping("/demo")
public class DemoController {
    Logger logger = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    @Qualifier("userValidator")
    private UserValidator userValidator;
    @Autowired
    @Qualifier("userService")
    private UserService userService;


    /**
     * 测试基本的传参，返回视图
     * @param map
     * @param name
     * @param age
     * @return
     */
    @RequestMapping("/testBasicView")
    public String testBasicView(ModelMap map, String name, String age) {
        map.addAttribute("host", "http:www....");
        map.addAttribute("name", name);
        map.addAttribute("age", age);
        return "demo/testBasicView";
    }

    @RequestMapping("/testJs")
    public String testJs() {
        return "index";
    }

    @RequestMapping("/testI18N")
    public String testI18N() {
        return "demo/testI18N";
    }


    //测试返回json数据
    @ResponseBody
    @RequestMapping("/testJson")
    public Map<String, Object> testJson(String id) {
        logger.debug("eeeeeeee");
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("id", id);
        map.put("result", 0);
        map.put("message", "成功 success");
        map.put("data", data);

        return map;
    }

    @ResponseBody
    @RequestMapping("/testMyBatis")
    public ResVO testMyBatis(Integer id) {
        return new ResVO(0, "", userService.findUserByUserId(id));
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
        Map<String, Object> mRet = new HashMap<>(2);
        mRet.put("result", demoVo);
        return mRet;
    }


    @RequestMapping("/testXml")
    public @ResponseBody XmlDemoVo testXml() {
        XmlDemoVo xmlDemoVo = new XmlDemoVo(1, "name", "eee");
        return xmlDemoVo;
    }

    @RequestMapping("/testXmlReq")
    public @ResponseBody Animal testXmlReq(@RequestBody Animal animal) {
        System.out.println(animal);
        animal.setName("abc");
        System.out.println(animal);
        return animal;
    }

    @RequestMapping("/testXmlReq2")
    public @ResponseBody XmlDemoVo testXmlReq2(@RequestBody XmlDemoVo xmlDemoVo) {
        System.out.println(xmlDemoVo);
        xmlDemoVo.setName("abc");
        System.out.println(xmlDemoVo);
        return xmlDemoVo;
    }

    @ResponseBody
    @RequestMapping("/testResVO")
    public ResVO testResVO() {
        ResVO resVO = new ResVO(1, "test", "test data");

        return resVO;
    }

    @ResponseBody
    @RequestMapping("testDataConvert1")
    public DemoVo testDataConvert1(@ModelAttribute DemoVo demoVo) {
        demoVo.setName("change");
        return demoVo;
    }

    @ResponseBody
    @RequestMapping("/register")
    public ResVO register(@Valid @ModelAttribute User user, Errors errors) {
        System.out.println("user===");
        System.out.println(user);

//        userValidator.validate(user, errors);
        System.out.println("hasErrors===" + errors.hasErrors());
        if (errors.hasErrors()) {
            return new ResVO(-1001, errors.getAllErrors().toString());
        }

        ResVO resVO = new ResVO(1, "test change  3322", user.toString());
        return resVO;
    }

    @ResponseBody
    @RequestMapping("/validate")
    public ResVO validate(@Valid @ModelAttribute Cat cat, Errors errors, BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        if (bindingResult.hasErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                sb.append(SpringUtil.getMessage(fieldError.getDefaultMessage()) + ",");
            }
        }

        return new ResVO(0, "",  sb.toString());
    }

    @ResponseBody
    @RequestMapping("testI18NByCode")
    public ResVO testI18NByCode(HttpServletRequest request) {
        RequestContext requestContext = new RequestContext(request);
        List<String> iList = new ArrayList<>(2);
        iList.add("requestContext===" + requestContext.getMessage("Message.demo.testI18NByCode.user"));
        iList.add("SpringUtil===" + SpringUtil.getMessage("Message.demo.testI18NByCode.user"));

        return new ResVO(0, "Message.demo.testI18NByCode.user",  iList.toString());
    }

    @RequestMapping("/testUpload")
    public String testUpload() {
        System.out.println(System.getProperty("app.root"));

        return "demo/testUpload";
    }

    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResVO upload(@ModelAttribute UploadFile uploadFile) {
        boolean isSuccess = uploadFile.save();
        System.out.println("isSuccess==" + isSuccess);
        if (!uploadFile.save()) {
            return new ResVO(1001, "Message.demo.upload.fail");
        }

        return new ResVO(0, "Message.demo.upload.success", uploadFile.getFilePath());
    }

    @ResponseBody
    @RequestMapping(value = "upload2", method = RequestMethod.POST)
    public ResVO upload2(HttpServletRequest request, @RequestParam("description") String description, @RequestParam("file")MultipartFile file) throws IOException {
        System.out.println("description=" + description);
        if (!file.isEmpty()) {
            String path = request.getServletContext().getRealPath("/images/");
            System.out.println("relative path==" + path);
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);

            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }

            System.out.println("filepath===" + path + File.separator + filename);

            file.transferTo(new File(path + File.separator + filename));

            return new ResVO(0, "Message.demo.upload.success", "description=" + description);
        }

        return new ResVO(1001, "Message.demo.upload.fail", "description=" + description);
    }

    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename, Model model) throws Exception {
        String path = request.getServletContext().getRealPath("/images/");
        File file = new File(path + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        //下载显示的文件名，解决中文乱码问题
        String downloadFileName = new String(filename.getBytes("UTF-8"), "iso-8859-1");
        // 通知浏览器以attachment（下载方式）打开图片
        headers.setContentDispositionFormData("attachment", downloadFileName);
        // application / octet-stream ：二进制流数据（最常见的文件下载）
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        // 201 HttpStatus.CREATED
        return new ResponseEntity<>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    }
}
