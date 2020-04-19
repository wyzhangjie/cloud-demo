package com.hyssop.framework.service.impl;

import com.hyssop.framework.entity.Terminal;
import com.hyssop.framework.mapper.TerminalMapper;
import com.hyssop.framework.service.ITerminalService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 机具信息表 服务实现类
 * </p>
 *
 * @author jie.zhang
 * @since 2019-08-08
 */
@Service
public class TerminalServiceImpl extends ServiceImpl<TerminalMapper, Terminal> implements ITerminalService {

}
