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
        addRequiredField("Código avión");
        addRequiredField("Procedencia");
        addField("Motivo", SpecialCause.names());
    }

    void onSubmit(ActionEvent e) {
        var planeCode = inputs.get("Código avión").getText();
        var origin    = inputs.get("Procedencia").getText();
        var cause     = (String) dropdowns.get("Motivo").getSelectedItem();

        control.requestLanding(planeCode, new Date(), origin, SpecialCause.from(cause));

        new Menu(control);
    }
}
