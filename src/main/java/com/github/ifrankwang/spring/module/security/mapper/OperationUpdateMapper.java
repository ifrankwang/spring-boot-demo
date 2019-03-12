package com.github.ifrankwang.spring.module.security.mapper;

import com.github.ifrankwang.spring.module.security.entity.OperationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author Frank Wang
 */
@Mapper
public interface OperationUpdateMapper {
    OperationUpdateMapper INSTANCE = Mappers.getMapper(OperationUpdateMapper.class);

    OperationEntity update(@MappingTarget OperationEntity dest, OperationEntity source);
}
