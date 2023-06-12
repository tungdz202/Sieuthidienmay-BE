package com.sapo.mock.techshop.mapper;

import com.sapo.mock.techshop.dto.response.CollectionDTO;
import com.sapo.mock.techshop.entity.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CollectionMapper extends GenericMapper<CollectionDTO, Collection> {
    @Mapping(target = "id", ignore = true)
    Collection createEntity(CollectionDTO dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "created_at", ignore = true)
    @Mapping(target = "updated_at", ignore = true)
    void updateEntity(@MappingTarget Collection entity, CollectionDTO dto);
}