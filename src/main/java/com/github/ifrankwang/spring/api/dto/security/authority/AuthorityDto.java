package com.github.ifrankwang.spring.api.dto.security.authority;

import com.github.ifrankwang.spring.api.dto.security.ApiDto;
import lombok.Data;

/**
 * @author Frank Wang
 */
@Data
public class AuthorityDto {
    private Long id;
    private String name;
    private String tag;
    private ApiDto api;
}
