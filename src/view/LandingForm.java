package view;

import java.util.Date;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import models.*;
import app.Control;

public class LandingForm extends Form {
    private Control control;
    public LandingForm(Control control) {
        super();
        super.build(this::build);

        this.control = control;
    }

    private void build(JPanel panel) {
        addField("Código avión");
        addField("Procedencia");
        addField("Motivo", SpecialCause.names());
    }

    void onSubmit(ActionEvent e) {
        var planeCodeField = inputs.get("Código avión");
        System.out.println(planeCodeField);
        var planeCode = planeCodeField.getText();
        if (planeCode == "") {
            planeCodeField.invalid();
            return;
        }

        var originField = inputs.get("Procedencia");
        var origin = originField.getText();
        if (origin == "") {
            originField.invalid();
            return;
        }

        var cause = (String) dropdowns.get("Motivo").getSelectedItem();
        control.requestLanding(planeCode, new Date(), origin, SpecialCause.from(cause));
    }
}
