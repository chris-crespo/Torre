package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import models.SpecialCause;
import app.Control;

public class LandingForm extends Frame {
    public LandingForm(Control control) {
        super();
        
        withPanel(this::build);
    }

    private void build(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(30, 44, 40, 44));

        addLabel(panel, "Código avión", 0);
        addInput(panel, 0);
        addLabel(panel, "Procedencia", 1);
        addInput(panel, 1);
        addLabel(panel, "Motivo", 2);
        addDropdown(panel, SpecialCause.names(), 2);
        addButton(panel, "Ok", 3);
    }

    private void addLabel(JPanel panel, String text, int row) {
        var label = new JLabel(text);
        var constraints = new GridBagConstraints();
        constraints.gridy = row;
        constraints.insets = new Insets(0, 0, 20, 20);
        panel.add(label, constraints);
    }

    private void addInput(JPanel panel, int row) {
        var input = new JTextField();
        var constraints = new GridBagConstraints();
        input.setPreferredSize(new Dimension(140, 20));
        constraints.gridy = row;
        constraints.insets = new Insets(0, 0, 20, 0);
        panel.add(input, constraints);
    }

    private void addDropdown(JPanel panel, String[] options, int row) {
        var dropdown = new JComboBox<String>(options);
        dropdown.setSelectedIndex(0);
        var constraints = new GridBagConstraints();
        constraints.gridy = row;
        panel.add(dropdown, constraints);
    }

    private void addButton(JPanel panel, String text, int row) {
        var button = new JButton(text);
        var constraints = new GridBagConstraints();
        constraints.gridy = row;
        constraints.gridwidth = 2;
        panel.add(button, constraints);
    }
}
