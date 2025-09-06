package com.example.springboot.mapper.company;

import com.example.springboot.entity.company.Company;
import com.example.springboot.dto.company.CompanyDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyDTOResponseMapper {
    // Entity to DTO
    CompanyDTOResponse toDTO(Company entity);

    @Mapping(target = "id", ignore = true)
    Company toEntity(CompanyDTOResponse dto);

    List<CompanyDTOResponse> toDTOList(List<Company> entities);

    List<Company> toEntityList(List<CompanyDTOResponse> dtos);

    // Update existing entity from DTO
    void updateEntityFromDTO(CompanyDTOResponse dto, @MappingTarget Company entity);
}
