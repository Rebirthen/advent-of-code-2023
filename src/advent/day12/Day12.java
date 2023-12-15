package advent.day12;

import advent.Reader;

import java.util.ArrayList;
import java.util.List;

public class Day12 extends Reader {
    static  List<char[]> grid = new ArrayList<>();
    static   List<int[]> arr = new ArrayList<>();
    static int[] curr;
    static char[] line;
    static int max =0;
    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/advent/day12/test.txt");
        populateOnsens(lines);
        int arrs  = calculateDay12();
        System.out.println("result: "+  arrs);
    }

    private static int calculateDay12() {
        int sum =0;
        for (int i = 0; i < grid.size(); i++) {
            line = grid.get(i);
            curr = arr.get(i);
            backtrack(0, 0, "", 0);
            sum+=max;
            max=0;
        }
        return sum;
    }

    private static void backtrack( int i, int j, String str, int count) {
          if(str.length() == line.length &&j<curr.length&& curr[j] ==count) {
             max++;
             return;
         } else if(j>=curr.length || i>=line.length) return;
        else if(curr[j]-count<0) return;
        else if(line[i] == '?'){
            backtrack(i+1, j, str+"#", count+1);
            j++;
            count=0;
            backtrack(i+1, j, str+".", count);
        }else{
            backtrack(i + 1, j, str+line[i], count+line[i]=='#'?1:0);
        }
    }

    private static void populateOnsens(List<String> lines) {

        for (int i = 0; i < lines.size(); i++) {
            String[] strs = lines.get(i).split(" ");
            grid.add(strs[0].toCharArray());
            String[] steps = strs[1].split(",");

            int[] m = new int[steps.length];
            for (int j = 0; j < steps.length; j++) {
                m[j] = Integer.parseInt(steps[j]);
            }
            arr.add(m);
        }
    }
}
