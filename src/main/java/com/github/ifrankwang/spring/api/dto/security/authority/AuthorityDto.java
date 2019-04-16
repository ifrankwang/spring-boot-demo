package com.github.ifrankwang.spring.api.dto.security.authority;

import com.github.ifrankwang.spring.api.dto.security.ApiDto;
import com.github.ifrankwang.spring.module.security.enums.Operations;
import lombok.Data;

/**
 * @author Frank Wang
 */
@Data
public class AuthorityDto {
    private Long id;
    private Operations operation;
    private ApiDto api;
}
