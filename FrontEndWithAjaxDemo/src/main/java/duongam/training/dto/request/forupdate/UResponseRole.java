package duongam.training.dto.request.forupdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UResponseRole {
    private Long id;
    private String name;
    private String description;
    private String syllabus;
    private String trainingProgram;
    private String classForProject;
    private String learningMetarial;
    private String userManagerment;
}
