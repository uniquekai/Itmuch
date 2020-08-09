package com.itmuch.api.contentcenterapi.feignclient;

import com.baomidou.mybatisplus.extension.api.R;
import com.itmuch.api.contentcenterapi.model.accept.ShareAuditDTO;
import com.itmuch.common.mybatisplus.content.entity.Share;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "content-center")
public interface ShareAdminFeignClient {
    @PutMapping("/admin/shares/audit/{id}")
    public R<Share> auditByID(@PathVariable Integer id, @RequestBody ShareAuditDTO shareAuditDTO);
}
