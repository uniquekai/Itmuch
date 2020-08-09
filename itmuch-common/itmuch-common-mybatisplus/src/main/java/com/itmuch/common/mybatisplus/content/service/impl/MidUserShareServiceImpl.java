package com.itmuch.common.mybatisplus.content.service.impl;

import com.itmuch.common.mybatisplus.content.entity.MidUserShare;
import com.itmuch.common.mybatisplus.content.mapper.MidUserShareMapper;
import com.itmuch.common.mybatisplus.content.service.IMidUserShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户-分享中间表【描述用户购买的分享】 服务实现类
 * </p>
 *
 * @author uniquek
 * @since 2019-12-27
 */
@Service
public class MidUserShareServiceImpl extends ServiceImpl<MidUserShareMapper, MidUserShare> implements IMidUserShareService {

}
