package components;

import javax.swing.*;
import java.awt.*;

public class FormInput extends JTextField {
    public FormInput() {
        super();
        setPreferredSize(new Dimension(180, 22));
    }

    public GridBagConstraints constraints(int row) {
        var constraints = new GridBagConstraints();
        constraints.gridy = row;
        constraints.insets = new Insets(0, 0, 20, 0);
        return constraints;
    }
}
