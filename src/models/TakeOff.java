package models;

import java.time.LocalDateTime;

public record TakeOff(String planeCode, LocalDateTime date, String destination) 
        implements Operation {
    public String time() {
        return String.format("%2d:%2d", date.getHour(), date.getMinute());
    }

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
