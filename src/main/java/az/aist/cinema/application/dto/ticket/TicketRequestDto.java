package az.aist.cinema.application.dto.ticket;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketRequestDto {

    String sector;
    String line;
    String place;
//    TicketStatus ticketStatus;  // tarix meselesi sorusu, bron tarixi ucun tickerleri release ede bilsin
    Long sessionId;
    Long customerId;

}
