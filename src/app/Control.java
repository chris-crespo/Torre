package app;

import java.util.Optional;

import view.*;
import adt.*;

public class Control {
    private Operations operations;

    public Control() {
        this.operations = new Operations();
    }

    public void takeOff() {
        
    }

    public void land() {

    }

    public boolean auth() {
        return operations.next().isPresent();
    }
}
