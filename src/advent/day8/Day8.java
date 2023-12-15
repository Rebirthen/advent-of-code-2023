package advent.day8;

import advent.Reader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day8 extends Reader {
    static Map<String, String[]> map = new HashMap<>();
    static List<String> start = new ArrayList<>();
    static Map<String, Map<String, Integer>> endMap= new HashMap<>();
    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/advent/day8/test3.txt");

        int steps = calculateSteps(lines);
        System.out.println(steps);
    }


    private static int calculateSteps(List<String> lines) {
        int res = 0;
        String[] instructions = lines.get(0).split("");
        populate(lines);
        res = calculate(instructions);

        return res;
    }

    private static int calculate(String[] instructions) {
        int sum =0;
        for (String str: start){
            runinstructions(instructions, str);
        }




        return sum;
    }

    private static int runinstructions(String[] instructions, String str) {
        int count = 0;
        int i =0;
        String curr = str;
        while(i != instructions.length){
            if(i >= instructions.length) i = 0;
            curr = map.get(curr)[getIndex(instructions, i)];
            i++;
            count++;
            if(curr.charAt(2) == 'Z' && i == instructions.length) break;
        }
        endMap.put(str,Map.of(curr, count));
        if(!endMap.containsKey(curr)) runinstructions(instructions, curr);

        return count;
    }

    private static int getIndex(String[] instructions, int i) {
        if(instructions[i].equals("R")) return 1;
        return 0;
    }

    private static void populate(List<String> lines) {
        for (int i = 2; i < lines.size(); i++) {
             String[] steps = lines.get(i).split(" = ");

             if(steps[0].charAt(2) == 'A'){
                 start.add(steps[0]);
             }
             map.put(steps[0], steps[1].replaceAll("[()]", "").split(",\\s+"));
        }
    }
}
