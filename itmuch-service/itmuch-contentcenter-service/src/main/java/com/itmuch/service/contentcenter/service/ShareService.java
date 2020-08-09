package com.itmuch.service.contentcenter.service;

import com.baomidou.mybatisplus.extension.api.R;
import com.itmuch.api.contentcenterapi.enums.AuditStatusEnum;
import com.itmuch.api.contentcenterapi.model.accept.ShareAuditDTO;
import com.itmuch.api.contentcenterapi.model.returnf.ShareInfoReturnModel;
import com.itmuch.api.usercenterapi.feignclient.UserFeignClient;
import com.itmuch.common.mybatisplus.content.entity.RocketmqTransactionLog;
import com.itmuch.common.mybatisplus.content.entity.Share;
import com.itmuch.common.mybatisplus.content.mapper.RocketmqTransactionLogMapper;
import com.itmuch.common.mybatisplus.content.mapper.ShareMapper;
import com.itmuch.common.mybatisplus.user.entity.User;
import com.itmuch.core.domain.dto.UserAddBonusMsgDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: uniquek
 * Date: 2019/12/28
 * Time: 6:43 下午
 * Description:
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareService {
    @Autowired
    ShareMapper shareMapper;
    @Autowired
    UserFeignClient userFeignClient;
    //注入rocketMQ
    private final RocketMQTemplate rocketMQTemplate;

    private final RocketmqTransactionLogMapper rocketmqTransactionLogMapper;

    /**
     * 功能描述:
     *
     * @param: id
     * @return: 装配之后的笔记信息模型
     * @auther: uniquek
     * @date: 2020/1/26 6:53 下午
     */
    public ShareInfoReturnModel shareInfoById(Integer id) {
        Share share = shareMapper.selectById(id);
        log.info("查询到的笔记 = {}", share);
        Integer userid = share.getUserId();
        User user = userFeignClient.userInfoByid(userid).getData();
        log.info("查询到的用户为 : {}", user);
        ShareInfoReturnModel shareInfoReturnModel = new ShareInfoReturnModel();
        BeanUtils.copyProperties(share, shareInfoReturnModel);
        shareInfoReturnModel.setWxNickname(user.getWxNickname());
        return shareInfoReturnModel;
    }
    /**
     * 功能描述:管理员审核分享业务实现
     * @param: [笔记的id, shareAuditDTO]
     * @return: com.baomidou.mybatisplus.extension.api.R<com.itmuch.common.mybatisplus.content.entity.Share>
     * @auther: uniquek
     * @date: 2020/2/16 8:17 下午
     */
    public R<Share> auditById(Integer id, ShareAuditDTO shareAuditDTO){

        //1,查询share是否存在，不存在或者当前的audit_status != NOT_YET那个就抛异常
        Share share = this.shareMapper.selectById(id);
        if (share == null){
            log.error("1查询到的share为：{}",share);
            throw new IllegalArgumentException("参数非法,该分享不存在");
        }
        if (!Objects.equals("NOT_YET",share.getAuditStatus())){
            log.error("2查询到的share为：{}",share);
            throw new IllegalArgumentException("参数非法,该分享审核已被审核");
        }
        // 3. 如果是PASS，那么发送消息给rocketMQ,让用户中心去消费，并为发布人添加积分
        if (AuditStatusEnum.PASS.equals(shareAuditDTO.getAuditStatusEnum())){
            log.info("0查询到的share为：{}",share);
            String transactionId = UUID.randomUUID().toString();
            this.rocketMQTemplate.sendMessageInTransaction(
                    "tx-add-bonus-group",
                    "add-bonus",
                    MessageBuilder
                            .withPayload(
                                UserAddBonusMsgDTO.builder()
                                    .userId(share.getUserId())
                                    .bonus(50)
                                    .build()
                    )
                     //heard也有妙用
                     .setHeader(RocketMQHeaders.TRANSACTION_ID, transactionId)
                     .setHeader("share_id",id)
                     .build(),
                    //arg有大用
                    shareAuditDTO
            );
        }else {
            this.auditById(id,shareAuditDTO);
        }


        // 3. 如果是PASS，那么发送消息给rocketMQ,让用户中心去消费，并为发布人添加积分
        //异步执行
//        this.rocketMQTemplate.convertAndSend("add-bonus", UserAddBonusMsgDTO.builder()
//                .userId(share.getUserId())
//                .bonus(50)
//                .build()
//        );
        return R.ok(share);
    }
    @Transactional(rollbackFor = Exception.class)
    public void auditByIdInDB(Integer id,ShareAuditDTO shareAuditDTO) {
        // 2. 审核资源，将状态设为PASS/REJECT
        Share share = Share.builder()
                .id(id)
                .auditStatus(shareAuditDTO.getAuditStatusEnum().toString())
                .reason(shareAuditDTO.getReason())
                .build();
        this.shareMapper.updateById(share);
        // 4.把share写到缓存
    }

    @Transactional(rollbackFor = Exception.class)
    public void auditByIdWithRocketMqLog(Integer id,ShareAuditDTO shareAuditDTO,String transactionId){
        this.auditByIdInDB(id,shareAuditDTO);

        this.rocketmqTransactionLogMapper.insert(
                RocketmqTransactionLog.builder()
                        .transactionId(transactionId)
                        .log("审核分享")
                        .build()
        );
    }
}