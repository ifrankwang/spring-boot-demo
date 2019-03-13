package com.github.ifrankwang.spring.api.dto.security;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

/**
 * @author Frank Wang
 */
@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    @JsonFormat(shape = STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    private Boolean enabled;
    private List<String> roles;
}
