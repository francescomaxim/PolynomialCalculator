package DataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialExtract {
    private final String input;
    private int grad = 1;
    private int exp = 1;

    public PolynomialExtract(String input) {
        this.input = input;
    }

    private List<String> extractPairs() {
        Pattern pattern = Pattern.compile("(-?\\d*x?(\\^-?\\d*)?)");
        Matcher matcher = pattern.matcher(input);
        List<String> pairs = new ArrayList<>();
        while (matcher.find()) {
            pairs.add(matcher.group(1));
        }
        return pairs;
    }

    public HashMap<Integer, Integer> extractExponents() {
        List<String> pairs = extractPairs();
        HashMap<Integer, Integer> exponents = new HashMap<>();
        for (String i : pairs) {
            if (i.isEmpty()) {
                continue;
            }
            grad = exp = 1;
            calculateGradAndExp(i);
            if (!exponents.containsKey(grad)) {
                exponents.put(grad, exp);
            } else {
                if (exp + exponents.get(grad) != 0) {
                    exponents.put(grad, exp + exponents.get(grad));
                } else {
                    exponents.remove(grad);
                }
            }
        }
        return exponents;
    }

    private void calculateGradAndExp(String i) {
        Pattern p = Pattern.compile("(-?\\d+)");
        Matcher m = p.matcher(i);
        if (m.find()) {
            int q1 = Integer.parseInt(m.group(1));
            if (m.find()) {
                grad = Integer.parseInt(m.group(1));
                exp = q1;
            } else {
                if (i.contains("^")) {
                    grad = q1;
                } else {
                    exp = q1;
                    if (!i.contains("x")) {
                        grad = 0;
                    }
                }
            }
        } else {
            if (!i.contains("x")) {
                grad = exp = 0;
            }
        }
        if(i.charAt(0) == '-' && exp == 1){
            exp *= (-1);
        }
    }
}
