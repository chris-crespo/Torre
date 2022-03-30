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
        var planeCode = inputs.get("Código avión").getText();
        var origin = inputs.get("Procedencia").getText();
        control.requestLanding(planeCode, origin, new Date());
    }
}
