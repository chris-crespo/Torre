import view.Menu;
import view.ConnectionFailure;
import app.Control;
import data.Db;

public class Main {
    private static void run(Db db) {
        var control = new Control(db);
        new Menu(control);
    }

    private static void setup(Db db) {
        db.setup()
            .ifOk(b -> run(db))
            .ifError(e -> System.out.println(e.getMessage()));
    }

    public static void main(String[] args) {
        Db.instance().ifPresentOrElse(Main::setup, ConnectionFailure::new);
    }
}
