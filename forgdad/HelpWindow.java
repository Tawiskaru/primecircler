/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgdad;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author S653957
 */
public class HelpWindow extends JFrame {

    JPanel helpPanel;

    public HelpWindow() throws FileNotFoundException {
        this.setTitle("HELP");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        helpPanel = new JPanel();
        helpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        helpPanel.setPreferredSize(new Dimension(this.getWidth(), 2250));
        fillPanel();
        JScrollPane scroller = new JScrollPane(helpPanel);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scroller);
        this.setVisible(true);
    }
    public HelpWindow(int n) throws FileNotFoundException{
        super();
        this.setTitle("START HELP");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        helpPanel = new JPanel();
        helpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        helpPanel.setPreferredSize(new Dimension(this.getWidth(), 350));
        fillPanel2();
        JScrollPane scroller = new JScrollPane(helpPanel);
        scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.add(scroller);
        this.setVisible(true);
    }

    private void fillPanel() throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File("help.txt"));
        while (fileReader.hasNext()) {
            String line = fileReader.nextLine();
            JLabel nextLabel = new JLabel(line);
            helpPanel.add(nextLabel);
        }
        helpPanel.setBackground(Color.WHITE);
    }
    
    private void fillPanel2() throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File("startHelp.txt"));
        while (fileReader.hasNext()) {
            String line = fileReader.nextLine();
            JLabel nextLabel = new JLabel(line);
            helpPanel.add(nextLabel);
        }
        helpPanel.setBackground(Color.WHITE);
    }

}
