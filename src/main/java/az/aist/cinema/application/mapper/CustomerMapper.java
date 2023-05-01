package az.aist.cinema.application.mapper;

import az.aist.cinema.application.dto.customer.CustomerRequestDto;
import az.aist.cinema.application.dto.customer.CustomerResponseDto;
import az.aist.cinema.application.entity.CustomerEnt;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    CustomerResponseDto toDto(CustomerEnt ent);

    CustomerEnt toEntity(CustomerRequestDto dto);

    List<CustomerResponseDto> toDtoList(List<CustomerEnt> ents);

    List<CustomerEnt> toEntityList(List<CustomerRequestDto> dtos);


}
