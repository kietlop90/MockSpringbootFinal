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
    private String status;
    private String location;
    private String FSU;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long createdBy;
    private Timestamp createdDate;
    private Long modifiedBy;
    private Timestamp modifiedDate;
    private String trainingProgramName;
    private ArrayList<String> listOfClass;
}
