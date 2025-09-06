package com.example.springboot.mapper.bol;

import com.example.springboot.entity.bol.BillOfLading;
import com.example.springboot.dto.bol.BillOfLadingDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BillOfLadingResponseDTOMapper {
    @Mapping(source = "company.name", target = "companyName")
    BillOfLadingDTOResponse toDto(BillOfLading bol);

    List<BillOfLadingDTOResponse> toDTOList(List<BillOfLading> entities);

    List<BillOfLading> toEntityList(List<BillOfLadingResponseDTOMapper> dtos);

    // Update existing entity from DTO
    void updateEntityFromDTO(BillOfLadingDTOResponse dto, @MappingTarget BillOfLading entity);
}
