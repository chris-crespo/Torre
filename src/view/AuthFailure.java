package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import app.Control;
import view.components.*;

public class AuthFailure extends Frame {
    public AuthFailure() {
        withPanel(this::build);
    }

    protected void build(JPanel panel) {
        panel.setLayout(new GridLayout(2, 1, 10, 20));
        panel.setBorder(new EmptyBorder(30, 44, 40, 44));

        panel.add(new Title("Operación rechazada"));
        panel.add(new MenuButton("Ok", e -> dispose()));
    }
}
