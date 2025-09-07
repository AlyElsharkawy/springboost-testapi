package com.example.springboot.mapper.bol;

import com.example.springboot.entity.bol.BillOfLadingDetail;
import com.example.springboot.dto.bol.BillOfLadingDetailDTOResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BillOfLadingDetailResponseMapper {
    @Mapping(source = "hscode.name", target = "hscodeName")
    @Mapping(source = "bol.nbr", target = "bolNbr")
    BillOfLadingDetailDTOResponse toDto(BillOfLadingDetail bolDetail);

    List<BillOfLadingDetailDTOResponse> toDTOList(List<BillOfLadingDetail> entities);

    List<BillOfLadingDetail> toEntityList(List<BillOfLadingResponseDTOMapper> dtos);

    // Update existing entity from DTO
    void updateEntityFromDTO(BillOfLadingDetailDTOResponse dto,
            @MappingTarget BillOfLadingDetail entity);
}
