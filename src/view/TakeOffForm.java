package view;

import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

import models.SpecialCause;
import app.Control;

public class TakeOffForm extends Form {
    private Control control;
    public TakeOffForm(Control control) {
        super();
        this.control = control;
    }

    protected void build() {
        addField("Código avión");
        addField("Destino");
    }

    void onSubmit(ActionEvent e) {
        var planeCodeField = inputs.get("Código avión");
        if (!planeCodeField.validate(t -> t.length() > 0))
            return;

        var destinationField = inputs.get("Destino");
        if (!destinationField.validate(t -> t.length() > 0))
            return;

        var planeCode   = planeCodeField.getText();
        var destination = destinationField.getText();

        control.requestTakeOff(planeCode, new Date(), destination);

        new Menu(control);
        dispose();
    }
}
