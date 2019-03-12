package com.github.ifrankwang.spring.api.dto.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Frank Wang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OperationRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String tag;
}
