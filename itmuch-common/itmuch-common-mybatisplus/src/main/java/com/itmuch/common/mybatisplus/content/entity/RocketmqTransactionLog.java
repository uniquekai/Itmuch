package com.itmuch.common.mybatisplus.content.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * RocketMQ事务日志表
 * </p>
 *
 * @author uniquek
 * @since 2020-03-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="RocketmqTransactionLog对象", description="RocketMQ事务日志表")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RocketmqTransactionLog extends Wrapper<RocketmqTransactionLog> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "事务id")
    @TableField("transaction_Id")
    private String transactionId;

    @ApiModelProperty(value = "日志")
    private String log;


    @Override
    public RocketmqTransactionLog getEntity() {
        return null;
    }

    @Override
    public MergeSegments getExpression() {
        return null;
    }

    @Override
    public String getSqlSegment() {
        return null;
    }
}
