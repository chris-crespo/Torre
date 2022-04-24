import view.*;
import app.Control;
import data.Db;

public class Main {
    private static void tryRun(Db db) {
        var control = new Control(db);
        control.sync()
            .ifOk(res -> new Menu(control))
            .ifError(SyncFailure::new);
    }

    private static void setup(Db db) {
        db.setup()
            .ifOk(b -> tryRun(db))
            .ifError(SetupFailure::new);
    }

    public static void main(String[] args) {
        Db.instance().ifPresentOrElse(Main::setup, ConnectionFailure::new);
    }
}
