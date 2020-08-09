package com.itmuch.api.contentcenterapi.feignclient;

import com.baomidou.mybatisplus.extension.api.R;
import com.itmuch.api.contentcenterapi.model.returnf.ShareInfoReturnModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "content-center")
public interface ShareFeignClient {
    @GetMapping("share/{id}")
    R<ShareInfoReturnModel> getShare(@PathVariable Integer id);
}
