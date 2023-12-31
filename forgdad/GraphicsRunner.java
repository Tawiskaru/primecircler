/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgdad;

import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author s653957
 */
public class GraphicsRunner {

    final static Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    private final static int WIDTH = (int) screenSize.getWidth();
    private final static int HEIGHT = (int) screenSize.getHeight();
    JFrame frame = new JFrame("PRIME CIRCLER - FOR GDAD");
    NumPanel runPanel = new NumPanel();

    public GraphicsRunner() throws FileNotFoundException {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(runPanel);
        runPanel.setBackground(Color.white);
        frame.setSize(screenSize);
        frame.setVisible(true);
    }
}
