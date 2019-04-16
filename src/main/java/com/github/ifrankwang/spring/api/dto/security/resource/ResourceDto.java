package com.github.ifrankwang.spring.api.dto.security.resource;

import com.github.ifrankwang.spring.api.dto.security.authority.AuthorityDto;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank Wang
 */
@Data
public class ResourceDto {
    private Long id;
    private String name;
    private String tag;
    private Long parentId;
    private Boolean protect;
    private List<AuthorityDto> operations = new ArrayList<>();
    private List<ResourceDto> children = new ArrayList<>();
}
