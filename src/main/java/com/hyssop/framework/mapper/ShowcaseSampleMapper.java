package com.hyssop.framework.mapper;

import com.hyssop.framework.entity.showcase.ShowcaseSampleVo;

import org.springframework.stereotype.Repository;

/**
 *
 * @author zhjie.zhang
 * @date 2019/5/7
 */
@Repository
public interface ShowcaseSampleMapper {
    ShowcaseSampleVo selectByPrimaryKey(Long id);
}
