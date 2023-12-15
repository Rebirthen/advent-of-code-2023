package advent.day7;

import advent.Reader;

import java.util.*;

public class CardCalculator  extends Reader {

    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/advent/day6/input.txt");
        int lowest = getBid(lines);
        System.out.println("result: " + lowest);
    }

    private static int getBid(List<String> lines) {
        int res = 0;
        PriorityQueue<String[]> queue= new PriorityQueue<>(lines.size(),new CardComparator());
        for (int i = 0; i < lines.size(); i++) {
            String[] game = lines.get(i).split("\\s+");
            queue.add(game);
        }

        int i=1;
        while(!queue.isEmpty()){
            res+=i*Integer.parseInt(queue.poll()[1]);
            i++;
        }

        return res;

    }
}

class CardComparator implements Comparator<String[]> {
    static char[] order = new char[]{'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
    Map<Character, Integer> map = new HashMap<>();
    CardComparator(){
        for (int i = 1; i <= order.length; i++) {
            map.put( order[i-1], i);
        }
   }
    @Override
    public int compare(String[] o1, String[] o2) {

        return calculate(o1[0], o2[0]);

    }

    private int calculate(String s1, String s2) {
        int first = firstOrder(s1);
        int second = firstOrder(s2);
        int duplicatesComparison = Integer.compare(first, second);
        if (duplicatesComparison != 0) {
            return duplicatesComparison;
        }
         return secondOrder(s1, s2);
    }

    private int firstOrder(String s) {
        int max =0;
        Map<Character, Integer> charCountMap = new HashMap<>();

        // Count the occurrences of each character in the string
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            max = Math.max(charCountMap.get(c), max);
        }

        // Sum the counts of characters with duplicates
        return max;
    }

    private int secondOrder(String s1, String s2) {
        int i =0;
        while(i<5){
            int index1 = map.get(s1.charAt(i));
            int index2 = map.get(s2.charAt(i));
            if (index1 != index2) {
                return Integer.compare(index1, index2);
            }
            i++;
        }
        return 0;
    }


}

