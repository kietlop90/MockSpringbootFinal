package duongam.training.dto.request.forupdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class URequestUserPermission {
    private Long id;

    private String role;

    private String syllabus;

    private String trainingProgram;

    private String classForProject;

    private String learningMetarial;

    private String userManagerment;
}
