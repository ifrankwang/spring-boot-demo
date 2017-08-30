package com.example.demo.web;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller 示例
 */
@RestController
public class HelloController {

    @ApiOperation(value = "测试url", notes = "正常则返回：Hello World!")
    @RequestMapping("/test")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }
}
