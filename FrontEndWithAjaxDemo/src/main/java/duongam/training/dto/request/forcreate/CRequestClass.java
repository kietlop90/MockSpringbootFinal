package duongam.training.dto.request.forcreate;

import lombok.*;

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
public class CRequestClass {
    private String name;
    private String status;
}
