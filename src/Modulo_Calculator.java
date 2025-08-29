
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Modulo_Calculator extends JFrame {

    private JPanel contentPane;
    private JTextArea displayArea;
    private double memory = 0.0;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Modulo_Calculator frame = new Modulo_Calculator();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public Modulo_Calculator() {
        setResizable(false);
        setTitle("Standard Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 200, 484, 650);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(51, 51, 51));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setBackground(new Color(51, 51, 51));
        displayArea.setForeground(Color.WHITE);
        displayArea.setFont(new Font("Tahoma", Font.BOLD, 32));
        displayArea.setBounds(12, 35, 442, 96);
        contentPane.add(displayArea);

        JLabel lblNewLabel = new JLabel("This Calculator is Designed by Md. Ahsanur Rahaman!");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel.setForeground(new Color(102, 102, 102));
        lblNewLabel.setBounds(12, 4, 442, 26);
        contentPane.add(lblNewLabel);

        // --- Row 1 ---
        JButton button_log = new JButton("Log");
        button_log.setBounds(12, 145, 79, 51);
        button_log.addActionListener(new ImmediateCalculationListener("log"));
        button_log.setForeground(Color.WHITE);
        button_log.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_log.setBackground(new Color(51, 102, 153));
        contentPane.add(button_log);

        JButton button_ln = new JButton("ln");
        button_ln.setBounds(103, 145, 79, 51);
        button_ln.addActionListener(new ImmediateCalculationListener("ln"));
        button_ln.setForeground(Color.WHITE);
        button_ln.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_ln.setBackground(new Color(51, 102, 153));
        contentPane.add(button_ln);

        JButton buttonB = new JButton("(");
        buttonB.setBounds(192, 145, 79, 51);
        buttonB.addActionListener(e -> displayArea.append("("));
        buttonB.setForeground(Color.WHITE);
        buttonB.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonB.setBackground(new Color(51, 102, 153));
        contentPane.add(buttonB);

        JButton buttonB2 = new JButton(")");
        buttonB2.setBounds(281, 145, 79, 51);
        buttonB2.addActionListener(e -> displayArea.append(")"));
        buttonB2.setForeground(Color.WHITE);
        buttonB2.setFont(new Font("Tahoma", Font.BOLD, 20));
        buttonB2.setBackground(new Color(51, 102, 153));
        contentPane.add(buttonB2);

        JButton button_AC = new JButton("AC");
        button_AC.setBounds(375, 145, 79, 51);
        button_AC.addActionListener(e -> displayArea.setText(""));
        button_AC.setBackground(new Color(204, 51, 51));
        button_AC.setForeground(Color.WHITE);
        button_AC.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(button_AC);

        // --- Row 2 ---
        JButton button_sin = new JButton("Sin");
        button_sin.setBounds(12, 208, 79, 51);
        button_sin.addActionListener(new ImmediateCalculationListener("sin"));
        button_sin.setForeground(Color.WHITE);
        button_sin.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_sin.setBackground(new Color(51, 102, 153));
        contentPane.add(button_sin);

        JButton button_cos = new JButton("Cos");
        button_cos.setBounds(103, 208, 79, 51);
        button_cos.addActionListener(new ImmediateCalculationListener("cos"));
        button_cos.setForeground(Color.WHITE);
        button_cos.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_cos.setBackground(new Color(51, 102, 153));
        contentPane.add(button_cos);

        JButton button_tan = new JButton("Tan");
        button_tan.setBounds(192, 208, 79, 51);
        button_tan.addActionListener(new ImmediateCalculationListener("tan"));
        button_tan.setForeground(Color.WHITE);
        button_tan.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_tan.setBackground(new Color(51, 102, 153));
        contentPane.add(button_tan);

        JButton button_square = new JButton("x²");
        button_square.setBounds(281, 208, 79, 51);
        button_square.addActionListener(new ImmediateCalculationListener("sqr"));
        button_square.setForeground(Color.WHITE);
        button_square.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_square.setBackground(new Color(0, 102, 102));
        contentPane.add(button_square);

        JButton btnDel = new JButton("Del");
        btnDel.setBounds(375, 208, 79, 51);
        btnDel.addActionListener(e -> {
            String currentText = displayArea.getText();
            if (currentText.length() > 0) {
                displayArea.setText(currentText.substring(0, currentText.length() - 1));
            }
        });
        btnDel.setBackground(new Color(204, 51, 51));
        btnDel.setForeground(Color.WHITE);
        btnDel.setFont(new Font("Tahoma", Font.BOLD, 20));
        contentPane.add(btnDel);

        // --- Row 3 ---
        JButton button_xn = new JButton("xⁿ");
        button_xn.setBounds(12, 271, 79, 51);
        button_xn.addActionListener(e -> displayArea.append("^"));
        button_xn.setForeground(Color.WHITE);
        button_xn.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_xn.setBackground(new Color(0, 102, 102));
        contentPane.add(button_xn);

        JButton button_x1 = new JButton("x⁻¹");
        button_x1.setBounds(103, 271, 79, 51);
        button_x1.addActionListener(new ImmediateCalculationListener("reciprocal"));
        button_x1.setForeground(Color.WHITE);
        button_x1.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_x1.setBackground(new Color(0, 102, 102));
        contentPane.add(button_x1);

        JButton button_root = new JButton("√");
        button_root.setBounds(192, 271, 79, 51);
        button_root.addActionListener(new ImmediateCalculationListener("sqrt"));
        button_root.setForeground(Color.WHITE);
        button_root.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_root.setBackground(new Color(51, 102, 153));
        contentPane.add(button_root);

        JButton button_plmi = new JButton("+/-");
        button_plmi.setBounds(281, 271, 79, 51);
        button_plmi.addActionListener(new ImmediateCalculationListener("negate"));
        button_plmi.setForeground(Color.WHITE);
        button_plmi.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_plmi.setBackground(new Color(51, 102, 153));
        contentPane.add(button_plmi);

        JButton button_mod = new JButton("Mod");
        button_mod.setBounds(375, 271, 79, 51);
        button_mod.addActionListener(e -> displayArea.append("%"));
        button_mod.setForeground(Color.WHITE);
        button_mod.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_mod.setBackground(new Color(255, 102, 51));
        contentPane.add(button_mod);

        // --- Row 4 ---
        JButton button_7 = new JButton("7");
        button_7.setBounds(12, 334, 79, 51);
        button_7.addActionListener(e -> displayArea.append("7"));
        button_7.setForeground(Color.WHITE);
        button_7.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_7.setBackground(new Color(51, 51, 51));
        contentPane.add(button_7);

        JButton button_8 = new JButton("8");
        button_8.setBounds(103, 334, 79, 51);
        button_8.addActionListener(e -> displayArea.append("8"));
        button_8.setForeground(Color.WHITE);
        button_8.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_8.setBackground(new Color(51, 51, 51));
        contentPane.add(button_8);

        JButton button_9 = new JButton("9");
        button_9.setBounds(192, 334, 79, 51);
        button_9.addActionListener(e -> displayArea.append("9"));
        button_9.setForeground(Color.WHITE);
        button_9.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_9.setBackground(new Color(51, 51, 51));
        contentPane.add(button_9);

        JButton button_div = new JButton("÷");
        button_div.setBounds(281, 334, 79, 51);
        button_div.addActionListener(e -> displayArea.append("/"));
        button_div.setForeground(Color.WHITE);
        button_div.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_div.setBackground(new Color(255, 102, 51));
        contentPane.add(button_div);

        // --- Row 5 ---
        JButton button_4 = new JButton("4");
        button_4.setBounds(12, 397, 79, 51);
        button_4.addActionListener(e -> displayArea.append("4"));
        button_4.setForeground(Color.WHITE);
        button_4.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_4.setBackground(new Color(51, 51, 51));
        contentPane.add(button_4);

        JButton button_5 = new JButton("5");
        button_5.setBounds(103, 397, 79, 51);
        button_5.addActionListener(e -> displayArea.append("5"));
        button_5.setForeground(Color.WHITE);
        button_5.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_5.setBackground(new Color(51, 51, 51));
        contentPane.add(button_5);

        JButton button_6 = new JButton("6");
        button_6.setBounds(192, 397, 79, 51);
        button_6.addActionListener(e -> displayArea.append("6"));
        button_6.setForeground(Color.WHITE);
        button_6.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_6.setBackground(new Color(51, 51, 51));
        contentPane.add(button_6);

        JButton btnX = new JButton("×");
        btnX.setBounds(281, 397, 79, 51);
        btnX.addActionListener(e -> displayArea.append("*"));
        btnX.setForeground(Color.WHITE);
        btnX.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnX.setBackground(new Color(255, 102, 51));
        contentPane.add(btnX);

        // --- Row 6 ---
        JButton button_1 = new JButton("1");
        button_1.setBounds(12, 460, 79, 51);
        button_1.addActionListener(e -> displayArea.append("1"));
        button_1.setForeground(Color.WHITE);
        button_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_1.setBackground(new Color(51, 51, 51));
        contentPane.add(button_1);

        JButton button_2 = new JButton("2");
        button_2.setBounds(103, 460, 79, 51);
        button_2.addActionListener(e -> displayArea.append("2"));
        button_2.setForeground(Color.WHITE);
        button_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_2.setBackground(new Color(51, 51, 51));
        contentPane.add(button_2);

        JButton button_3 = new JButton("3");
        button_3.setBounds(192, 460, 79, 51);
        button_3.addActionListener(e -> displayArea.append("3"));
        button_3.setForeground(Color.WHITE);
        button_3.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_3.setBackground(new Color(51, 51, 51));
        contentPane.add(button_3);

        JButton button_minus = new JButton("–");
        button_minus.setBounds(281, 460, 79, 51);
        button_minus.addActionListener(e -> displayArea.append("-"));
        button_minus.setForeground(Color.WHITE);
        button_minus.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_minus.setBackground(new Color(255, 102, 51));
        contentPane.add(button_minus);

        // --- Row 7 ---
        JButton button_0 = new JButton("0");
        button_0.setBounds(12, 523, 170, 51);
        button_0.addActionListener(e -> displayArea.append("0"));
        button_0.setForeground(Color.WHITE);
        button_0.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_0.setBackground(new Color(51, 51, 51));
        contentPane.add(button_0);

        JButton button_p = new JButton(".");
        button_p.setBounds(192, 523, 79, 51);
        button_p.addActionListener(e -> displayArea.append("."));
        button_p.setForeground(Color.WHITE);
        button_p.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_p.setBackground(new Color(51, 51, 51));
        contentPane.add(button_p);

        JButton button_plus = new JButton("+");
        button_plus.setBounds(281, 523, 79, 51);
        button_plus.addActionListener(e -> displayArea.append("+"));
        button_plus.setForeground(Color.WHITE);
        button_plus.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_plus.setBackground(new Color(255, 102, 51));
        contentPane.add(button_plus);

        // --- Tall Equals Button ---
        JButton button_eq = new JButton("=");
        button_eq.setBounds(375, 334, 79, 240);
        button_eq.addActionListener(e -> {
            if (displayArea.getText().isEmpty()) {
                return;
            }
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            try {
                String expression = displayArea.getText();

                // Handle chained calculations
                if (expression.contains("=")) {
                    expression = expression.substring(expression.lastIndexOf("=") + 2);
                }

                String engineExpression = expression.replaceAll("×", "*")
                        .replaceAll("÷", "/")
                        .replaceAll("–", "-")
                        .replaceAll("\\^", "**");

                // Logic for mathematical modulo
                if (engineExpression.contains("%")) {
                    String trueModFunction = "function mod(a, b) { return ((a % b) + b) % b; }";
                    engine.eval(trueModFunction);
                    engineExpression = engineExpression.replaceAll("(-?[\\d.]+)\\s*%\\s*(-?[\\d.]+)", "mod($1, $2)");
                }

                Object result = engine.eval(engineExpression);
                displayArea.setText(expression + "\n= " + result.toString());
            } catch (ScriptException ex) {
                displayArea.setText("Syntax Error");
            }
        });
        button_eq.setForeground(Color.WHITE);
        button_eq.setFont(new Font("Tahoma", Font.BOLD, 20));
        button_eq.setBackground(new Color(204, 51, 51));
        contentPane.add(button_eq);
    }
    
    private class ImmediateCalculationListener implements ActionListener {

        private final String function;

        public ImmediateCalculationListener(String function) {
            this.function = function;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String currentText = displayArea.getText();
            if (currentText.isEmpty()) {
                return;
            }

            // --- THIS IS THE IMPROVEMENT ---
            // If the display shows a result, use just the result for the new calculation.
            String expressionToEvaluate = currentText;
            if (currentText.contains("=")) {
                expressionToEvaluate = currentText.substring(currentText.lastIndexOf("=") + 2).trim();
            }

            try {
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("JavaScript");
                String expression = expressionToEvaluate.replaceAll("×", "*").replaceAll("÷", "/").replaceAll("–", "-").replaceAll("\\^", "**");

                Object evalResult = engine.eval(expression);
                double number = Double.parseDouble(evalResult.toString());
                double finalResult = 0;

                // This logic correctly handles all the instant functions
                switch (function) {
                    case "sqrt":
                        if (number < 0) {
                            displayArea.setText(new java.text.DecimalFormat("0.##########").format(Math.sqrt(Math.abs(number))) + "i");
                            return; // Exit here for complex numbers
                        }
                        finalResult = Math.sqrt(number);
                        break;
                    case "sqr":
                        finalResult = Math.pow(number, 2);
                        break;
                    case "reciprocal":
                        if (number == 0) {
                            displayArea.setText("Cannot divide by zero");
                            return;
                        }
                        finalResult = 1.0 / number;
                        break;
                    case "negate":
                        finalResult = -number;
                        break;
                    case "sin":
                        finalResult = Math.sin(Math.toRadians(number));
                        break;
                    case "cos":
                        finalResult = Math.cos(Math.toRadians(number));
                        break;
                    case "tan":
                        if ((number - 90) % 180 == 0) {
                            displayArea.setText("Undefined");
                            return;
                        }
                        finalResult = Math.tan(Math.toRadians(number));
                        break;
                    case "log":
                        if (number <= 0) {
                            displayArea.setText("Invalid input for log");
                            return;
                        }
                        finalResult = Math.log10(number);
                        break;
                    case "ln":
                        if (number <= 0) {
                            displayArea.setText("Invalid input for ln");
                            return;
                        }
                        finalResult = Math.log(number);
                        break;
                }

                // Clean up the final result for display
                if (Math.abs(finalResult) < 1E-10) {
                    finalResult = 0;
                }
                displayArea.setText(new java.text.DecimalFormat("0.##########").format(finalResult));

            } catch (Exception ex) {
                displayArea.setText("Syntax Error");
            }
        }
    }
}
