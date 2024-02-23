package duongam.training.dto.response.fordetail;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DResponseClass {
    private Long id;
    private String name;
    private String code;
    private Integer duration;
    private Integer days;
    private Integer hours;
    private String status;
    private String location;
    private String fsu;
    private String startDate;
    private String endDate;
    private Long createdBy;
    private Timestamp createdDate;
    private Long modifiedBy;
    private Timestamp modifiedDate;
    private String trainingProgramName;
    private String attendee;
    private ArrayList<String> listOfClass;

    public void formatData() {
        days = duration != null ? duration : 0;
        hours = days * 4;
        attendee = trainingProgramName;
    }
}
