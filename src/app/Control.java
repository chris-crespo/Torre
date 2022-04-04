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

    public void requestTakeOff(TakeOff takeOff) {
        operations.add(takeOff);
    }

    public void requestLanding(Landing landing) {
        operations.add(landing);
    }

    public Optional<Operation> auth() {
        return operations.next(); 
    }
}
