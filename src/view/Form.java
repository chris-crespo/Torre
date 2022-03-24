package view;

import java.util.function.Consumer;
import javax.swing.*;

public class Form extends Frame {
    private JPanel panel;

    public Form() { super(); }

    void setPanel(JPanel panel) {
        this.panel = panel;
    }

    void addInput(String name) {
        var label = new JLabel(name); 
        panel.add(label);

        var input = new JTextField();
        panel.add(input);
    }
}
