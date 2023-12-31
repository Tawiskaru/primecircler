/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgdad;

import java.util.ArrayList;
import javax.swing.JButton;
import usefulfunctions.USEFULFUNCTIONS;

/**
 *
 * @author s653957
 */
public class NumberSafe {
    private final int val;
    private boolean prime;
    private int affinity;
    private int distance;
    private double theta;
    private double xPos;
    private double yPos;

    //constructor sets value of number and checks for primality
    public NumberSafe(int val) {
        this.val = val;
        calcPrime(FORGDAD.fileNums);
        calcCoordinates();
    }
    
    //returns the integer base 10 value of the number
    public int getVal(){
        return this.val;
    }
    
    //calculates whether the number is prime(true) or composite(false)
    private void calcPrime(ArrayList<Integer> primeList){
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
    
    //returns whether the number is prime(true) or composite(false)
    public boolean isPrime(){
        return this.prime;
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
    
    public int getAffinity(){
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

    public int getDistance() {
        return this.distance;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }
}
