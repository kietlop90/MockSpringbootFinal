package duongam.training.dto.response;


import lombok.Data;

import java.time.LocalDateTime;

@Data

public class ErrorMessage {
    private final String message;


    public ErrorMessage( String message) {
        this.message = message;

    }


}
