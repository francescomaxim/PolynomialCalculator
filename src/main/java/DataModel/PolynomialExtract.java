package DataModel;

import BusinessLogic.Monom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolynomialExtract {
    private final String input;

    public PolynomialExtract(String input) {
        this.input = input;
    }

    public List<Monom> extractPairs() {
        Pattern pattern = Pattern.compile("[-]?[0-9]*(\\.[0-9]+)?(x(\\^[-]?[0-9]+)?)?");
        Matcher matcher = pattern.matcher(input);
        List<Monom> pairs = new ArrayList<>();
        while (matcher.find()) {
            if(!matcher.group().isEmpty()) {
                pairs.add(new Monom(matcher.group()));
            }
        }
        return pairs;
    }
}
