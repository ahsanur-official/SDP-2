
/**
 * This class replaces your old ComplexParser
 * It is a real, working expression parser.
 * It now correctly handles "5i", "(2+3)i", and "ii"
 */
final class ComplexParser {

    public static Complex evaluate(String expr) {
        // Create a new parser instance for each evaluation
        return new ComplexParser(expr).parse();
    }

    private final String expr;
    private int pos;
    private int ch;

    private ComplexParser(String expr) {
        this.expr = expr.replaceAll(" ", ""); // Remove spaces
        this.pos = -1;
    }

    private void nextChar() {
        ch = (++pos < expr.length()) ? expr.charAt(pos) : -1;
    }

    private boolean eat(int charToEat) {
        while (ch == ' ') nextChar();
        if (ch == charToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    private Complex parse() {
        nextChar();
        Complex x = parseExpression();
        if (pos < expr.length()) throw new RuntimeException("Unexpected: " + (char) ch);
        return x;
    }

    // Handles + and -
    private Complex parseExpression() {
        Complex x = parseTerm();
        for (;;) {
            if (eat('+')) x = x.add(parseTerm());
            else if (eat('-')) x = x.sub(parseTerm());
            else return x;
        }
    }

    // Handles *, /, %
    private Complex parseTerm() {
        Complex x = parseFactor();
        for (;;) {
            if (eat('*')) x = x.mul(parseFactor());
            else if (eat('/')) x = x.div(parseFactor());
            else if (eat('%')) x = x.mod(parseFactor());
            else return x;
        }
    }

    // Handles ^ (power) and functions/numbers/parentheses
    private Complex parseFactor() {
        if (eat('+')) return parseFactor(); // Unary plus
        if (eat('-')) return parseFactor().negate(); // Unary minus

        Complex x;
        int startPos = this.pos;

        if (eat('(')) { // Parentheses
            x = parseExpression();
            eat(')');
        } else if ((ch >= '0' && ch <= '9') || ch == '.') { // Numbers
            while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
            double num = Double.parseDouble(expr.substring(startPos, this.pos));
            x = new Complex(num, 0);
        } else if (ch == 'i') { // Imaginary constant
            nextChar();
            x = new Complex(0, 1);
        } else if (ch >= 'a' && ch <= 'z') { // Functions
            while (ch >= 'a' && ch <= 'z') nextChar();
            String func = expr.substring(startPos, this.pos);
            x = parseFactor(); // Get the argument
            switch (func) {
                case "sin": x = x.sin(); break;
                case "cos": x = x.cos(); break;
                case "tan": x = x.tan(); break;
                case "sqrt": x = x.sqrt(); break;
                case "ln": x = x.ln(); break;
                case "log": x = x.log10(); break;
                default: throw new RuntimeException("Unknown function: " + func);
            }
        } else {
            throw new RuntimeException("Unexpected: " + (char) ch);
        }

        if (eat('^')) x = x.pow(parseFactor()); // Power

        // *** FIX FOR COMPLEX NUMBERS ***
        // Check for implicit multiplication with 'i'
        // This handles cases like "5i", "(1+1)i", and "ii"
        if (eat('i')) {
            x = x.mul(new Complex(0, 1));
        }

        return x;
    }
}


/**
 * This class replaces your old Complex class.
 * It has the corrected sqrt() method and new functions like pow() and exp().
 */
class Complex {
    final double r; // Real part
    final double i; // Imaginary part

    public Complex(double r, double i) {
        this.r = r;
        this.i = i;
    }

    public Complex add(Complex c) {
        return new Complex(r + c.r, i + c.i);
    }

    public Complex sub(Complex c) {
        return new Complex(r - c.r, i - c.i);
    }

    public Complex mul(Complex c) {
        return new Complex(r * c.r - i * c.i, r * c.i + i * c.r);
    }

    public Complex div(Complex c) {
        double d = c.r * c.r + c.i * c.i;
        if (d == 0) return new Complex(Double.NaN, Double.NaN); // Division by zero
        return new Complex((r * c.r + i * c.i) / d, (i * c.r - r * c.i) / d);
    }
    
    // Modulo - uses the 'fmod' logic, (a - b * trunc(a/b))
    // We assume this is what is wanted for real numbers, as complex mod is not standard.
    public Complex mod(Complex c) {
        // This parser only handles real numbers for %
        // For complex numbers, this behavior is undefined in a simple calculator
        return new Complex(this.r % c.r, 0); // Only mod the real part
    }

    public Complex negate() {
        return new Complex(-r, -i);
    }

    public Complex reciprocal() {
        double d = r * r + i * i;
        if (d == 0) return new Complex(Double.NaN, Double.NaN);
        return new Complex(r / d, -i / d);
    }

    // *** FIXED SQUAREROOT ***
    // Uses atan2(i, r) to get the correct angle for all quadrants.
    public Complex sqrt() {
        double m = Math.hypot(r, i);
        double a = Math.atan2(i, r); // This is the correct way to find the angle
        return new Complex(Math.sqrt(m) * Math.cos(a / 2), Math.sqrt(m) * Math.sin(a / 2));
    }

    public Complex sin() {
        return new Complex(Math.sin(r) * Math.cosh(i), Math.cos(r) * Math.sinh(i));
    }

    public Complex cos() {
        return new Complex(Math.cos(r) * Math.cosh(i), -Math.sin(r) * Math.sinh(i));
    }

    public Complex tan() {
        return sin().div(cos());
    }

    // Natural log (ln)
    public Complex ln() {
        double m = Math.hypot(r, i);
        double angle = Math.atan2(i, r);
        return new Complex(Math.log(m), angle);
    }

    // Base-10 log
    public Complex log10() {
        return this.ln().div(new Complex(Math.log(10), 0));
    }

    // Exponent (e^z)
    public Complex exp() {
        return new Complex(Math.exp(r) * Math.cos(i), Math.exp(r) * Math.sin(i));
    }

    // Power (a^b = exp(b * ln(a)))
    public Complex pow(Complex c) {
        if (this.r == 0 && this.i == 0) return new Complex(0, 0); // 0^c = 0
        return this.ln().mul(c).exp();
    }

    @Override
    public String toString() {
        // Clean up output for pure real or pure imaginary numbers
        double real = this.r;
        double imag = this.i;

        // Handle very small numbers (close to zero)
        if (Math.abs(real) < 1e-9) real = 0.0;
        if (Math.abs(imag) < 1e-9) imag = 0.0;

        if (imag == 0.0) { // Pure real
            return String.format("%.6f", real).replaceAll("\\.?0+$", "");
        }
        
        if (real == 0.0) { // Pure imaginary
            if (imag == 1.0) return "i";
            if (imag == -1.0) return "-i";
            return String.format("%.6fi", imag).replaceAll("\\.?0+$", "");
        }

        // Full complex number
        String sign = (imag < 0) ? "" : "+";
        if (imag == 1.0) return String.format("%.6f+i", real).replaceAll("\\.?0+$", "");
        if (imag == -1.0) return String.format("%.6f-i", real).replaceAll("\\.?0+$", "");
        
        return String.format("%.6f%s%.6fi", real, sign, imag)
                .replaceAll("\\.?0+\\+", "+") // Clean up 5.000+...
                .replaceAll("\\.?0+-", "-");  // Clean up 5.000-...
    }
}