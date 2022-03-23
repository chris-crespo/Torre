import view.Menu;
import app.Control;

public class Main {
    public static void main(String[] args) {
        var control = new Control();
        new Menu(control);
    }
}
