package com.itmuch.setvice.usercontentservice.rockermq;

import com.itmuch.common.mybatisplus.user.entity.BonusEventLog;
import com.itmuch.common.mybatisplus.user.entity.User;
import com.itmuch.common.mybatisplus.user.mapper.BonusEventLogMapper;
import com.itmuch.common.mybatisplus.user.mapper.UserMapper;
import com.itmuch.core.domain.dto.UserAddBonusMsgDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RocketMQMessageListener(consumerGroup = "consumer-group", topic = "add-bonus")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class AddBonusListener implements RocketMQListener<UserAddBonusMsgDTO> {
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;
    @Override
    public void onMessage(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        //1.为用户添加积分
        Integer userId = userAddBonusMsgDTO.getUserId();
        User user = userMapper.selectById(userId);
        Integer bonus = userAddBonusMsgDTO.getBonus();
        user.setBonus(user.getBonus()+ bonus);
        this.userMapper.updateById(user);
        //2.记录到日志表里
        this.bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(bonus)
                        .event("CONTRIBUTE")
                        .createTime(new Date())
                        .description("投稿加积分")
                        .build()
        );
        log.info("积分添加完毕");
    }
}
