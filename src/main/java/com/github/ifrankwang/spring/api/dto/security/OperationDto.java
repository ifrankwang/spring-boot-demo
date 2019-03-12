package com.github.ifrankwang.spring.api.dto.security;

import lombok.AccessLevel;
import lombok.Data;

/**
 * @author Frank Wang
 */
@Data
public class OperationDto {
    private Long id;
    private String name;
    private String tag;
    private AccessLevel accessLevel;
}
