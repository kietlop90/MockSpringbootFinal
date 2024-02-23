package duongam.training.dto.response.fordetail;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Permission;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DReponseUserPermission {
    private Long id;

    private String role;

    private String syllabus;

    private String trainingProgram;

    private String classForProject;

    private String learningMetarial;

    private String userManagerment;


}
