package az.aist.cinema.application.exception;

import static az.aist.cinema.application.exception.ErrorCodesEnum.USER_NOT_FOUND;
import static org.springframework.http.HttpStatus.NOT_FOUND;

public class UserNotFoundException extends GenericException{
    public UserNotFoundException(Object... arguments) {
        super(404, NOT_FOUND.toString(),USER_NOT_FOUND.value, USER_NOT_FOUND.value, arguments);
    }
}
