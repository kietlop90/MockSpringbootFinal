package duongam.training.dto.response.forlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LResponseClass {
    private Long id;
    private String name;
    private String code;
    private Integer duration;
    private Integer days;
    private Integer hours;
    private String status;
    private String location;
    private String fsu;
    private String createdBy;
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

    public String formatDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(createdDate);
    }
}
