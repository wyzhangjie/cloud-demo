package com.hyssop.framework.service;

import com.hyssop.framework.entity.showcase.ShowcaseSampleVo;
import com.hyssop.framework.mapper.ShowcaseSampleMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhjie.zhang on 2019/5/7.
 */
@Service
public class UserService {
    @Autowired
    ShowcaseSampleMapper userMapper;
    public ShowcaseSampleVo Sel(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
