package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import app.Control;

public class LandingForm extends Frame {
    public LandingForm(Control control) {
        super();
        
        withPanel(this::build);
    }

    private void build(JPanel panel) {
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new EmptyBorder(30, 44, 40, 44));

        addLabel(panel, "Código avión");
        addInput(panel);
    }

    private void addLabel(JPanel panel, String title) {
        var label = new JLabel(title);
        var constraints = new GridBagConstraints();
        constraints.gridwidth = 1;
        constraints.insets = new Insets(50, 50, 50, 50);
        panel.add(label);
    }

    private void addInput(JPanel panel) {
        var input = new JTextField();
        input.setPreferredSize(new Dimension(140, 20));
        var constraints = new GridBagConstraints();
        constraints.gridx = 1;
        panel.add(input);
    }
}
