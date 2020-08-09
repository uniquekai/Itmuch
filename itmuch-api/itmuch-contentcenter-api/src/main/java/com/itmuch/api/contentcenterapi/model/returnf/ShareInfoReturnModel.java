package com.itmuch.api.contentcenterapi.model.returnf;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Created with IntelliJ IDEA.
 * User: uniquek
 * Date: 2019/12/28
 * Time: 6:28 下午
 * Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShareInfoReturnModel {
    private Integer id;

    private Integer userId;

    private String title;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Boolean isOriginal;

    private String author;

    private String cover;

    private String summary;

    private Integer price;

    private String downloadUrl;

    private Integer buyCount;

    private Boolean showFlag;

    private String auditStatus;

    private String reason;

    private String wxNickname;
}