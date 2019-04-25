package com.github.ifrankwang.spring.api.dto.security.authority;

import lombok.Data;

/**
 * @author Frank Wang
 */
@Data
public class BaseAuthorityDto {
    private String name;
    private String tag;
    private Long apiId;
}
