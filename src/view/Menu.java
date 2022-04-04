package view;

import java.util.LinkedHashMap;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import app.Control;

public class Menu extends Frame {
    /* Usamos LinkedHashMap para preservar el orden en el que añadimos
     * los elementos */
    private Control control;
    private LinkedHashMap<String, ActionListener> options;

    public Menu(Control control) {
        super();

        this.control = control;
        this.options = new LinkedHashMap<>() {{
            put("Solicitud de permiso para aterrizar", e -> showLandingForm());
            put("Solicitud de permiso para despegar",  e -> showTakeOffForm());
            put("Autorizar operación",                 e -> auth());
        }};

        withPanel(this::build);
    }

    private void build(JPanel panel) {
        panel.setLayout(new GridLayout(4, 1, 10, 20));
        panel.setBorder(new EmptyBorder(30, 44, 40, 44));

        panel.add(createLabel("Menú de Operaciones"));
        options.forEach((title, cb) -> panel.add(createButton(title, cb)));
    }

    private void showLandingForm() {
        new LandingForm(control);  
    }

    private void showTakeOffForm() {
        new TakeOffForm(control);
    }

    private void auth() {
        var authorized = control.auth(); 
        control.auth().ifPresentOrElse(AuthdOpView::new, AuthFailure::new);
    }
}
