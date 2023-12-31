/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgdad;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author s653957
 */
public class FORGDAD {

    //2D list of all our numbers
    public static ArrayList<ArrayList> listlist = new ArrayList<>();
    //prime numbers from the file (i should think about making a buffer for reference
    public static ArrayList<Integer> fileNums = new ArrayList<>();
    private static int numLayers;
    public static boolean standby = true;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        /*
        Scanner kb = new Scanner(System.in);
        System.out.print("Please input the number of layers: ");
        numLayers = kb.nextInt();
         */
        int[] array = new int[10];
        runner();
        while (standby) {
            System.out.println("standing by");
        }
        fillNumList();
        makeList(numLayers);
        GraphicsRunner run = new GraphicsRunner();
    }

    private static void runner() {
        JFrame runFrame = new JFrame("Startup");
        JPanel startPanel = new JPanel();
        JLabel welcomeL = new JLabel("Welcome to the Prime Circler!");
        JLabel inputL = new JLabel("Please enter the number of circles: ");
        JTextField numPrompt = new JTextField("9", 3);
        JLabel suggestL = new JLabel("(Please only type integers)");
        JButton helpB = new JButton("What's going on?");
        helpB.setBackground(new Color(255, 245, 204));
        helpB.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    HelpWindow helpWindow = new HelpWindow(1);
                    helpWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    helpWindow.setBounds(100, 100, 600, 250);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FORGDAD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        numPrompt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    numLayers = Integer.parseInt(numPrompt.getText());
                    standby=false;
                    runFrame.dispose();
                } catch (Exception ex) {
                    JFrame oopsFrame = new JFrame("oops!");
                    JLabel oopsLabel = new JLabel("please only input integers");
                    oopsFrame.add(oopsLabel);
                    oopsFrame.setBounds(400, 250, 250, 60);
                    oopsFrame.setVisible(true);
                }
            }
        });

        startPanel.setBackground(new Color(255, 250, 230));
        startPanel.add(welcomeL);
        startPanel.add(Box.createVerticalStrut(60));
        startPanel.add(inputL);
        startPanel.add(numPrompt);
        startPanel.add(suggestL);
        startPanel.add(Box.createVerticalStrut(30));
        startPanel.add(helpB);

        runFrame.add(startPanel);
        runFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        runFrame.setBounds(400, 250, 300, 225);
        runFrame.setVisible(true);
    }

    //only called in makeList
    private static void addLayer() {
        ArrayList<Number> thisList = new ArrayList<>();
        //every iteration of this method, this number gets larger
        int listLayer = listlist.size();
        System.out.println("LIST LAYER :" + listLayer);
        for (int i = 0; i < Math.pow(2, listLayer); i++) {
            //works because it indexes the numbers within the layers as well, clever!
            Number thisNum = new Number((int) Math.pow(2, listLayer) + i);
            thisList.add(thisNum);
            //debugging
            System.out.println(thisNum.getVal());
            if (thisNum.isPrime()) {
                System.out.println("PRIME");
            }
        }
        listlist.add(thisList);
    }

    //just calls addLayer as many times as needed
    private static void makeList(int layers) {
        int numLayers = layers;
        //the items in addlayer increment each time it's called, so just call 
        //it as many times as you need
        for (int i = 0; i < numLayers; i++) {
            addLayer();
        }
    }

    //this method provides the program with an ArrayList of prime numbers
    private static void fillNumList() throws FileNotFoundException {
        FileReader primer = new FileReader(new File("primes.txt"));
        Scanner primeReader = new Scanner(primer);
        //goes through the entire list of primes
        while (primeReader.hasNext()) {
            int i = primeReader.nextInt();
            //makes sure that it doesn't go through the ENTIRE list, only the
            //ones we want for time's sake
            if (i <= Math.pow(2, numLayers + 1)) {
                fileNums.add(new Integer(i));
            } else {
                break;
            }
        }
    }

    public static int getNumLayers() {
        return numLayers;
    }

}
