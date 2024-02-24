package duongam.training.dto.response.fordetail;

import lombok.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
    private String createdBy;
    private Long createdById;
    private Timestamp createdDate;
    private String modifiedBy;
    private Timestamp modifiedDate;
    private String trainingProgramCode;
    private String trainingProgramName;
    private String attendee;
    private ArrayList<String> listOfClass;

    public void formatData() {
        days = duration != null ? duration : 0;
        hours = days * 4;
        attendee = trainingProgramName;
    }

    public String formatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(createdDate);
    }
}
