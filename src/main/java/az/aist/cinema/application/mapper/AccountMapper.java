package az.aist.cinema.application.mapper;

import az.aist.cinema.application.dto.account.AccountRegisterRequestDto;
import az.aist.cinema.application.entity.AccountEnt;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountEnt toEntity(AccountRegisterRequestDto dto);
}
