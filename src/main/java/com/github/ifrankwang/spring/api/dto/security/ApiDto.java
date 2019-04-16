package com.github.ifrankwang.spring.api.dto.security;

import com.github.ifrankwang.spring.module.security.enums.ApiMethod;
import lombok.Data;

/**
 * @author Frank Wang
 */
@Data
public class ApiDto {
    private Long id;
    private ApiMethod method;
    private String path;
    private String name;
}
