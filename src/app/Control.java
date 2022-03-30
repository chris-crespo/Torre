package app;

import java.util.Date;
import java.util.Optional;

import view.*;
import adt.*;

public class Control {
    private Operations operations;

    public Control() {
        this.operations = new Operations();
    }

    public void requestTakeOff() {
        
    }

    public void requestLanding(String planeCode, String origin, Date date) {
        System.out.printf("%s %s\n", planeCode, origin);
    }

    public boolean auth() {
        return operations.next().isPresent();
    }
}
