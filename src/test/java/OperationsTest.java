import BusinessLogic.Monom;
import BusinessLogic.Polynom;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class OperationsTest {
    Polynom polynom1;
    Polynom polynom2;
    HashMap<Integer, Double> expected;
    public void buffer(){
        Monom monom1 = new Monom("x^2");
        Monom monom2 = new Monom("x");
        List<Monom> monomList1 = new ArrayList<>();
        List<Monom> monomList2 = new ArrayList<>();
        monomList1.add(monom1);
        monomList2.add(monom2);

        polynom1 = new Polynom(monomList1);
        polynom2 = new Polynom(monomList2);

        expected = new HashMap<>();

    }
    @Test
    public void addTest(){ // x^2+x
        buffer();
        expected.put(2,1.0);
        expected.put(1,1.0);
        assertEquals(polynom1.add(polynom2.getMyPolynom()),expected);
    }

    @Test
    public void subTest(){ // x^2-x
        buffer();
        expected.put(2,1.0);
        expected.put(1,-1.0);
        assertEquals(polynom1.sub(polynom2.getMyPolynom()),expected);
    }

    @Test
    public void multiplyTest(){ //x^3
        buffer();
        expected.put(3,1.0);
        assertEquals(polynom1.multiplicate(polynom2.getMyPolynom()),expected);
    }

    @Test
    public void divTest(){ // x
        buffer();
        expected.put(1,1.0);
        assertEquals(polynom1.divide(polynom2.getMyPolynom()),expected);
    }

    @Test
    public void moduloTest(){ // 0
        buffer();
        assertEquals(polynom1.modulo(polynom2.getMyPolynom()),expected);
    }

    @Test
    public void derivateTest(){ // 2x
        buffer();
        expected.put(1, 2.0);
        assertEquals(polynom1.derivative(),expected);
    }

    @Test
    public void integrateTest(){ // 1/3 * x^3
        buffer();
        expected.put(3, 0.33);
        assertEquals(polynom1.integration(),expected);
    }

}
