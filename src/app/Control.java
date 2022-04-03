package app;

import java.util.Date;
import java.util.Optional;

import view.*;
import adt.*;
import models.*;

public class Control {
    private Operations operations;

    public Control() {
        this.operations = new Operations();
    }

    public void requestTakeOff(String planeCode, Date date, String destination) {
        var takeOff = new TakeOff(planeCode, date, destination);
        operations.add(takeOff);
        new Menu(this);
    }

    public void requestLanding(String planeCode, Date date, String origin, SpecialCause cause) {
        System.out.printf("%s %s\n", planeCode, origin);
    }

    public boolean auth() {
        return operations.next().isPresent();
    }
}
