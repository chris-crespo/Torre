package view;

import java.util.function.*;
import java.util.LinkedHashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import app.Control;
import view.components.*;

public class Menu extends Frame {
    /* Usamos LinkedHashMap para preservar el orden en el que añadimos
     * los elementos */
    private Control control;
    private LinkedHashMap<String, ActionListener> options;

    public Menu(Control control) {
        super(JFrame.EXIT_ON_CLOSE);

        this.control = control;
        this.options = new LinkedHashMap<>() {{
            put("Solicitud de permiso para aterrizar", e -> showLandingForm());
            put("Solicitud de permiso para despegar",  e -> showTakeOffForm());
            put("Autorizar operación",                 e -> auth());
            put("Mostrar autorizaciones",              e -> showAuths());
        }};

        withPanel(this::build);
    }

    protected void build(JPanel panel) {
        panel.setLayout(new GridLayout(options.size() + 1, 1, 10, 20));
        panel.setBorder(new EmptyBorder(30, 44, 40, 44));

        panel.add(new Title("Menú de Operaciones"));
        options.forEach((title, cb) -> panel.add(new MenuButton(title, cb)));
    }

    private void showLandingForm() {
        new LandingForm(control);  
    }

    private void showTakeOffForm() {
        new TakeOffForm(control);
    }

    private void auth() {
        control.auth().ifPresentOrElse(AuthdOpView::new, AuthFailure::new);
    }

    private void showAuths() {
        control.getTodaysAuths()
            .ifOk(ListAuths::new)
            .ifError(e -> System.out.println(e.getMessage()));
    }
}
