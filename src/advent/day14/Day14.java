package advent.day14;

import advent.Reader;

import java.util.List;

public class Day14 extends Reader {
    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/advent/day14/input.txt");
        int res  = calculate14(lines);

        System.out.println("result: "+  res);
    }

    private static int calculate14(List<String> lines) {
        int res= 0;
        String[] strs = lines.get(0).split(",");
        for (String str: strs) {
            int sum= 0;
            for(char ch :str.toCharArray()){
                sum+=ch;
                sum = sum*17;
                sum = sum%256;
            }
            res+=sum;

        }

        return res;
    }
}
