package view;

import java.util.function.*;
import javax.swing.*;

public class Form extends Frame {
    HashMap<String, JTextField> inputs;
    HashMap<String, JComboBox>  dropdowns;
    HashMap<String, JButton>    buttons;

    private int rows;

    public Form(Function<Form, Consumer<Panel>> builder) {
        super();

        this.inputs    = new HashMap<>();
        this.dropdowns = new HashMap<>();
        this.buttons   = new HashMap<>();

        this.rows = 0;

        withPanel(builder.apply(this));
    }

    void addField(JPanel panel, String name) {
        var label = createLabel(name);
        var input = createInput();

        inputs.put(name, input);
        panel.add(label);
        panel.add(input);
    }

    void addField(JPanel panel, String name, String[] options) {
        var label = createLabel(name);
        var dropdown = createDropdown(options);

        dropdowns.add(name, dropdown);
        panel.add(label);
        panel.add(input);
    }


}
