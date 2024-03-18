package GUI;

import javax.swing.*;
import java.awt.*;

class TextsOfFrame extends JPanel{

    JLabel title;

    JLabel firstPolynomial;
    JLabel francesco;
    JLabel secondPolynomial;
    JLabel resultText;

    JButton multiplicateButton;
    JButton divideButton;
    JButton addButton;
    JButton substractButton;
    JButton moduloButton;
    JButton exitButton;
    JButton integrateButton;
    JButton derivateButton;
    protected TextsOfFrame(){

        francesco = new JLabel("Francesco Maxim - 30224");
        francesco.setBounds(30,425,400,30);
        francesco.setFont(new Font("Times New Roman",Font.BOLD,30));
        add(francesco);

        title = new JLabel("Polynomial Calculator");
        title.setBounds(50,20,300,30);
        title.setFont(new Font("Times New Roman",Font.BOLD,30));
        add(title);

        firstPolynomial = new JLabel("First Polynomial = ");
        firstPolynomial.setBounds(30,80,170,30);
        firstPolynomial.setFont(new Font("Times New Roman",Font.BOLD,20));
        add(firstPolynomial);

        secondPolynomial = new JLabel("Second Polynomial = ");
        secondPolynomial.setBounds(15,120,200,30);
        secondPolynomial.setFont(new Font("Times New Roman",Font.BOLD,20));
        add(secondPolynomial);

        resultText = new JLabel("Result = ");
        resultText.setBounds(35,165,200,30);
        resultText.setFont(new Font("Times New Roman",Font.BOLD,20));
        add(resultText);

        multiplicateButton = new JButton("Multiplicate");
        multiplicateButton.setBounds(10,220,180,40);
        multiplicateButton.setFocusable(false);
        add(multiplicateButton);

        divideButton = new JButton("Divide");
        divideButton.setBounds(10,270,180,40);
        divideButton.setFocusable(false);
        add(divideButton);

        addButton = new JButton("Add");
        addButton.setBounds(10,320,180,40);
        addButton.setFocusable(false);
        add(addButton);

        derivateButton = new JButton("Derivate");
        derivateButton.setBounds(10,370,180,40);
        derivateButton.setFocusable(false);
        add(derivateButton);

        substractButton = new JButton("Substract");
        substractButton.setBounds(200,220,180,40);
        substractButton.setFocusable(false);
        add(substractButton);

        moduloButton = new JButton("Modulo");
        moduloButton.setBounds(200,270,180,40);
        moduloButton.setFocusable(false);
        add(moduloButton);

        integrateButton = new JButton("Integrate");
        integrateButton.setBounds(200,320,180,40);
        integrateButton.setFocusable(false);
        add(integrateButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(200,370,180,40);
        exitButton.setFocusable(false);
        add(exitButton);

        setLayout(null);
        setBackground(new Color(188, 156, 227));

    }

}
