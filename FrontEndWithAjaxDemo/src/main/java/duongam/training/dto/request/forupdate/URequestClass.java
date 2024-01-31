package duongam.training.dto.request.forupdate;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class URequestClass {
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
