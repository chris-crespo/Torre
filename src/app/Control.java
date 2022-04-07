package app;

import java.util.Date;
import java.util.Optional;

import view.*;
import adt.*;
import models.*;
import data.Db;

public class Control {
    private Db db;

    private Operations operations;
    private Operations emergencyLandings;

    public Control(Db db) {
        this.db = db;

        this.operations        = new Operations();
        this.emergencyLandings = new Operations();
    }

    public void requestTakeOff(TakeOff takeOff) {
        operations.add(takeOff);
    }

    public void requestLanding(Landing landing) {
        var q = landing.cause() == SpecialCause.None ? operations : emergencyLandings;
        q.add(landing);
        db.add(landing);
    }

    public Optional<Operation> auth() {
        return emergencyLandings.next().or(operations::next);
    }
}
