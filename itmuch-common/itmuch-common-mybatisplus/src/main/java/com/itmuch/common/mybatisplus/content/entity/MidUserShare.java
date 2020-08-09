package com.itmuch.common.mybatisplus.content.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户-分享中间表【描述用户购买的分享】
 * </p>
 *
 * @author uniquek
 * @since 2019-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "MidUserShare对象", description = "用户-分享中间表【描述用户购买的分享】")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MidUserShare implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "share.id")
    private Integer shareId;

    @ApiModelProperty(value = "user.id")
    private Integer userId;


}
