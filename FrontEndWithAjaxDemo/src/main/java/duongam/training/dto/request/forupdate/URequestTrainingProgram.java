package duongam.training.dto.request.forupdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class URequestTrainingProgram {
    private String id;

    @Size(min = 6, max = 20, message = "Length of Program Name should be from 6 to 20")
    private String name;
    private String duration;
    private String status;
}