/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgdad;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import usefulfunctions.USEFULFUNCTIONS;

/**
 *
 * @author s653957
 */
public class Number extends JButton {

    private final int val;
    private boolean prime;
    private boolean fibonacci;
    private boolean square;
    private int affinity;
    private int distance;
    private double theta;
    private double xPos;
    private double yPos;
    private boolean clicked;
    static JFrame infoFrame = new JFrame("Info Panel");
    static InfoPanel infoPanel = new InfoPanel();

    //constructor sets value of number and checks for primality
    public Number(int val) {
        this.val = val;
        this.clicked = false;
        calcPrime(FORGDAD.fileNums);
        calcAffinity();
        calcCoordinates();

        infoFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                infoPanel.removeAll();
                infoPanel.emptyList();
                InfoPanel.COUNTER = 0;
            }
        });
    }

    //returns the integer base 10 value of the number
    public int getVal() {
        return this.val;
    }

    //calculates whether the number is prime(true) or composite(false)
    private void calcPrime(ArrayList<Integer> primeList) {
        this.prime = false;

        //iterates over what's left of list
        for (int i = 0; i < primeList.size(); i++) {
            int num = primeList.get(i);
            //if it gets bigger no need to keep checking
            if (num > this.val) {
                break;
            } else if (num == this.val) {
                prime = true;
                //removes it so it doesn't have to check again...keeps it roughly O(n) time
                primeList.remove(i);
                break;
            }
        }
    }

    //calcPrime only works when accessing the List. This is for one time use
    public void altCalcPrime() {
        this.prime = true;
        for (int i = 2; i <= Math.sqrt(val); i++) {
            if (val % i == 0) {
                this.prime = false;
                break;
            }
        }
    }

    //returns whether the number is prime(true) or composite(false)
    public boolean isPrime() {
        return this.prime;
    }

    //calculates and returns if this number is a fibonacci number
    public boolean isFibonacci() {
        fibonacci = false;
        double trialOne = Math.sqrt(5 * val * val - 4);
        double trialTwo = Math.sqrt(5 * val * val + 4);
        if (Math.floor(trialOne) == trialOne || Math.floor(trialTwo) == trialTwo) {
            fibonacci = true;
        }
        return fibonacci;
    }

    //calculates and returns if this number is a perfect square
    public boolean isSquare() {
        square = false;
        double sqrt = Math.sqrt(val);
        if (Math.floor(sqrt) == sqrt) {
            square = true;
        }
        return square;
    }

    //calculates the binary affinity of a number
    private void calcAffinity() {
        int numbits;
        if (this.val == 0 || this.val == 1) {
            numbits = 1;
        } else {
            numbits = (int) (Math.floor(USEFULFUNCTIONS.log2(val))) + 1;
        }
        this.affinity = ((int) (Math.pow(2, numbits - 1)) - val - 1) * -1;
    }

    public int getAffinity() {
        return this.affinity;
    }

    //calculates polar coordinates and gives corresponding x and y coords
    private void calcCoordinates() {
        calcDistance();
        calcTheta();
        //this bit just converts the theta into radians b/c Math.cos and Math.sin deal with radians
        xPos = Math.cos(theta * Math.PI / 180);
        yPos = Math.sin(theta * Math.PI / 180);
        //this gives basically a unit vector (that's why we still need distance)
    }

    //basically calculates the ring that it's on
    private void calcDistance() {
        this.distance = (int) (Math.floor(USEFULFUNCTIONS.log2(val)));
    }

    //calculates the theta in degrees
    private void calcTheta() {
        double numNums = Math.pow(2, this.distance);
        double unit = 360 / numNums;
        double posInLayer = val - Math.pow(2, this.distance);
        this.theta = posInLayer * unit;
    }

    public double getTheta() {
        return this.theta;
    }

    public int getDistance() {
        return this.distance;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public void addMouse(Number me) {

        me.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    infoPanel.addLabels(val);
                    if (infoFrame.getSize().getHeight() < 600) {
                        infoFrame.setBounds(10, 40, 300, 120 * InfoPanel.COUNTER);
                    }
                    infoFrame.add(infoPanel);
                    infoFrame.setAlwaysOnTop(true);
                    infoFrame.setVisible(true);
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    if (!Number.this.clicked) {
                        Number.this.setSize(new Dimension(6, 6));
                        Number.this.setBackground(Color.green);
                        clicked = true;
                    } else {
                        Number.this.setSize(new Dimension(3, 3));
                        Number.this.setBackground(Color.black);
                        clicked = false;
                    }
                }
            }
        });

    }

    public boolean getClicked() {
        return clicked;
    }

    public void setClicked(boolean flag) {
        clicked = flag;
    }
}
