import view.Menu;
import app.Control;
import data.Db;

public class Main {
    private static void run(Db instance) {
        var control = new Control(instance);
        new Menu(control);
    }

    public static void main(String[] args) {
        Db.instance().ifPresentOrElse(Main::run, ConnectionFailure::new);
    }
}
