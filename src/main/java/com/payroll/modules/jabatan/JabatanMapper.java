package com.payroll.modules.jabatan;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface JabatanMapper {
    JabatanMapper INSTANCE = Mappers.getMapper(JabatanMapper.class);

    JabatanDTO toDto(Jabatan entity);
    Jabatan toEntity(JabatanDTO dto);
    List<JabatanDTO> toDtoList(List<Jabatan> entities);

    void updateEntityFromDto(JabatanDTO dto, @MappingTarget Jabatan entity);
}
