package com.itmuch.core.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonusMsgDTO {
//    为谁添加积分
    private Integer userId;
//    添加多少积分
    private Integer bonus;
}
