package models;

import java.util.Date;

public record Landing(String planeCode, Date date, String origin, SpecialCause cause) 
        implements Operation {

}
