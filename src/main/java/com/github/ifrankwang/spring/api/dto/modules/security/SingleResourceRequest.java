package com.github.ifrankwang.spring.api.dto.modules.security;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank Wang
 */
@Data
public class SingleResourceRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String tag;
    private Long parentId;
    @Valid
    private List<Long> operations = new ArrayList<>();
}
