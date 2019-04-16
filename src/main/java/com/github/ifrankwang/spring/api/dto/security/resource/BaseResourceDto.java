package com.github.ifrankwang.spring.api.dto.security.resource;

import com.github.ifrankwang.spring.api.dto.security.authority.BaseAuthorityDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank Wang
 */
@Data
public class BaseResourceDto {
    private String name;
    private String tag;
    private Long parentId;
    private Boolean protect;
    private List<BaseAuthorityDto> operations = new ArrayList<>();
}
