package view;

import java.util.List;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Dimension;

import view.components.*;

import models.Authorization;

public class ListAuths extends Frame {
    private List<Authorization> auths;
    private int rows;

    public ListAuths(List<Authorization> auths) {
        this.auths = auths; 
        this.rows  = 0;
        withPanel(this::build);
    }

    private void showHeader(JPanel panel) {
        var cols = new String[] { "Avión", "Tipo", "Destino/Procedencia", "Hora" };
        for (var col : cols ) {
            var label = new Label(col, Font.BOLD, 13);
            panel.add(label, label.constraints(rows));
        }
        
        rows++;
    }

    private Consumer<Authorization> addAuth(JPanel panel) {
        return auth -> {
            var op = auth.operation();

            var planeLabel = new Label(op.planeCode());
            panel.add(planeLabel, planeLabel.constraints(rows));

            var kindLabel = new Label(op.kind());
            panel.add(kindLabel, kindLabel.constraints(rows));

            var cityLabel = new Label(op.city());
            panel.add(cityLabel, cityLabel.constraints(rows));

            var dateLabel = new Label(op.time().toString());
            panel.add(dateLabel, dateLabel.constraints(rows++));
        };
    }

    protected void build(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(30, 44, 30, 44));

        var title = new Title("Autorizaciones del día");
        panel.add(title, title.constraints()); 

        if (auths.size() == 0) {
            var label = new Label("No se ha realizado ninguna autorización");
            panel.add(label, label.constraints(1));
        }
        else {
            var subPanel = new JPanel();
            subPanel.setLayout(new GridBagLayout());

            var constraints = new GridBagConstraints();
            constraints.gridy = 1;

            var scroll = new JScrollPane(subPanel);
            
            panel.add(scroll, constraints);
            showHeader(subPanel);
            auths.forEach(addAuth(subPanel));
        }
    }
}
