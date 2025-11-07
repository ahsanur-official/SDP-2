
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.Scanner;
import java.awt.Button;
import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.Dialog.ModalExclusionType;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.HierarchyBoundsAdapter;
import java.awt.event.HierarchyEvent;

public class Modulo_Calculator extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    double first;
    double second;
    double result;
    String operation;
    String answer;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Modulo_Calculator frame = new Modulo_Calculator();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Modulo_Calculator() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\msi\\OneDrive\\Desktop\\Calc.png"));
        setForeground(new Color(25, 25, 112));
        setBackground(new Color(25, 25, 112));
        setTitle("Standard Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 389, 535);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 51, 51));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBackground(new Color(51, 51, 51));
        textField.setForeground(new Color(255, 255, 255));
        textField.setFont(new Font("Tahoma", Font.BOLD, 24));
        textField.setBounds(12, 35, 348, 58);
        contentPane.add(textField);
        textField.setColumns(12);

        JLabel lblNewLabel = new JLabel("This Calculator is Designed by Md. Ahsanur Rahaman!");
        lblNewLabel.setBackground(new Color(102, 102, 153));
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel.setForeground(new Color(102, 102, 102));
        lblNewLabel.setBounds(12, 4, 348, 26);
        contentPane.add(lblNewLabel);

        JButton button_00 = new JButton("00");
        button_00.setBackground(new Color(51, 51, 51));
        button_00.setForeground(new Color(255, 255, 255));
        button_00.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText() + button_00.getText();
                textField.setText(number);
            }
        });
        button_00.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_00.setBounds(12, 424, 79, 51);
        contentPane.add(button_00);

        JButton button_0 = new JButton("0");
        button_0.setBackground(new Color(51, 51, 51));
        button_0.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent arg0) {
                textField.setText("0");
            }
        });
        button_0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String number = textField.getText() + button_0.getText();
                textField.setText(number);
            }
        });
        button_0.setForeground(new Color(255, 255, 255));
        button_0.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_0.setBounds(103, 424, 79, 51);
        contentPane.add(button_0);

        JButton button_p = new JButton(".");
        button_p.setBackground(new Color(51, 51, 51));
        button_p.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText() + button_p.getText();
                textField.setText(number);
            }
        });
        button_p.setForeground(new Color(255, 255, 255));
        button_p.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_p.setBounds(190, 424, 79, 51);
        contentPane.add(button_p);

        JButton button_eq = new JButton("=");
        button_eq.setBackground(new Color(204, 51, 51));
        button_eq.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String answer;
                second = Double.parseDouble(textField.getText());
                if (operation == "+") {
                    result = first + second;
                    textField.setText(String.valueOf(result));
                } else if (operation == "-") {
                    result = first - second;
                    textField.setText(String.valueOf(result));
                } else if (operation == "*") {
                    result = first * second;
                    textField.setText(String.valueOf(result));
                } else if (operation == "/") {
                    result = first / second;
                    textField.setText(String.valueOf(result));
                } else if (operation == "%") {
                    result = ((first % second) + second) % second;
                    textField.setText(String.valueOf(result));
                } else if (operation == "pow") {
                    result = Math.pow(first, second);
                    textField.setText(String.valueOf(result));
                }
            }
        });
        button_eq.setForeground(new Color(255, 255, 255));
        button_eq.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_eq.setBounds(281, 424, 79, 51);
        contentPane.add(button_eq);

        JButton button_1 = new JButton("1");
        button_1.setBackground(new Color(51, 51, 51));
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText() + button_1.getText();
                textField.setText(number);
            }
        });
        button_1.setForeground(new Color(255, 255, 255));
        button_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_1.setBounds(12, 360, 79, 51);
        contentPane.add(button_1);

        JButton button_2 = new JButton("2");
        button_2.setBackground(new Color(51, 51, 51));
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText() + button_2.getText();
                textField.setText(number);
            }
        });
        button_2.setForeground(new Color(255, 255, 255));
        button_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_2.setBounds(103, 360, 79, 51);
        contentPane.add(button_2);

        JButton button_3 = new JButton("3");
        button_3.setBackground(new Color(51, 51, 51));
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText() + button_3.getText();
                textField.setText(number);
            }
        });
        button_3.setForeground(new Color(255, 255, 255));
        button_3.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_3.setBounds(190, 360, 79, 51);
        contentPane.add(button_3);

        JButton button_plus = new JButton("+");
        button_plus.setBackground(new Color(255, 102, 51));
        button_plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first = Double.parseDouble(textField.getText());
                textField.setText("");
                operation = "+";
            }
        });
        button_plus.setForeground(new Color(255, 255, 255));
        button_plus.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_plus.setBounds(281, 360, 79, 51);
        contentPane.add(button_plus);

        JButton button_4 = new JButton("4");
        button_4.setBackground(new Color(51, 51, 51));
        button_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText() + button_4.getText();
                textField.setText(number);
            }
        });
        button_4.setForeground(new Color(255, 255, 255));
        button_4.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_4.setBounds(12, 296, 79, 51);
        contentPane.add(button_4);

        JButton button_5 = new JButton("5");
        button_5.setBackground(new Color(51, 51, 51));

        button_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText() + button_5.getText();
                textField.setText(number);
            }
        });
        button_5.setForeground(new Color(255, 255, 255));
        button_5.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_5.setBounds(103, 296, 79, 51);
        contentPane.add(button_5);

        JButton button_6 = new JButton("6");
        button_6.setBackground(new Color(51, 51, 51));
        button_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText() + button_6.getText();
                textField.setText(number);
            }
        });
        button_6.setForeground(new Color(255, 255, 255));
        button_6.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_6.setBounds(190, 296, 79, 51);
        contentPane.add(button_6);

        JButton button_minus = new JButton("\u2013");
        button_minus.setBackground(new Color(255, 102, 51));
        button_minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first = Double.parseDouble(textField.getText());
                textField.setText("");
                operation = "-";
            }
        });
        button_minus.setForeground(new Color(255, 255, 255));
        button_minus.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_minus.setBounds(281, 296, 79, 51);
        contentPane.add(button_minus);

        JButton button_7 = new JButton("7");
        button_7.setBackground(new Color(51, 51, 51));
        button_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText() + button_7.getText();
                textField.setText(number);
            }
        });
        button_7.setForeground(new Color(255, 255, 255));
        button_7.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_7.setBounds(12, 232, 79, 51);
        contentPane.add(button_7);

        JButton button_8 = new JButton("8");
        button_8.setBackground(new Color(51, 51, 51));
        button_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText() + button_8.getText();
                textField.setText(number);
            }
        });
        button_8.setForeground(new Color(255, 255, 255));
        button_8.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_8.setBounds(103, 232, 79, 51);
        contentPane.add(button_8);

        JButton button_9 = new JButton("9");
        button_9.setBackground(new Color(51, 51, 51));
        button_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String number = textField.getText() + button_9.getText();
                textField.setText(number);
            }
        });
        button_9.setForeground(new Color(255, 255, 255));
        button_9.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_9.setBounds(190, 232, 79, 51);
        contentPane.add(button_9);

        JButton btnX = new JButton("\u00D7");
        btnX.setBackground(new Color(255, 102, 51));
        btnX.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first = Double.parseDouble(textField.getText());
                textField.setText("");
                operation = "*";
            }
        });
        btnX.setForeground(new Color(255, 255, 255));
        btnX.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnX.setBounds(281, 232, 79, 51);
        contentPane.add(btnX);

        JButton button_C = new JButton("C");
        button_C.setBackground(new Color(204, 51, 51));
        button_C.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText(null);
            }
        });
        button_C.setForeground(new Color(255, 255, 255));
        button_C.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_C.setBounds(12, 106, 79, 51);
        contentPane.add(button_C);

        JButton button_percent = new JButton("%");
        button_percent.setBackground(new Color(51, 102, 153));
        button_percent.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first = Double.parseDouble(textField.getText());
                textField.setText("");
                operation = "%";
            }
        });
        button_percent.setForeground(new Color(255, 255, 255));
        button_percent.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_percent.setBounds(190, 168, 79, 51);
        contentPane.add(button_percent);

        JButton btnDel = new JButton("Del");
        btnDel.setBackground(new Color(204, 51, 51));
        btnDel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String backSpace = null;
                if (textField.getText().length() > 0) {
                    StringBuilder str = new StringBuilder(textField.getText());
                    str.deleteCharAt(textField.getText().length() - 1);
                    backSpace = str.toString();
                    textField.setText(backSpace);
                }
            }
        });
        btnDel.setForeground(new Color(255, 255, 255));
        btnDel.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnDel.setBounds(281, 106, 79, 51);
        contentPane.add(btnDel);

        JButton button_div = new JButton("\u00F7");
        button_div.setBackground(new Color(255, 102, 51));
        button_div.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first = Double.parseDouble(textField.getText());
                textField.setText("");
                operation = "/";
            }
        });
        button_div.setForeground(new Color(255, 255, 255));
        button_div.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_div.setBounds(281, 168, 79, 51);
        contentPane.add(button_div);

        JButton button_root = new JButton("\u221A");
        button_root.setBackground(new Color(51, 102, 153));
        button_root.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    double number = Double.parseDouble(textField.getText());
                    if (number < 0) {
                        double imaginaryPart = Math.sqrt(Math.abs(number));
                        textField.setText(String.valueOf(imaginaryPart) + "i");
                    } else {
                        double realPart = Math.sqrt(number);
                        textField.setText(String.valueOf(realPart));
                    }
                } catch (NumberFormatException ex) {
                    textField.setText("Error");
                }
            }
        });
        button_root.setForeground(new Color(255, 255, 255));
        button_root.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_root.setBounds(103, 168, 79, 51);
        contentPane.add(button_root);

        JButton button_plmi = new JButton("+/_");
        button_plmi.setBackground(new Color(51, 102, 153));
        button_plmi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first = Double.parseDouble(String.valueOf(textField.getText()));
                first = first * (-1);
                textField.setText(String.valueOf(first));

            }
        });
        button_plmi.setForeground(new Color(255, 255, 255));
        button_plmi.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_plmi.setBounds(12, 168, 79, 51);
        contentPane.add(button_plmi);

        JButton button_square = new JButton("x\u00B2");
        button_square.setBackground(new Color(0, 102, 102));
        button_square.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                double number = Double.parseDouble(String.valueOf(textField.getText()));
                number = number * number;
                textField.setText(String.valueOf(number));
            }
        });
        button_square.setForeground(new Color(255, 255, 255));
        button_square.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_square.setBounds(103, 106, 79, 51);
        contentPane.add(button_square);

        JButton button_xn = new JButton("x\u207F");
        button_xn.setBackground(new Color(0, 102, 102));
        button_xn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                first = Double.parseDouble(textField.getText());
                textField.setText("");
                operation = "pow";

            }
        });
        button_xn.setForeground(new Color(255, 255, 255));
        button_xn.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_xn.setBounds(190, 106, 79, 51);
        contentPane.add(button_xn);
    }
}
