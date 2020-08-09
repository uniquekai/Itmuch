package com.itmuch.common.mybatisplus.content.service.impl;

import com.itmuch.common.mybatisplus.content.entity.RocketmqTransactionLog;
import com.itmuch.common.mybatisplus.content.mapper.RocketmqTransactionLogMapper;
import com.itmuch.common.mybatisplus.content.service.IRocketmqTransactionLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * RocketMQ事务日志表 服务实现类
 * </p>
 *
 * @author uniquek
 * @since 2020-03-11
 */
@Service
public class RocketmqTransactionLogServiceImpl extends ServiceImpl<RocketmqTransactionLogMapper, RocketmqTransactionLog> implements IRocketmqTransactionLogService {

}
