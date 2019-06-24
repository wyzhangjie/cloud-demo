package com.hyssop.framework.controller;

import com.hyssop.framework.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zhjie.zhang
 * @date 2019/5/7
 */
@RestController
@RequestMapping("/testBoot")
public class ShowcaseSampleController {
    @Autowired
    private UserService userService;

    @RequestMapping("getUser/{id}")
    public String GetUser(@PathVariable Long id) {
        return userService.Sel(id).toString();
    }
}
