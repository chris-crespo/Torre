package view;

import java.util.HashMap;
import java.util.function.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import components.*;

public abstract class Form extends Frame {
    HashMap<String, JTextField> inputs;
    HashMap<String, JComboBox>  dropdowns;

    private JPanel panel;
    private int rows;

    public Form() {
        super();

        this.inputs    = new HashMap<>();
        this.dropdowns = new HashMap<>();

        this.rows = 0;
    }

    void build(Consumer<JPanel> builder) {
        withPanel(panel -> {
            this.panel = panel;
            panel.setLayout(new GridBagLayout());
            panel.setBorder(new EmptyBorder(30, 44, 40, 44));

            builder.accept(panel);

            var submitButton = new FormSubmitButton(this::onSubmit);
            panel.add(submitButton, submitButton.constraints(rows));
        });
    }

    void addField(String name) {
        var label = new FormLabel(name);
        var input = new FormInput();

        inputs.put(name, input);
        panel.add(label, label.constraints(rows));
        panel.add(input, input.constraints(rows++));
    }

    void addField(String name, String[] options) {
        var label    = new FormLabel(name);
        var dropdown = new FormDropdown(options);

        dropdowns.put(name, dropdown);
        panel.add(label, label.constraints(rows));
        panel.add(dropdown, dropdown.constraints(rows++));
    }

    abstract void onSubmit(ActionEvent e);
}
