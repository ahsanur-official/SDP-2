import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class Modulo_Calculator extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField displayField;
    private JLabel historyLabel;
    
    private double num1 = 0, num2 = 0, result = 0;
    private String operator = ""; // Changed char to String for flexibility
    private boolean isOperatorClicked = false;
    private boolean isResultDisplayed = false;

    // --- COLORS (Real Calculator Style) ---
    private final Color BG_COLOR = new Color(0, 0, 0);            
    private final Color BTN_NUM = new Color(51, 51, 51);          
    private final Color BTN_NUM_HOVER = new Color(70, 70, 70);
    private final Color BTN_OP = new Color(255, 159, 10);         
    private final Color BTN_OP_HOVER = new Color(255, 180, 60);
    private final Color BTN_TOP = new Color(165, 165, 165);       
    private final Color BTN_TOP_HOVER = new Color(190, 190, 190);
    private final Color TEXT_WHITE = Color.WHITE;
    private final Color TEXT_BLACK = Color.BLACK;

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
        setTitle("Calculator Pro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 750); 
        setLocationRelativeTo(null);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBackground(BG_COLOR);
        contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 20));

        // --- DISPLAY AREA ---
        JPanel displayPanel = new JPanel();
        displayPanel.setBackground(BG_COLOR);
        displayPanel.setLayout(new GridLayout(2, 1)); 
        displayPanel.setPreferredSize(new Dimension(380, 160));
        
        historyLabel = new JLabel("");
        historyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        historyLabel.setForeground(Color.LIGHT_GRAY);
        historyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayPanel.add(historyLabel);

        displayField = new JTextField("0");
        displayField.setFont(new Font("Segoe UI", Font.BOLD, 65)); 
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setBackground(BG_COLOR);
        displayField.setForeground(TEXT_WHITE);
        displayField.setBorder(null);
        displayField.setEditable(false);
        displayPanel.add(displayField);

        contentPane.add(displayPanel, BorderLayout.NORTH);

        // --- BUTTONS AREA ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(BG_COLOR);
        
        // REPLACED "(" and ")" with "e" and "π" for better utility
        String[] realLayout = {
             "C",   "Del", "%",   "log",
             "sin", "cos", "tan", "ln",
             "x²",  "√",   "x^y", "/",
             "7",   "8",   "9",   "*",
             "4",   "5",   "6",   "-",   
             "1",   "2",   "3",   "+", 
             "+/-", "0",   ".",   "="    
        };

        buttonPanel.setLayout(new GridLayout(7, 4, 10, 10));

        for (String text : realLayout) {
            BigButton btn = new BigButton(text);
            btn.addActionListener(this);

            if ("C".equals(text) || "Del".equals(text) || "%".equals(text)) {
                btn.setColors(BTN_TOP, BTN_TOP_HOVER, TEXT_BLACK); 
            } 
            else if ("/".equals(text) || "*".equals(text) || "-".equals(text) || "+".equals(text) || "=".equals(text)) {
                btn.setColors(BTN_OP, BTN_OP_HOVER, TEXT_WHITE); 
            } 
            else if (Character.isDigit(text.charAt(0)) && text.length() == 1 || ".".equals(text)) {
                btn.setColors(BTN_NUM, BTN_NUM_HOVER, TEXT_WHITE); 
            } 
            else {
                btn.setColors(new Color(40,40,40), new Color(60,60,60), TEXT_WHITE); 
            }
            buttonPanel.add(btn);
        }

        contentPane.add(buttonPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        // 1. NUMBER INPUT
        if ((cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9') || cmd.equals(".")) {
            if (isResultDisplayed || isOperatorClicked) {
                if(isResultDisplayed) {
                    // Start fresh if a result was just shown and no operator pressed
                    if(!isOperatorClicked) {
                        displayField.setText("");
                        operator = "";
                        num1 = 0;
                    } else {
                         displayField.setText("");
                    }
                } else if (isOperatorClicked) {
                    displayField.setText("");
                }
                
                isResultDisplayed = false;
                isOperatorClicked = false;
            }
            
            // Prevent multiple dots
            if (cmd.equals(".") && displayField.getText().contains(".")) {
                return;
            }
            
            if(displayField.getText().equals("0") && !cmd.equals(".")) {
                 displayField.setText(cmd);
            } else {
                 displayField.setText(displayField.getText() + cmd);
            }
        } 
        
        // 2. CLEAR & DELETE
        else if (cmd.equals("C")) {
            displayField.setText("0");
            historyLabel.setText("");
            num1 = 0; num2 = 0; operator = "";
            isOperatorClicked = false;
            isResultDisplayed = false;
        } 
        else if (cmd.equals("Del")) {
            String txt = displayField.getText();
            if (txt.contains("Error") || txt.contains("NaN") || txt.contains("Infinity")) {
                displayField.setText("0");
            } else {
                if (txt.length() > 0) {
                    displayField.setText(txt.substring(0, txt.length() - 1));
                }
                if (displayField.getText().isEmpty() || displayField.getText().equals("-")) {
                    displayField.setText("0");
                }
            }
        }
        
        // 3. OPERATORS
        else if (isOperator(cmd)) {
            if(displayField.getText().equals("Error")) return;

            if (!operator.isEmpty() && !isResultDisplayed && !isOperatorClicked) {
                // Chained Calculation logic (e.g., 5 + 3 + ...)
                calculateResult(); 
                num1 = result;
                displayField.setText(strip(result));
            } else {
                num1 = Double.parseDouble(displayField.getText());
            }
            
            operator = cmd;
            
            // Format operator symbol for history
            String opSym = operator;
            if(operator.equals("x^y")) opSym = "^";
            
            historyLabel.setText(strip(num1) + " " + opSym);
            isOperatorClicked = true;
            isResultDisplayed = true; // Treats current display as result until new input
        }

        // 4. EQUALS (=)
        else if (cmd.equals("=")) {
            if (!operator.isEmpty()) {
                calculateResult();
                operator = ""; // Clear operator after calculation
            }
        }

        // 5. SCIENTIFIC & SPECIAL FUNCTIONS
        else {
            handleScientific(cmd);
        }
    }

    // --- HELPER METHODS ---

    private boolean isOperator(String cmd) {
        return cmd.equals("+") || cmd.equals("-") || cmd.equals("*") || 
               cmd.equals("/") || cmd.equals("%") || cmd.equals("x^y");
    }

    private void calculateResult() {
        try {
            num2 = Double.parseDouble(displayField.getText());
            
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/": 
                    if(num2 == 0) { displayField.setText("Error"); return; }
                    result = num1 / num2; 
                    break;
                case "%": 
                    if(num2 == 0) { displayField.setText("Error"); return; }
                    result = ((num1 % num2) + num2) % num2; // Custom Modulo
                    break;
                case "x^y": result = Math.pow(num1, num2); break;
            }
            
            displayField.setText(strip(result));
            historyLabel.setText("");
            isResultDisplayed = true;
            isOperatorClicked = false;
            
        } catch (Exception ex) {
            displayField.setText("Error");
        }
    }

    private void handleScientific(String cmd) {
        try {
            if(displayField.getText().contains("i") || displayField.getText().equals("Error")) {
                displayField.setText("Error");
                return;
            }

            double val = Double.parseDouble(displayField.getText());
            double res = 0;
            boolean isComplex = false;

            switch (cmd) {
                case "sin": res = Math.sin(Math.toRadians(val)); break;
                case "cos": res = Math.cos(Math.toRadians(val)); break;
                case "tan": res = Math.tan(Math.toRadians(val)); break;
                case "log": 
                    if(val <= 0) { displayField.setText("Error"); return; }
                    res = Math.log10(val); 
                    break;
                case "ln":  
                    if(val <= 0) { displayField.setText("Error"); return; }
                    res = Math.log(val); 
                    break;
                case "x²":  res = Math.pow(val, 2); break;
                case "n!":  
                    if(val < 0) { displayField.setText("Error"); return; }
                    res = factorial(val); 
                    break;
                case "+/-": res = val * -1; break;
                case "e":   res = Math.E; break;
                case "π":   res = Math.PI; break;
                case "√":   
                    if(val < 0) {
                        res = Math.sqrt(Math.abs(val));
                        isComplex = true;
                    } else {
                        res = Math.sqrt(val);
                    }
                    break;
            }

            if(isComplex) {
                displayField.setText(strip(res) + "i");
            } else {
                displayField.setText(strip(res));
            }
            isResultDisplayed = true;
            
        } catch (Exception ex) { displayField.setText("Error"); }
    }

    private String strip(double d) {
        if (Double.isInfinite(d) || Double.isNaN(d)) return "Error";
        if (d == (long) d) return String.format("%d", (long) d);
        return String.format("%s", d);
    }

    private double factorial(double n) {
        if (n == 0) return 1;
        double fact = 1;
        for (int i = 1; i <= n; i++) fact *= i;
        return fact;
    }

    // --- CUSTOM BUTTON CLASS ---
    class BigButton extends JButton {
        private Color normalColor;
        private Color hoverColor;

        public BigButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorderPainted(false);
            setFont(new Font("Segoe UI", Font.BOLD, 20));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) { setBackground(hoverColor); }
                public void mouseExited(MouseEvent e) { setBackground(normalColor); }
            });
        }

        public void setColors(Color normal, Color hover, Color text) {
            this.normalColor = normal;
            this.hoverColor = hover;
            setForeground(text);
            setBackground(normal);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            g2.setColor(getBackground());
            g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30)); 
            
            g2.setColor(getForeground());
            FontMetrics fm = g2.getFontMetrics();
            Rectangle r = getBounds();
            int x = (r.width - fm.stringWidth(getText())) / 2;
            int y = (r.height - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(getText(), x, y);
            
            g2.dispose();
        }
    }
}
