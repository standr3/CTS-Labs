import java.util.*;
import java.util.function.Function;

public class Main {
    public static class Ranking {
        private int[] scores = null;
        public Ranking(int[] scores) {
            this.scores = scores;
        }
        public int[] getScores() { return scores; }
        public void setScores(int[] scores) {  this.scores = scores; }
        public int getPos(int val) {
            if (scores == null)
                throw new IllegalArgumentException("scores null");
            if (val < 0)
                throw new IllegalArgumentException("arg negativ");
            Set<Integer> values = new HashSet<>();
            for (int sCurr : scores) {
                if (sCurr < 0)
                    throw new RuntimeException("scor negativ in vector");
                if (sCurr >= val)
                    values.add(sCurr);
            }
            if (!values.contains(val))
                throw new RuntimeException("valoarea introdusa nu exista in vector");
            return values.size();
        }

    }

    public static class Tester<T, R> {
        private Function<T, R> func;
        public Tester(Function<T, R> func) {
            this.func = func;
        }
        public Function<T, R> getFunc() {return func;}
        public void setFunc(Function<T, R> func) {this.func = func;}


        public boolean test(T arg, R expected) {
            R result = func.apply(arg);
            return result==expected;
        }
    }
    public static void main(String[] args) {
        Ranking ranking = new Ranking(new int[]{5,10,10,15,20,20,5,25});

      /*  System.out.println("pozitia: " + ranking.getPos(25));
        System.out.println("pozitia: " + ranking.getPos(20));
        System.out.println("pozitia: " + ranking.getPos(15));
        System.out.println("pozitia: " + ranking.getPos(10));
        System.out.println("pozitia: " + ranking.getPos(5));*/


        Tester<Integer, Integer> tester = new Tester<>(ranking::getPos);

        List<Map.Entry<Integer,Integer>> pairList= new ArrayList<>();

        pairList.add(new AbstractMap.SimpleEntry<Integer,Integer>(25,1));
        pairList.add(new AbstractMap.SimpleEntry<Integer,Integer>(20,2));
        pairList.add(new AbstractMap.SimpleEntry<Integer,Integer>(15,3));
        pairList.add(new AbstractMap.SimpleEntry<Integer,Integer>(10,4));
        pairList.add(new AbstractMap.SimpleEntry<Integer,Integer>(5,4));

        // wrong arg//
        pairList.add(new AbstractMap.SimpleEntry<Integer,Integer>(2,-1));

        for(Map.Entry<Integer,Integer> pair:pairList){
            Integer first = pair.getKey();
            Integer second = pair.getValue();

            System.out.println("["
                    + tester.test(first, second)
                    +"]"
                    + first
                    + " - > "
                    + second);
        }
    }
}
