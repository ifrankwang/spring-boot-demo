package com.github.ifrankwang.spring.api.dto.security;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
    @NotEmpty
    private List<String> availableOperations = new ArrayList<>();
}
