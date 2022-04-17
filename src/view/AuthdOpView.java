package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;

import view.components.Title;
import view.components.Label;
import models.*;

public class AuthdOpView extends Frame {
    private Authorization auth;
    private JPanel panel;

    private int rows;

    public AuthdOpView(Authorization auth) {
        super();

        this.auth = auth;
        this.rows = 1;

        withPanel(this::build);              
    }

    protected void build(JPanel panel) {
        this.panel = panel;
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(30, 44, 40, 44));

        addTitle("Aeropuerto Plaiaundi");
        display(auth);
    }

    private void addTitle(String text) {
        var title = new Title(text);
        panel.add(title, title.constraints());
    }

    private void addRow(String left, String right) {
        var leftLabel  = new Label(left);
        var rightLabel = new Label(right);

        panel.add(leftLabel,  leftLabel.constraints(rows));
        panel.add(rightLabel, rightLabel.constraints(rows++));
    }

    private void display(Authorization auth) {
        var op = auth.operation();
        addRow("Operación relizada: ", op.kind());
        addRow("Código avión: ", op.planeCode());
        addRow("Hora operación: ", op.time());

        if (op.kind().equals("Aterrizaje")) {
            addRow("Origen: ", op.city());
            addRow("Motivo: ", ((Landing)op).cause().label());
        }
        else 
            addRow("Destino: ", op.city());
    }
}
