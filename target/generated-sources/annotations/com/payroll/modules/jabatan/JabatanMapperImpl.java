package com.payroll.modules.jabatan;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-13T18:24:48+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.46.100.v20260624-0231, environment: Java 21.0.11 (Eclipse Adoptium)"
)
@Component
public class JabatanMapperImpl implements JabatanMapper {

    @Override
    public JabatanDTO toDto(Jabatan entity) {
        if ( entity == null ) {
            return null;
        }

        JabatanDTO jabatanDTO = new JabatanDTO();

        jabatanDTO.setDeskripsi( entity.getDeskripsi() );
        jabatanDTO.setGajiPokok( entity.getGajiPokok() );
        jabatanDTO.setId( entity.getId() );
        jabatanDTO.setKodeJabatan( entity.getKodeJabatan() );
        jabatanDTO.setNamaJabatan( entity.getNamaJabatan() );

        return jabatanDTO;
    }

    @Override
    public Jabatan toEntity(JabatanDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Jabatan.JabatanBuilder jabatan = Jabatan.builder();

        jabatan.deskripsi( dto.getDeskripsi() );
        jabatan.gajiPokok( dto.getGajiPokok() );
        jabatan.id( dto.getId() );
        jabatan.kodeJabatan( dto.getKodeJabatan() );
        jabatan.namaJabatan( dto.getNamaJabatan() );

        return jabatan.build();
    }

    @Override
    public List<JabatanDTO> toDtoList(List<Jabatan> entities) {
        if ( entities == null ) {
            return null;
        }

        List<JabatanDTO> list = new ArrayList<JabatanDTO>( entities.size() );
        for ( Jabatan jabatan : entities ) {
            list.add( toDto( jabatan ) );
        }

        return list;
    }

    @Override
    public void updateEntityFromDto(JabatanDTO dto, Jabatan entity) {
        if ( dto == null ) {
            return;
        }

        entity.setDeskripsi( dto.getDeskripsi() );
        entity.setGajiPokok( dto.getGajiPokok() );
        entity.setId( dto.getId() );
        entity.setKodeJabatan( dto.getKodeJabatan() );
        entity.setNamaJabatan( dto.getNamaJabatan() );
    }
}
