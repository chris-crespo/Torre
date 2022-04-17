package app;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import view.*;
import adt.*;
import models.*;
import data.Db;
import utils.Result;

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
        db.insertOp(takeOff);
    }

    public void requestLanding(Landing landing) {
        var q = landing.cause() == SpecialCause.None ? operations : emergencyLandings;
        q.add(landing);
        db.insertOp(landing);
    }

    public Result<List<Authorization>> getTodaysAuths() {
        return db.fetchAuths();
    }

    public Optional<Authorization> auth() {
        var auth = emergencyLandings.next().or(operations::next)
            .map(op -> new Authorization(op, LocalDateTime.now()));

        auth.ifPresent(db::insertAuth);
        return auth;
    }
}
