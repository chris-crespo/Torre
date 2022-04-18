package models;

import java.time.LocalDateTime;
import java.util.Optional;

public sealed interface Operation permits TakeOff, Landing {
    public String planeCode();
    public String kind();
    public LocalDateTime date();
    public String city();
    public SpecialCause cause();

    public default String time() {
        return String.format("%02d:%02d", date().getHour(), date().getMinute());
    }

}
