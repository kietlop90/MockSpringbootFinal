package duongam.training.dto.response.forlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LResponseSyllabus {


    private String topicCode;

    private String topicName;

    private String technicalGroup;

    private String version;

    private String topicOutline;

    private String trainingMaterials;

    private String trainingPrinciples;

    private Boolean status;

    private String createdBy; // id of user created syllabus

    private Timestamp createdDate;

    private Long modifiedBy; // id of user modified syllabus

    private Timestamp modifiedDate;

}