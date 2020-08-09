package com.itmuch.common.mybatisplus.content.service.impl;

import com.itmuch.common.mybatisplus.content.entity.Share;
import com.itmuch.common.mybatisplus.content.mapper.ShareMapper;
import com.itmuch.common.mybatisplus.content.service.IShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 分享表 服务实现类
 * </p>
 *
 * @author uniquek
 * @since 2019-12-27
 */
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, Share> implements IShareService {

}
