package duongam.training.dto.response.fordetail;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DReponseTrainingProgram {

    private String id;
    private String ProgramName;
    private String CreatedOn;
    private String CreatedBy;
    private String duration;
    private String Status;
}
