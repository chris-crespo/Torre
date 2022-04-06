package view;

import java.time.LocalDateTime;

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

    protected void buildForm() {
        addRequiredField("Código avión");
        addRequiredField("Procedencia");
        addField("Motivo", SpecialCause.names());
    }

    void onSubmit(ActionEvent e) {
        var planeCode = inputs.get("Código avión").getText();
        var origin    = inputs.get("Procedencia").getText();
        var cause     = (String) dropdowns.get("Motivo").getSelectedItem();

        control.requestLanding(
            new Landing(planeCode, LocalDateTime.now(), origin, SpecialCause.from(cause)));
    }
}
