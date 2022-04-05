package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import models.*;
import view.components.*;

public class AuthdOpView extends Frame {
    private Operation op;

    public AuthdOpView(Operation op) {
        super();

        this.op = op;

        withPanel(this::build);              
    }

    protected void build(JPanel panel) {
        panel.setLayout(new GridLayout(4, 1, 10, 20));
        panel.setBorder(new EmptyBorder(30, 44, 40, 44));

        var vm = op.viewModel();
        panel.add(new Title("Aeropuerto Plaiaundi"));
        panel.add(new FormLabel("Operación realizada: " + vm.kind()));
        panel.add(new FormLabel("Hora operación: " + vm.time()));
        panel.add(new FormLabel("Código avión: " + vm.planeCode()));
    }
}
