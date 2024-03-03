package GUI;

import DataModel.PolynomialExtract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Frame extends JFrame implements ActionListener {
    private final static int LUNGIME = 400;
    private final static int LATIME = 500;

    private final static Dimension dimensiunePanel = new Dimension(LUNGIME, LATIME);

    private final JTextField firstPolynomialField;
    private final JTextField secondPolynomialField;
    private final TextsOfFrame textsOfFrame;
    private JLabel result;

    public Frame(){

        firstPolynomialField = new JTextField();
        firstPolynomialField.setBounds(200,80,170,30);
        firstPolynomialField.setBorder(null);

        secondPolynomialField = new JTextField();
        secondPolynomialField.setBounds(200,120,170,30);
        secondPolynomialField.setBorder(null);

        result = new JLabel("Result Goes Here");
        result.setBounds(165,165,200,30);
        result.setFont(new Font("Times New Roman",Font.BOLD,20));
        add(result);

        add(firstPolynomialField);
        add(secondPolynomialField);

        textsOfFrame = new TextsOfFrame();
        add(textsOfFrame);

        textsOfFrame.addButton.addActionListener(this);
        textsOfFrame.exitButton.addActionListener(this);
        textsOfFrame.integrateButton.addActionListener(this);
        textsOfFrame.moduloButton.addActionListener(this);
        textsOfFrame.substractButton.addActionListener(this);
        textsOfFrame.derivateButton.addActionListener(this);
        textsOfFrame.divideButton.addActionListener(this);
        textsOfFrame.multiplicateButton.addActionListener(this);

        setTitle("Polynom Calculator");
        setSize(dimensiunePanel);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

    }

    public String getFirstPolynomialField(){
        return firstPolynomialField.getText();
    }

    public String getSecondPolynomialField(){
        return secondPolynomialField.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PolynomialExtract polynomialExtract = new PolynomialExtract(getFirstPolynomialField());
        HashMap<Integer, Integer> exponents = polynomialExtract.extractExponents();
        PolynomialExtract polynomialExtract2 = new PolynomialExtract(getSecondPolynomialField());
        HashMap<Integer, Integer> exponents2 = polynomialExtract2.extractExponents();
        System.out.println(exponents);
        System.out.println(exponents2);
        if(e.getSource() == textsOfFrame.exitButton){
            dispose();
        }
        if(e.getSource() == textsOfFrame.addButton){
            result.setText("schimbat");
        }
    }
}
