package GUI;

import BusinessLogic.Polynom;
import DataModel.PolynomialExtract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        result.setBounds(120,165,250,30);
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
        if(e.getSource() == textsOfFrame.exitButton){
            dispose();
        }

        PolynomialExtract f1 = new PolynomialExtract(firstPolynomialField.getText());
        PolynomialExtract f2 = new PolynomialExtract(secondPolynomialField.getText());
        Polynom p1 = new Polynom(f1.extractPairs());
        Polynom p2 = new Polynom(f2.extractPairs());

        if(e.getSource() == textsOfFrame.addButton){
            HashMap<Integer, Double> newPolynom = p1.add(p2.getMyPolynom());
            String s = p1.polynomToString(newPolynom);
            if(!s.isEmpty()) {
                result.setText(s);
            }else{
                result.setText("0");
            }
        }

        if(e.getSource() == textsOfFrame.substractButton){
            HashMap<Integer, Double> newPolynom = p1.sub(p2.getMyPolynom());
            String s = p1.polynomToString(newPolynom);
            if(!s.isEmpty()) {
                result.setText(s);
            }else{
                result.setText("0");
            }
        }

        if(e.getSource() == textsOfFrame.derivateButton){
            HashMap<Integer, Double> newPolynom = p1.derivative();
            String s = p1.polynomToString(newPolynom);
            if(!s.isEmpty()) {
                result.setText(s);
            }else{
                result.setText("0");
            }
        }

        if(e.getSource() == textsOfFrame.integrateButton){
            HashMap<Integer, Double> newPolynom = p1.integration();
            String s = p1.polynomToString(newPolynom);
            if(!s.isEmpty()) {
                result.setText(s);
            }else{
                result.setText("0");
            }
        }

        if(e.getSource() == textsOfFrame.multiplicateButton){
            HashMap<Integer, Double> newPolynom = p1.multiplicate(p2.getMyPolynom());
            String s = p1.polynomToString(newPolynom);
            if(!s.isEmpty()) {
                result.setText(s);
            }else{
                result.setText("0");
            }
        }

        if(e.getSource() == textsOfFrame.divideButton){
            HashMap<Integer, Double> newPolynom = p1.divide(p2.getMyPolynom());
            if(newPolynom != null) {
                String s = p1.polynomToString(newPolynom);
                if (!s.isEmpty()) {
                    result.setText(s);
                } else {
                    result.setText("0");
                }
            }else{
                String s = "ce prost";
                result.setText(s);
            }
        }

        if(e.getSource() == textsOfFrame.moduloButton){
            HashMap<Integer, Double> newPolynom = p1.modulo(p2.getMyPolynom());
            if(newPolynom != null) {
                String s = p1.polynomToString(newPolynom);
                if (!s.isEmpty()) {
                    result.setText(s);
                } else {
                    result.setText("0");
                }
            }else{
                String s = "ce prost";
                result.setText(s);
            }
        }

    }
}
