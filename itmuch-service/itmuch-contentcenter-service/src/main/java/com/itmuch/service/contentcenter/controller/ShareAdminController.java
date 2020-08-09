package com.itmuch.service.contentcenter.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.itmuch.api.contentcenterapi.feignclient.ShareAdminFeignClient;
import com.itmuch.api.contentcenterapi.model.accept.ShareAuditDTO;
import com.itmuch.common.mybatisplus.content.entity.Share;
import com.itmuch.service.contentcenter.service.ShareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: uniquek
 * @Date: 2020/2/16 19:46
 * @Description:管理员管理信息的controller
 */
@RestController
@Slf4j
public class ShareAdminController implements ShareAdminFeignClient {

    private final ShareService shareService;

    public ShareAdminController(ShareService shareService) {
        this.shareService = shareService;
    }

    @Override
    public R<Share> auditByID(Integer id, ShareAuditDTO shareAuditDTO) {
        // TODO 认证、授权
        return this.shareService.auditById(id,shareAuditDTO);
    }
}
