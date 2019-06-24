package com.hyssop.framework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:    java类作用描述
 * @Author:         yc
 * @CreateDate:     2019/5/8$ 14:47$
 * @UpdateUser:     yc
 * @UpdateDate:     2019/5/8$ 14:47$
 * @UpdateRemark:   修改内容
 * @Version:        1.0
 */
@Controller
public class HelloController {

    @RequestMapping("/test")
    public String test(){
        return "welcome";
    }
}