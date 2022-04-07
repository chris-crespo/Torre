import view.Menu;
import view.ConnectionFailure;
import app.Control;
import data.Db;

public class Main {
    private static void run(Db db) {
        db.setup();

        var control = new Control(db);
        new Menu(control);
    }

    public static void main(String[] args) {
        Db.instance().ifPresentOrElse(Main::run, ConnectionFailure::new);
    }
}
