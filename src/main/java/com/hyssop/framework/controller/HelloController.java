package com.hyssop.framework.controller;

import com.hyssop.framework.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:    controller入口
 * @Author:         yc
 * @CreateDate:     2019/5/8$ 14:47$
 * @UpdateUser:     yc
 * @UpdateDate:     2019/5/8$ 14:47$
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Controller
public class HelloController {
    @Autowired
    private UserService userService;
    @RequestMapping("/test")
    public String test(){
        return "welcome";
    }
    @RequestMapping("/async")
    @ResponseBody
    public String async(){
        System.out.println("####IndexController####   1");
        userService.sendSms();
        System.out.println("####IndexController####   4");

        return "success";
    }
}