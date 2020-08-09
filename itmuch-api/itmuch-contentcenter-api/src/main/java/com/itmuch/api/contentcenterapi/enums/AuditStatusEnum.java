package com.itmuch.api.contentcenterapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Auther: uniquek
 * @Date: 2020/2/16 19:54
 * @Description:
 */
@Getter
@AllArgsConstructor
public enum  AuditStatusEnum {
    //待审核
    NOT_TET,
    //审核通过
    PASS,
    //审核不通过
    REJECT
}
