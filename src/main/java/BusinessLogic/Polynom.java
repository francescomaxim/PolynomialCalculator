package BusinessLogic;
import java.util.*;
public class Polynom {
    private List<Monom> monomList;
    private HashMap<Integer, Double> myPolynom = new HashMap<>();
    public Polynom(List<Monom> monomList) {
        this.monomList = monomList;
        monomsToPolynom();
    }

    public String polynomToString(HashMap<Integer, Double> polynom){
        String forScreen = "";
        Monom[] forPrint = sortMonoms(polynom);
        for(Monom i : forPrint){
            if(i.getExp() > 0.0){
                forScreen = forScreen + "+";
                if(i.getExp() != 1){
                    forScreen = forScreen + i.getExp();
                }
            }else{
                if(i.getExp() < 0.0){
                    if(i.getExp() != -1.00){
                        forScreen = forScreen + i.getExp();
                    }
                }
            }
            if(i.getGrad() != 0){
                if(i.getExp() == -1){
                    forScreen = forScreen + "-";
                }
                forScreen = forScreen + "x";
                if(i.getGrad() != 1){
                    forScreen = forScreen + "^" + i.getGrad();
                }
            }else{
                if(i.getExp() == 1 || i.getExp() == -1) {
                    forScreen = forScreen + i.getExp();
                }
            }
        }
        return forScreen;
    }

//    public String polynomToString(HashMap<Integer, Double> polynom) {
//        String forScreen = "";
//        Monom[] forPrint = sortMonoms(polynom);
//        for (Monom i : forPrint) {
//            if (i.getExp() != 0) {
//                if (i.getExp() < 0) {
//                    if (i.getExp() != -1 && i.getGrad() != 0) {
//                        forScreen = forScreen + "-" + i.getExp();
//                    } else {
//                        if (i.getExp() == -1) {
//                            forScreen = forScreen + "-";
//                            if (i.getGrad() == 0) {
//                                forScreen = forScreen + "1";
//                            }
//                        }
//                    }
//                } else {
//                    forScreen = forScreen + "+";
//                    if (i.getExp() != 1 || i.getGrad() == 0) {
//                        forScreen = forScreen + i.getExp();
//                    }
//                }
//            }
//            if (i.getGrad() != 0) {
//                forScreen = forScreen + "x";
//                if(i.getGrad() != 1){
//                    forScreen = forScreen + "^" + i.getGrad();
//                }
//            }
//        }
//        return forScreen;
//    }

    private List<Monom> mapToList(HashMap<Integer, Double> hashMap) {
        List<Monom> newList = new ArrayList<>();
        for (Map.Entry<Integer, Double> i : hashMap.entrySet()) {
            newList.add(new Monom(i.getKey(), i.getValue()));
        }
        return newList;
    }

    private Monom[] listToArray(List<Monom> monomList1) {
        Monom[] array = new Monom[monomList1.size()];
        for (int i = 0; i < monomList1.size(); i++) {
            array[i] = monomList1.get(i);
        }
        return array;
    }

    private Monom[] sortMonoms(HashMap<Integer, Double> map) {
        List<Monom> monomList1 = mapToList(map);
        Monom[] array = listToArray(monomList1);
        Arrays.sort(array, new SortMonoms());
        return array;
    }

    public HashMap<Integer, Double> getMyPolynom() {
        return myPolynom;
    }

    private void monomsToPolynom() {
        for (Monom i : monomList) {
            if (!myPolynom.containsKey(i.getGrad())) {
                myPolynom.put(i.getGrad(), i.getExp());
            } else {
                double buffer1 = Math.round(i.getExp() * 100) / 100.0;
                double buffer2 = Math.round(myPolynom.get(i.getGrad()) * 100) / 100.0;

                if ((buffer1 + buffer2) != 0.0) {
                    myPolynom.put(i.getGrad(), buffer1 + buffer2);
                } else {
                    myPolynom.remove(i.getGrad());
                }
            }
        }
    }

    public HashMap<Integer, Double> add(HashMap<Integer, Double> number) {
        HashMap<Integer, Double> newPolynom = myPolynom;
        for (Map.Entry<Integer, Double> i : number.entrySet()) {
            if (!newPolynom.containsKey(i.getKey())) {
                newPolynom.put(i.getKey(), i.getValue());
            } else {
                double buffer1 = Math.round(i.getValue() * 100) / 100.0;
                double buffer2 = Math.round(newPolynom.get(i.getKey()) * 100) / 100.0;
                if (buffer1 + buffer2 != 0) {
                    newPolynom.put(i.getKey(), buffer1 + buffer2);
                } else {
                    newPolynom.remove(i.getKey());
                }
            }
        }
        return newPolynom;
    }

    public HashMap<Integer, Double> sub(HashMap<Integer, Double> number) {
        HashMap<Integer, Double> newPolynom = myPolynom;
        for (Map.Entry<Integer, Double> i : number.entrySet()) {
            if (!newPolynom.containsKey(i.getKey())) {
                newPolynom.put(i.getKey(), -i.getValue());
            } else {
                double buffer1 = Math.round(i.getValue() * 100) / 100.0;
                double buffer2 = Math.round(newPolynom.get(i.getKey()) * 100) / 100.0;
                if (buffer2 - buffer1 != 0) {
                    newPolynom.put(i.getKey(), buffer2 - buffer1);
                } else {
                    newPolynom.remove(i.getKey());
                }
            }
        }
        System.out.println(newPolynom);
        return newPolynom;
    }

    public HashMap<Integer, Double> integration() {
        HashMap<Integer, Double> newPolynom = new HashMap<>();
        for (Map.Entry<Integer, Double> i : myPolynom.entrySet()) {
            int oldPower = i.getKey();
            double oldCoef = i.getValue();
            double buffer1 = Math.round(((double) oldCoef / (oldPower + 1)) * 100);
            buffer1 = buffer1 / 100;
            newPolynom.put(oldPower + 1, buffer1);
        }
        return newPolynom;
    }

    public HashMap<Integer, Double> derivative() {
        HashMap<Integer, Double> newPolynom = new HashMap<>();
        for (Map.Entry<Integer, Double> i : myPolynom.entrySet()) {
            int oldPower = i.getKey();
            double oldCoef = i.getValue();
            double buffer1 = Math.round((oldCoef * oldPower) * 100);
            buffer1 = buffer1 / 100;
            if (oldPower != 0) {
                newPolynom.put(oldPower - 1, buffer1);
            }
        }
        return newPolynom;
    }

    public HashMap<Integer, Double> multiplicate(HashMap<Integer, Double> number) {
        HashMap<Integer, Double> newPolynom = new HashMap<>();
        newPolynom.put(0, 0.00);
        if ((number.equals(newPolynom) || myPolynom.equals(newPolynom))) {
            return newPolynom;
        }
        newPolynom.clear();
        for (Map.Entry<Integer, Double> i : myPolynom.entrySet()) {
            System.out.println("i : " + i);
            for (Map.Entry<Integer, Double> j : number.entrySet()) {
                System.out.println("j : " + j);
                if (!newPolynom.containsKey(i.getKey() + j.getKey())) {
                    newPolynom.put(i.getKey() + j.getKey(), i.getValue() * j.getValue());
                } else {
                    double buffer1 = Math.round((i.getValue() * j.getValue()) * 100) / 100.0;
                    double buffer2 = Math.round(newPolynom.get(i.getKey() + j.getKey()) * 100) / 100.0;
                    if (buffer1 + buffer2 != 0) {
                        newPolynom.put(i.getKey() + j.getKey(), newPolynom.get(i.getKey() + j.getKey()) + (i.getValue() * j.getValue()));
                    } else {
                        newPolynom.remove(i.getKey() + j.getKey());
                    }
                }
            }
        }
        return newPolynom;
    }

    private int getMax(HashMap<Integer, Double> number) {
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Double> i : number.entrySet()) {
            if (i.getKey() > max) {
                max = i.getKey();
            }
        }
        return max;
    }

    public HashMap<Integer, Double> divide(HashMap<Integer, Double> number) {
        HashMap<Integer, Double> newPolynom = new HashMap<>();
        newPolynom.put(0, 0.00);
        if (number.equals(newPolynom)) {
            return null;
        }
        newPolynom.clear();
        HashMap<Integer, Double> recovery1 = myPolynom;
        HashMap<Integer, Double> recovery2 = myPolynom;
        HashMap<Integer, Double> res = new HashMap<>();
        while (getMax(recovery2) >= getMax(number)) {
            res.put(getMax(recovery2) - getMax(number), recovery2.get(getMax(recovery2)) / number.get(getMax(number)));
            HashMap<Integer, Double> inmultitor = new HashMap<>();
            inmultitor.put(getMax(recovery2) - getMax(number), recovery2.get(getMax(recovery2)) / number.get(getMax(number)));
            myPolynom = number;
            HashMap<Integer, Double> scazator = multiplicate(inmultitor);
            myPolynom = recovery2;
            recovery2 = sub(scazator);
        }
        myPolynom = recovery1;
        return res;
    }

    public HashMap<Integer, Double> modulo(HashMap<Integer, Double> number) {
        HashMap<Integer, Double> newPolynom = new HashMap<>();
        newPolynom.put(0, 0.00);
        if (number.equals(newPolynom)) {
            return myPolynom;
        }
        newPolynom.clear();
        HashMap<Integer, Double> recovery1 = myPolynom;
        HashMap<Integer, Double> recovery2 = myPolynom;
        HashMap<Integer, Double> res = new HashMap<>();
        while (getMax(recovery2) >= getMax(number)) {
            res.put(getMax(recovery2) - getMax(number), recovery2.get(getMax(recovery2)) / number.get(getMax(number)));
            HashMap<Integer, Double> inmultitor = new HashMap<>();
            inmultitor.put(getMax(recovery2) - getMax(number), recovery2.get(getMax(recovery2)) / number.get(getMax(number)));
            myPolynom = number;
            HashMap<Integer, Double> scazator = multiplicate(inmultitor);
            myPolynom = recovery2;
            recovery2 = sub(scazator);
        }
        myPolynom = recovery1;
        return recovery2;
    }
}

class SortMonoms implements Comparator<Monom>{
    public int compare(Monom x, Monom y){
        return x.getGrad() - y.getGrad();
    }
}
