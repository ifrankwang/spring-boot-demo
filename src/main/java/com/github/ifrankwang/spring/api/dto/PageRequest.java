package com.github.ifrankwang.spring.api.dto;

import com.github.ifrankwang.utils.page.Pageable;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;


/**
 * @author Frank Wang
 */
@Data
public class PageRequest implements Pageable {
    @Min(1)
    private Integer page = 1;
    @Min(1)
    private Integer size = 10;

    @Override
    @ApiModelProperty(hidden = true)
    public int getLimit() {
        return size;
    }

    @Override
    @ApiModelProperty(hidden = true)
    public int getOffset() {
        return (page - 1) * size;
    }
}
