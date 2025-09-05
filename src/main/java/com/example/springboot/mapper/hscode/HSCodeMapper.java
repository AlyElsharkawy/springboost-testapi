package com.example.springboot.mapper.hscode;

import com.example.springboot.entity.hscode.HSCode;
import com.example.springboot.dto.hscode.HSCodeMinimal;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import java.util.List;

@Mapper(componentModel = "spring")
public interface HSCodeMapper {

  // Entity to DTO
  HSCodeMinimal toDTO(HSCode entity);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "timeStamp", ignore = true)
  HSCode toEntity(HSCodeMinimal dto);

  List<HSCodeMinimal> toDTOList(List<HSCode> entities);

  List<HSCode> toEntityList(List<HSCodeMinimal> dtos);

  // Update existing entity from DTO
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "timeStamp", ignore = true)
  void updateEntityFromDTO(HSCodeMinimal dto, @MappingTarget HSCode entity);
}
