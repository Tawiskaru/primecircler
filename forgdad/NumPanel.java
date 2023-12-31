/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgdad;

import static forgdad.Number.infoFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

/**
 *
 * @author s653957
 */
public class NumPanel extends JPanel {

    final static Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    private final static int WIDTH = (int) screenSize.getWidth();
    private final static int HEIGHT = (int) screenSize.getHeight() - 33;
    private int numCircles = FORGDAD.getNumLayers();
    SettingsWindow settingsWindow;
    HelpWindow helpWindow;

    public NumPanel() throws FileNotFoundException {
        this.setBackground(Color.WHITE);
        this.setLayout(null);
        this.setVisible(true);
        settingsWindow = new SettingsWindow(this);
        settingsWindow.setBounds(WIDTH - settingsWindow.getWidth() - 20, 120, settingsWindow.getWidth(), settingsWindow.getHeight());
        settingsButton();
        helpWindow = new HelpWindow();
        helpWindow.setBounds(50, 50, 510, 500);
        helpButton();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (settingsWindow.getCircleFlag()) {
            paintCircles(g);
        }
        setNums();
    }

    //draws my circles
    private void paintCircles(Graphics g) {
        //change this to a parameter later
        //calculates everything from the center, and makes the circles smaller as there are more
        Dimension center = new Dimension(WIDTH / 2, HEIGHT / 2);
        int unit = (int) center.getHeight() / numCircles;
        for (int i = 1; i < numCircles; i++) {
            int radius = unit * i;
            g.drawOval((int) center.getWidth() - radius, (int) center.getHeight() - radius, radius * 2, radius * 2);
        }
    }

    private void setNums() {
        Dimension center = new Dimension(WIDTH / 2, HEIGHT / 2);
        int unit = (int) center.getHeight() / numCircles;
        //nested for loops iterate over every element
        for (int i = 0; i < FORGDAD.listlist.size(); i++) {
            for (int j = 0; j < FORGDAD.listlist.get(i).size(); j++) {
                Number thisNum = (Number) FORGDAD.listlist.get(i).get(j);
                float xPos = (float) (thisNum.getXPos()) * thisNum.getDistance() * unit + center.width - 1;
                float yPos = (float) (thisNum.getYPos()) * thisNum.getDistance() * -unit + center.height - 1;
                int numSize = 3;
                if (thisNum.getVal() == settingsWindow.getNumLookupFlag()) {
                    numSize = 6;
                    thisNum.setBackground(Color.RED);
                }
                if (thisNum.getTheta() == settingsWindow.getThetaLookupFlag()) {
                    numSize = 6;
                    if (thisNum.getVal() == settingsWindow.getNumLookupFlag()) {
                        thisNum.setBackground(Color.MAGENTA);
                    } else {
                        thisNum.setBackground(Color.PINK);
                    }
                    if (thisNum.isPrime()) {
                        thisNum.setBackground(Color.BLUE);
                    }
                }
                if (thisNum.getAffinity() == settingsWindow.getAffineLookupFlag()) {
                    numSize = 6;
                    if (thisNum.getVal() == settingsWindow.getNumLookupFlag()) {
                        thisNum.setBackground(Color.MAGENTA);
                    } else {
                        thisNum.setBackground(Color.YELLOW);
                    }
                    if (thisNum.isPrime()) {
                        thisNum.setBackground(Color.CYAN);
                    }
                }
                if (thisNum.getClicked()) {
                    numSize = 6;
                    thisNum.setBackground(Color.green);
                }
                thisNum.setBounds((int) xPos, (int) yPos, numSize, numSize);
                //shows up if it's prime or the prime button is toggled, or this number is flagged for numLookup or affineLookup or thetaLookup
                if (thisNum.isPrime() || !settingsWindow.getPrimeFlag()
                        || thisNum.getVal() == settingsWindow.getNumLookupFlag()
                        || thisNum.getAffinity() == settingsWindow.getAffineLookupFlag()
                        || thisNum.getTheta() == settingsWindow.getThetaLookupFlag()
                        || thisNum.getClicked()) {
                    thisNum.setVisible(true);
                } else {
                    thisNum.setVisible(false);
                }
                //so people can see what the number is
                thisNum.setToolTipText(Integer.toString(thisNum.getVal()));
                //Number class has a little listener in it
                thisNum.addMouse(thisNum);
                this.add(thisNum);
            }
        }
    }

    //just a quality of life function; it makes the settings panel visible or not so you don't have to retrieve it from the taskbar
    private void settingsButton() {
        JButton sBut = new JButton("View Settings");
        sBut.setBounds(WIDTH - 200, 40, 120, 33);
        sBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (sBut.getText().equals("View Settings")) {
                    settingsWindow.setAlwaysOnTop(true);
                    sBut.setText("Hide Settings");
                } else {
                    settingsWindow.setAlwaysOnTop(false);
                    settingsWindow.toBack();
                    sBut.setText("View Settings");
                }
            }
        });
        this.add(sBut);
    }

    //similar to settings button but for help
    private void helpButton() {
        JButton hBut = new JButton("HELP");
        hBut.setBounds(WIDTH - 200, 600, 120, 33);
        hBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hBut.getText().equals("HELP")) {
                    helpWindow.setAlwaysOnTop(true);
                    hBut.setText("Hide HELP");
                } else {
                    helpWindow.setAlwaysOnTop(false);
                    helpWindow.toBack();
                    hBut.setText("HELP");
                }
            }
        });
        this.add(hBut);
    }

}
