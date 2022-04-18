package models;

import java.time.LocalDateTime;

public record TakeOff(String planeCode, LocalDateTime date, String destination) 
        implements Operation {
    public String kind() {
        return "Despegue";
    }

    public String city() {
        return destination;
    }

    public SpecialCause cause() {
        return SpecialCause.None;
    }
}
