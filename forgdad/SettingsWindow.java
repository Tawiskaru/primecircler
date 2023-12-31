/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgdad;

import usefulfunctions.USEFULFUNCTIONS;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author s653957
 */
public class SettingsWindow extends JFrame {

    private static boolean circleFlag = true;
    private static boolean primeFlag = true;
    private static boolean thetaFlag = true;
    private static boolean squareFlag = false;
    private static boolean fibonacciFlag = false;
    private static int numLookupFlag = 0;
    private static int affineLookupFlag = 0;
    private static double thetaLookupFlag = 1;
    JPanel settingsPanel;

    SettingsWindow(NumPanel numPanel) {
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setTitle("Settings");
        this.setSize(250, 320);
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 20));
        this.add(settingsPanel);
        settings(numPanel);
        settingsPanel.setVisible(true);
        this.setVisible(true);
    }

    private void settings(NumPanel numPanel) {
        circleButton(numPanel);
        primeButton(numPanel);
        numLookupPrompt(numPanel);
        affinityLookupPrompt(numPanel);
        //squaresButton(numPanel);
        //fibonacciButton(numPanel);
        thetaLookupPrompt(numPanel);
    }

    private void circleButton(NumPanel numPanel) {
        JButton circleB = new JButton("turn circles off");
        circleB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (circleFlag) {
                    circleFlag = false;
                    circleB.setText("turn circles on");
                    numPanel.repaint();
                } else {
                    circleFlag = true;
                    circleB.setText("turn circles off");
                    numPanel.repaint();
                }
            }
        });
        settingsPanel.add(circleB);
    }

    private void primeButton(NumPanel numPanel) {
        JButton primeB = new JButton("show all numbers");
        primeB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (primeFlag) {
                    primeFlag = false;
                    primeB.setText("show only primes");
                    numPanel.repaint();
                } else {
                    primeFlag = true;
                    primeB.setText("show all numbers");
                    numPanel.repaint();
                }
            }
        });
        settingsPanel.add(primeB);
    }

    private void numLookupPrompt(NumPanel numPanel) {
        JTextField numLookup = new JTextField("Number Lookup", 10);
        numLookup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numLookupFlag = Integer.parseInt(numLookup.getText());
                numPanel.repaint();
            }
        });
        numLookup.setPreferredSize(new Dimension(60, 27));
        settingsPanel.add(numLookup);
    }

    private void affinityLookupPrompt(NumPanel numPanel) {
        JTextField affineLookup = new JTextField("Affinity Lookup", 10);
        affineLookup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                affineLookupFlag = Integer.parseInt(affineLookup.getText());
                numPanel.repaint();
            }
        });
        affineLookup.setPreferredSize(new Dimension(60, 27));
        settingsPanel.add(affineLookup);
    }

    private void thetaLookupPrompt(NumPanel numPanel) {
        JTextField thetaLookup = new JTextField("Angle Lookup", 10);
        thetaLookup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String operation = thetaLookup.getText();
                double value = USEFULFUNCTIONS.adder(operation);
                while (value >= 360) {
                    value -= 360;
                }
                thetaLookupFlag = value;
                numPanel.repaint();
            }
        });
        thetaLookup.setPreferredSize(new Dimension(60, 27));
        settingsPanel.add(thetaLookup);
    }

    private void squaresButton(NumPanel numPanel) {
        JButton squaresB = new JButton("select squares");
        squaresB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (squareFlag) {
                    squareFlag = false;
                    squaresB.setText("select squares");
                    numPanel.repaint();
                    System.out.println("repainted off");
                } else {
                    squareFlag = true;
                    squaresB.setText("deselect squares");
                    numPanel.repaint();
                    System.out.println("repainted on");
                }
            }
        });
        settingsPanel.add(squaresB);
    }

    private void fibonacciButton(NumPanel numPanel) {
        JButton fibonacciB = new JButton("select fibonacci");
        fibonacciB.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (fibonacciFlag) {
                    fibonacciFlag = false;
                    fibonacciB.setText("select fibonacci");
                    numPanel.repaint();
                } else {
                    fibonacciFlag = true;
                    fibonacciB.setText("deselect fibonacci");
                    numPanel.repaint();
                }
            }
        });
        settingsPanel.add(fibonacciB);
    }

    public boolean getCircleFlag() {
        return circleFlag;
    }

    public boolean getPrimeFlag() {
        return primeFlag;
    }

    public int getNumLookupFlag() {
        return numLookupFlag;
    }

    public int getAffineLookupFlag() {
        return affineLookupFlag;
    }

    public boolean getSquareFlag() {
        return squareFlag;
    }

    public boolean getFibonacciFlag() {
        return fibonacciFlag;
    }

    public double getThetaLookupFlag() {
        return thetaLookupFlag;
    }
}
