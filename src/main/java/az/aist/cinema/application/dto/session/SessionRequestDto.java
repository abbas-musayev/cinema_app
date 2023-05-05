package az.aist.cinema.application.dto.session;

import az.aist.cinema.application.enums.SeansTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SessionRequestDto {
    String name;
    LocalDate startDate;
    String startHour;
    LocalDate endDate;
    String end_hour;
    String price;
    SeansTime seansTime;
    Long movieId;
    List<Long> hallIds;

}
