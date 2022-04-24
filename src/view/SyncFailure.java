package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

import view.components.*;

public class SyncFailure extends Frame {
    public static String text = 
        "No se pudo sincronizar el programa con la base de datos";

    private Exception exn;

    public SyncFailure(Exception exn) {
        super(JFrame.EXIT_ON_CLOSE);
        this.exn = exn;
        withPanel(this::build);
    }

    protected void build(JPanel panel) {
        panel.setBorder(new EmptyBorder(30, 44, 30, 44)); 
        
        panel.add(new Label(text, Font.BOLD, 15));
        panel.add(new Label(exn.getMessage(), Font.BOLD, 13));
    }
}
