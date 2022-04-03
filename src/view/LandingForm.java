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
        this.control = control;
    }

    protected void build() {
        addField("Código avión");
        addField("Procedencia");
        addField("Motivo", SpecialCause.names());
    }

    void onSubmit(ActionEvent e) {
        var planeCodeField = inputs.get("Código avión");
        if (!planeCodeField.validate(t -> t.length() > 0))
            return;

        var originField = inputs.get("Procedencia");
        if (!originField.validate(t -> t.length() > 0))
            return;

        var planeCode = planeCodeField.getText();
        var origin    = originField.getText();
        var cause     = (String) dropdowns.get("Motivo").getSelectedItem();

        control.requestLanding(planeCode, new Date(), origin, SpecialCause.from(cause));

        new Menu(control);
        dispose();
    }
}
