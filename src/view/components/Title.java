package view.components;

import javax.swing.*;
import java.awt.*;

public class Title extends JLabel {
    public Title(String text) {
        super(text);
        setFont(new Font("sans-serif", Font.BOLD, 22));
    }
}
