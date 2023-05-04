package az.aist.cinema.application.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorCodesEnum {

    USER_NOT_FOUND("USER-NOT-FOUND"),
    ROLE_NOT_FOUND("ROLE-NOT-FOUND"),
    PASSWORD_IS_NOT_CORRECT("PASSWORD-IS-NOT-CORRECT"),
    VALUTE_CURS_NOT_FOUND("VALUTE-CURS-NOT-FOUND"),
    MOVIE_CURS_NOT_FOUND("MOVIE-CURS-NOT-FOUND"),
    ACCOUNT_NOT_FOUND("ACCOUNT-NOT-FOUND"),
    TICKET_NOT_FOUND("TICKET-NOT-FOUND"),
    DUBLICATE_TRANSACTION("DUBLICATE-TRANSACTION");
    public final String value;
}
