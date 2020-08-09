package com.itmuch.api.contentcenterapi.model.accept;

import com.itmuch.api.contentcenterapi.enums.AuditStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: uniquek
 * @Date: 2020/2/16 19:51
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShareAuditDTO {
    //审核状态
    private AuditStatusEnum auditStatusEnum;
    //原因
    private String reason;
}
