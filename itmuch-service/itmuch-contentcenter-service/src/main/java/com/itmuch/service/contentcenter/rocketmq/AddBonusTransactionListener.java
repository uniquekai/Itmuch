package com.itmuch.service.contentcenter.rocketmq;

import com.itmuch.api.contentcenterapi.model.accept.ShareAuditDTO;
import com.itmuch.common.mybatisplus.content.entity.RocketmqTransactionLog;
import com.itmuch.common.mybatisplus.content.mapper.RocketmqTransactionLogMapper;
import com.itmuch.service.contentcenter.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;


//该处的txProducerGroup一定要与调用时的rocketMQTemplate.sendMessageInTransaction中的txProducerGroup一致
@RocketMQTransactionListener(txProducerGroup = "tx-add-bonus-group")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AddBonusTransactionListener implements RocketMQLocalTransactionListener {
    private final ShareService shareService;
    private final RocketmqTransactionLogMapper rocketmqTransactionLogMapper;

    //用来执行本地事务
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        log.info("用来执行本地事务arg为：{}",arg);
        MessageHeaders headers = message.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        Integer share_id = Integer.valueOf((String)headers.get("share_id"));
        try{
            this.shareService.auditByIdWithRocketMqLog(share_id, (ShareAuditDTO) arg,transactionId);
            return RocketMQLocalTransactionState.COMMIT;
        }catch (Exception e){
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }
    //本地事务的检查接口
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        MessageHeaders headers = message.getHeaders();
        String transactionId = (String) headers.get(RocketMQHeaders.TRANSACTION_ID);
        Integer share_id = (Integer) headers.get("share_id");
        RocketmqTransactionLog rocketmqTransactionLog = rocketmqTransactionLogMapper.selectOne(
                RocketmqTransactionLog.builder()
                        .transactionId(transactionId)
                        .build()
        );
        log.info("本地事务检查接口查询到的rocketmqTransactionLog：{}",rocketmqTransactionLog);
        if (rocketmqTransactionLog != null) {
            return RocketMQLocalTransactionState.COMMIT;
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }
}
