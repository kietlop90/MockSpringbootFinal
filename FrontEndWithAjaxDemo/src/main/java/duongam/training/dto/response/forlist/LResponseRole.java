package duongam.training.dto.response.forlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LResponseRole {
    private Long id;
    private String name;
    private String description;
    private String syllabus;
    private String trainingProgram;
    private String classForProject;
    private String learningMetarial;
    private String userManagerment;
}
