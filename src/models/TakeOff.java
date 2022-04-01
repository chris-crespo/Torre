package models;

import java.util.Date;

public record TakeOff(String planeCode, Date date, String destination) 
        implements Operation {
}
