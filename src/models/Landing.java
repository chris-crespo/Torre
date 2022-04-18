package models;

import java.time.LocalDateTime;

public record Landing(String planeCode, LocalDateTime date, String origin, SpecialCause cause) 
        implements Operation {
    public String kind() {
        return "Aterrizaje";
    }

    public String city() {
        return origin;
    }
}
