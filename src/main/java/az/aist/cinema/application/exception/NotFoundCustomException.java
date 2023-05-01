package az.aist.cinema.application.exception;

public class NotFoundCustomException extends GenericException{
    public NotFoundCustomException(ErrorCodesEnum code,String details, Object... arguments) {
        super(404, code.value, code.value, details, arguments);
    }
}