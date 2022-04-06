package view.components;

import javax.swing.*;
import java.awt.*;

public class ViewLabel extends JLabel {
    public ViewLabel(String name) {
        super(name);
    }

    public GridBagConstraints constraints(int row) {
        var constraints = new GridBagConstraints();

        constraints.gridy = row;
        constraints.insets = new Insets(0, 0, 10, 0);
        constraints.anchor = GridBagConstraints.WEST;

        return constraints;
    }
}
