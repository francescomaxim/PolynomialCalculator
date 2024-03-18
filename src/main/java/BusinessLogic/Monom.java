package BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monom {
    private String monomString;
    private double exp;
    private int grad;
    public Monom(String monomString){
        this.monomString = monomString;
        setCoeficients();
    }

    public Monom(int grad, double exp){
        this.grad = grad;
        this.exp = exp;
    }

    private void setCoeficients(){
        List<String> subStrings = extractSubStrings();
        if(subStrings.isEmpty()){
            exp = 0.0;
            grad = 0;
        }else {
            exp = Double.parseDouble(subStrings.get(0));
            if (subStrings.size() == 2) {
                grad = Integer.parseInt(subStrings.get(1));
            } else {
                grad = 0;
                if (monomString.contains("^")) {
                    grad = Integer.parseInt(subStrings.get(0));
                    exp = 1;
                } else {
                    if (monomString.contains("x")) {
                        grad = 1;
                    }
                }
            }
        }
    }

    private List<String> extractSubStrings() {
        List<String> subStrings = new ArrayList<>();
        Pattern pattern = Pattern.compile("[-]?[0-9]*(\\.[0-9]+)?");
        Matcher matcher = pattern.matcher(monomString);
        while (matcher.find()) {
            if (!matcher.group().isEmpty()) {
                if (matcher.group().equals("-")) {
                    subStrings.add("-1");
                } else {
                    subStrings.add(matcher.group());
                }
            }
            if (monomString.contains("x") && subStrings.isEmpty()) {
                subStrings.add("1");
            }
        }
        return subStrings;
    }

    public String toString(){
        return "Exp : " + exp + " Grad : " + grad;
    }

    public double getExp() {
        return exp;
    }

    public int getGrad() {
        return grad;
    }
}
