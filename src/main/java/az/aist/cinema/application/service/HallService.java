package az.aist.cinema.application.service;

import az.aist.cinema.application.dto.hall.HallRequestDto;
import az.aist.cinema.application.dto.hall.HallResponseDto;
import az.aist.cinema.application.entity.HallEnt;

public interface HallService extends GenericCrudService<HallRequestDto, HallEnt, HallResponseDto> {
}
