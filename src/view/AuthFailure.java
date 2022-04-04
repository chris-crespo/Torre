package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import app.Control;

public class AuthFailure extends Frame {
    public AuthFailure() {
        var panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 20));
        panel.setBorder(new EmptyBorder(30, 44, 40, 44));

        panel.add(createLabel("Operación rechazada"));
        panel.add(createButton("Ok", e -> dispose()));

        add(panel);
        pack();
        setVisible(true);
    }
}
