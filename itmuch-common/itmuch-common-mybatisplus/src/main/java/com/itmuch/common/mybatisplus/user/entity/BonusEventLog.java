package com.itmuch.common.mybatisplus.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 积分变更记录表
 * </p>
 *
 * @author uniquek
 * @since 2019-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "BonusEventLog对象", description = "积分变更记录表")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BonusEventLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "user.id")
    private Integer userId;

    @ApiModelProperty(value = "积分操作值")
    private Integer value;

    @ApiModelProperty(value = "发生的事件")
    private String event;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "描述")
    private String description;


}
