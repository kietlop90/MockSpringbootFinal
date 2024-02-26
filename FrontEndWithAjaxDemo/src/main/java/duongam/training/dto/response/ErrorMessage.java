package duongam.training.dto.response;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private final int statusCode;
    private final String message;

    private final LocalDateTime timestamp;

    public ErrorMessage(int statusCode, String message, LocalDateTime timestamp) {
        this.statusCode = statusCode;
        this.message = message;
        this.timestamp = timestamp;
    }


}
