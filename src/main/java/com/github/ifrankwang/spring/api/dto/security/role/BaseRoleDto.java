package com.github.ifrankwang.spring.api.dto.security.role;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Frank Wang
 */
@Data
public class BaseRoleDto {
    @NotBlank
    private String name;
    private Boolean generic = false;
}
