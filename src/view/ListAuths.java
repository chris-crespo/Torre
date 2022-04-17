package view;

import java.util.List;

import javax.swing.*;
import java.awt.*;

import view.components.*;

import models.Authorization;

public class ListAuths extends Frame {
    private List<Authorization> auths;

    public ListAuths(List<Authorization> auths) {
        this.auths = auths; 
        withPanel(this::build);
    }

    protected void build(JPanel panel) {
        panel.add(new Title("Operaciones del día")); 
    }
}
