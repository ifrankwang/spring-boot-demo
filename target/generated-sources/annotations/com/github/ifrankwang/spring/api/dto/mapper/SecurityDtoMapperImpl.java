package com.github.ifrankwang.spring.api.dto.mapper;

import com.github.ifrankwang.spring.api.dto.security.*;
import com.github.ifrankwang.spring.module.security.entity.OperationEntity;
import com.github.ifrankwang.spring.module.security.entity.ResourceEntity;
import com.github.ifrankwang.spring.module.security.exception.OperationNotFoundException;
import com.github.ifrankwang.spring.module.security.exception.ResourceNotFoundException;
import com.github.ifrankwang.spring.module.security.mapper.OperationIdMapper;
import com.github.ifrankwang.spring.module.security.mapper.ResourceIdMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.List;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-03-12T13:55:25+0800",
    comments = "version: 1.3.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
@Component
public class SecurityDtoMapperImpl implements SecurityDtoMapper {

    @Autowired
    private ResourceIdMapper resourceIdMapper;
    @Autowired
    private OperationIdMapper operationIdMapper;

    @Override
    public ResourceEntity toResourceEntity(SingleResourceRequest request) throws OperationNotFoundException {
        if ( request == null ) {
            return null;
        }

        ResourceEntity resourceEntity = new ResourceEntity();

        try {
            resourceEntity.setParent( resourceIdMapper.idToEntity( request.getParentId() ) );
        }
        catch ( ResourceNotFoundException e ) {
            throw new RuntimeException( e );
        }
        resourceEntity.setName( request.getName() );
        resourceEntity.setTag( request.getTag() );
        resourceEntity.setOperations( longListToOperationEntityList( request.getOperations() ) );

        return resourceEntity;
    }

    @Override
    public ResourceEntity toResourceEntity(SingleResourceRequest request, Long id) throws OperationNotFoundException {
        if ( request == null && id == null ) {
            return null;
        }

        ResourceEntity resourceEntity = new ResourceEntity();

        if ( request != null ) {
            try {
                resourceEntity.setParent( resourceIdMapper.idToEntity( request.getParentId() ) );
            }
            catch ( ResourceNotFoundException e ) {
                throw new RuntimeException( e );
            }
            resourceEntity.setName( request.getName() );
            resourceEntity.setTag( request.getTag() );
            resourceEntity.setOperations( longListToOperationEntityList( request.getOperations() ) );
        }
        if ( id != null ) {
            resourceEntity.setId( id );
        }

        return resourceEntity;
    }

    @Override
    public ResourceDto fromResourceEntity(ResourceEntity resource) {
        if ( resource == null ) {
            return null;
        }

        ResourceDto resourceDto = new ResourceDto();

        resourceDto.setParentId( resourceIdMapper.entityToId( resource.getParent() ) );
        resourceDto.setId( resource.getId() );
        resourceDto.setName( resource.getName() );
        resourceDto.setTag( resource.getTag() );
        resourceDto.setOperations( fromOperationEntities( resource.getOperations() ) );
        resourceDto.setChildren( fromResourceEntities( resource.getChildren() ) );

        return resourceDto;
    }

    @Override
    public List<ResourceDto> fromResourceEntities(List<ResourceEntity> resources) {
        if ( resources == null ) {
            return null;
        }

        List<ResourceDto> list = new ArrayList<ResourceDto>( resources.size() );
        for ( ResourceEntity resourceEntity : resources ) {
            list.add( fromResourceEntity( resourceEntity ) );
        }

        return list;
    }

    @Override
    public OperationEntity toOperationEntity(OperationRequest dto) {
        if ( dto == null ) {
            return null;
        }

        OperationEntity operationEntity = new OperationEntity();

        operationEntity.setName( dto.getName() );
        operationEntity.setTag( dto.getTag() );

        return operationEntity;
    }

    @Override
    public OperationEntity toOperationEntity(OperationRequest dto, Long id) {
        if ( dto == null && id == null ) {
            return null;
        }

        OperationEntity operationEntity = new OperationEntity();

        if ( dto != null ) {
            operationEntity.setName( dto.getName() );
            operationEntity.setTag( dto.getTag() );
        }
        if ( id != null ) {
            operationEntity.setId( id );
        }

        return operationEntity;
    }

    @Override
    public OperationDto fromOperationEntity(OperationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        OperationDto operationDto = new OperationDto();

        operationDto.setId( entity.getId() );
        operationDto.setName( entity.getName() );
        operationDto.setTag( entity.getTag() );

        return operationDto;
    }

    @Override
    public List<OperationDto> fromOperationEntities(List<OperationEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<OperationDto> list = new ArrayList<OperationDto>( entity.size() );
        for ( OperationEntity operationEntity : entity ) {
            list.add( fromOperationEntity( operationEntity ) );
        }

        return list;
    }

    protected List<OperationEntity> longListToOperationEntityList(List<Long> list) throws OperationNotFoundException {
        if ( list == null ) {
            return null;
        }

        List<OperationEntity> list1 = new ArrayList<OperationEntity>( list.size() );
        for ( Long long1 : list ) {
            list1.add( operationIdMapper.idToEntity( long1 ) );
        }

        return list1;
    }
}
