package app;

import java.util.Date;
import java.util.Optional;

import view.*;
import adt.*;
import models.*;

public class Control {
    private Operations operations;
    private Operations emergencyLandings;

    public Control() {
        this.operations        = new Operations();
        this.emergencyLandings = new Operations();
    }

    public void requestTakeOff(TakeOff takeOff) {
        operations.add(takeOff);
    }

    public void requestLanding(Landing landing) {
        var q = landing.cause() == SpecialCause.None ? operations : emergencyLandings;
        q.add(landing);
    }

    public Optional<Operation> auth() {
        return emergencyLandings.next().or(operations::next);
    }
}
