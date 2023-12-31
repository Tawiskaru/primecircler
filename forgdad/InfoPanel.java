/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forgdad;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author S653957
 */
public class InfoPanel extends JPanel {

    private ArrayList checkList = new ArrayList<Integer>();
    public static int COUNTER = 0;

    public InfoPanel() {
        this.setPreferredSize(new Dimension(300, 600));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(Color.white);
    }

    public void addLabels(int val) {
        Integer value = new Integer(val);
        if (!checkList.contains(value)) {
            COUNTER++;
            checkList.add(value);
            Number infoNum = new Number(val);
            infoNum.altCalcPrime();
            JLabel valLabel = new JLabel(Integer.toString(infoNum.getVal()));
            valLabel.setVisible(true);
            JLabel primeLabel;
            if (infoNum.isPrime()) {
                primeLabel = new JLabel("Prime: true");
            } else {
                primeLabel = new JLabel("Prime: false");
            }
            primeLabel.setVisible(true);
            JLabel affineLabel = new JLabel("Affine #: " + Integer.toString(infoNum.getAffinity()));
            affineLabel.setVisible(true);
            JLabel binaryLabel = new JLabel("Binary: " + Integer.toBinaryString(infoNum.getVal()));
            binaryLabel.setVisible(true);
            JLabel thetaLabel = new JLabel("Degrees: " + Double.toString(infoNum.getTheta()));
            thetaLabel.setVisible(true);

            this.add(valLabel);
            this.add(primeLabel);
            this.add(affineLabel);
            this.add(binaryLabel);
            this.add(thetaLabel);
            this.add(Box.createRigidArea(new Dimension(10, 10)));
        }
        this.setVisible(true);
        try {
            JScrollPane scroller = new JScrollPane(this);
            scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            this.getRootPane().add(scroller);
            this.setVisible(true);
        } catch (Exception e) {
        }
    }

    public void emptyList() {
        checkList.clear();
    }
}
