package components;

import java.util.function.Predicate;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FormInput extends JTextField {
    public FormInput() {
        super();
        setPreferredSize(new Dimension(180, 22));
        valid();
    }

    public GridBagConstraints constraints(int row) {
        var constraints = new GridBagConstraints();
        constraints.gridy = row;
        constraints.insets = new Insets(0, 0, 20, 0);
        return constraints;
    }

    private void valid() {
        setBorder(new LineBorder(Color.black, 1));
    }

    private void invalid() {
        setBorder(new LineBorder(Color.red, 1));
    }

    public boolean validate(Predicate<String> pred) {
        if (!pred.test(getText())) {
            invalid();
            return false;
        }

        valid();
        return true;
    }
}
