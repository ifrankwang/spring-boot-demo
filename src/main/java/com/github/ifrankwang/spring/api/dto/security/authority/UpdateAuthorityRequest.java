package com.github.ifrankwang.spring.api.dto.security.authority;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Frank Wang
 */
@Data
public class UpdateAuthorityRequest {
    private List<Long> authorityIdList = new ArrayList<>();
}
