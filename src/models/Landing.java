package models;

import java.time.LocalDateTime;

public record Landing(String planeCode, LocalDateTime date, String origin, SpecialCause cause) 
        implements Operation {
    public String time() {
        return String.format("%2d:%2d", date.getHour(), date.getMinute());
    }
}
