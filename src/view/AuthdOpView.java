package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import view.components.*;
import models.*;

public class AuthdOpView extends Frame {
    private Operation op;
    private JPanel panel;

    private int rows;

    public AuthdOpView(Operation op) {
        super();

        this.op = op;
        this.rows = 1;

        withPanel(this::build);              
    }

    protected void build(JPanel panel) {
        this.panel = panel;
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(30, 44, 40, 44));

        addTitle("Aeropuerto Plaiaundi");

        switch (op) {
            case Landing landing -> display(landing);
            case TakeOff takeOff -> display(takeOff);
        }
    }

    private void addTitle(String text) {
        var title = new Title(text);
        panel.add(title, title.constraints());
    }

    private void addRow(String left, String right) {
        var leftLabel  = new ViewLabel(left);
        var rightLabel = new ViewLabel(right);

        panel.add(leftLabel,  leftLabel.constraints(rows));
        panel.add(rightLabel, rightLabel.constraints(rows++));
    }

    private void display(Landing landing) {
        addRow("Operación realizada: ", "Aterrizaje");
        addRow("Código avión: ", landing.planeCode());
        addRow("Hora operación: ", landing.time());
        addRow("Procedencia: ", landing.origin());
        addRow("Motivo: ", landing.cause().label());
    }

    private void display(TakeOff takeOff) {
        addRow("Operación relizada: ", "Despegue");
        addRow("Código avión: ", takeOff.planeCode());
        addRow("Hora operación: ", takeOff.time());
        addRow("Destino: ", takeOff.destination());
    }
}
