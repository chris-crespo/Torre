package view;

import java.util.HashMap;
import java.util.Map;
import java.util.function.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;

import components.*;

public abstract class Form extends Frame {
    HashMap<String, FormInput> inputs;
    HashMap<String, FormDropdown>  dropdowns;

    private JPanel panel;
    private int rows;

    public Form() {
        super();

        this.inputs    = new HashMap<>();
        this.dropdowns = new HashMap<>();

        this.rows = 0;

        buildForm();
    }

    protected abstract void build();

    private void buildForm() {
        withPanel(panel -> {
            this.panel = panel;
            panel.setLayout(new GridBagLayout());
            panel.setBorder(new EmptyBorder(30, 44, 40, 44));

            build();

            var submitButton = new FormSubmitButton(this::handleSubmit);
            panel.add(submitButton, submitButton.constraints(rows));
        });
    }

    void addRequiredField(String name) {
        addRequiredField(name, t -> true);
    }

    void addRequiredField(String name, Predicate<String> validator) {
        addField(name, validator);
        inputs.get(name).required();
    }

    void addField(String name) {
        addField(name, t -> true); 
    }

    void addField(String name, Predicate<String> validator) {
        var label = new FormLabel(name);
        var input = new FormInput(validator);

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

    private boolean valid() {
        return inputs.entrySet().stream()
            .map(Map.Entry::getValue)
            .allMatch(FormInput::valid);
    }

    private void handleSubmit(ActionEvent e) {
        if (valid()) {
            onSubmit(e);
            dispose();
        }
    }

    abstract void onSubmit(ActionEvent e);
}
