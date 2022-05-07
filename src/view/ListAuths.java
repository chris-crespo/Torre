package view;

import java.util.List;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import view.components.*;
import view.components.Label;

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
        panel.setLayout(new BorderLayout(0, 0));
        panel.setBorder(new EmptyBorder(30, 44, 30, 44));

        var title = new Title("Autorizaciones del día");
        panel.add(title, BorderLayout.NORTH); 

        if (auths.size() == 0) {
            var label = new Label("No se ha realizado ninguna autorización");
            panel.add(label, BorderLayout.SOUTH);
        }
        else {
            var rows = auths.stream()
                .map(Authorization::operation)
                .map(op -> new String[] { 
                    op.planeCode(), op.kind(), op.city(), op.time().toString()
                })
                .toArray(String[][]::new);

            var cols = new String[] { "Avión", "Tipo", "Ciudad", "Hora" };
            var table = new JTable(rows, cols);
            var scroll = new JScrollPane();

            scroll.setPreferredSize(new Dimension(400, 200));
            
            scroll.setViewportView(table);
            scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

            panel.add(scroll, BorderLayout.CENTER);
        }
    }
}
