package advent.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Seed2Calculator {
    static boolean found = false;
    static Stack<long[]> stack =  new Stack<>();
    public static void main(String[] args) {
        try {
            List<String> lines = readLinesFromFile("src/advent/day5/test.txt");
            long lowest = getLowestLocation(lines);
            System.out.println("result: " + lowest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long getLowestLocation(List<String> lines) {

        String[] s = lines.get(0).split(":")[1].trim().split(" ");
        int first = 0, second = 1;
        //seeds
        long min = Long.MAX_VALUE;
        while (second < s.length) {
            long count = Long.parseLong(s[second]);
            long from = Long.parseLong(s[first]);
            long to = Long.parseLong(s[first])+count-1;

            min = Math.min(min,caculateLocation(from, to,  lines));
            System.out.println(LocalDateTime.now() +" in progress ->"+ from + " ----> "+ second);


            first = second + 1;
            second = first + 1;
        }

        //other



        return min;
    }

    private static long caculateLocation(long start, long end, List<String> lines) { // from 79   to 92    // 50   97   //
        stack.add(new long[]{start, end, 2});
        long min = Long.MAX_VALUE;
        while(!stack.isEmpty()){
            found = false;
            long[] res = stack.pop();
            start = res[0];
            end = res[1];
            for (long i = res[2]; i < lines.size(); i++) {
                if (lines.get((int)i).isEmpty()) {
                    found = false;
                    start = res[0];
                    end = res[1];
                } else if (!found && Character.isDigit(lines.get((int)i).charAt(0))) {
                    res = calculate(start, end, lines.get((int)i), i);
                }
            }

            min = Math.min(min, res[0]);
        }


        return min;
    }

    private static long[] calculate(long start, long end, String line, long i) {  // 74   87
        String[] range = line.split(" ");
        long to = Long.parseLong(range[0]); // 45
        long from = Long.parseLong(range[1]); //77
        long count = Long.parseLong(range[2]); //23
           //
        if(from<=start && end<=from+count-1){
            found = true;
            start = start+(to-from);
            end = end+(to-from);
        }else if(from>start&& from<end && end<=from+count-1){
            found = true;
            stack.add(new long[]{start,from-1,i});
            start = from+(to-from);
            end = end+(to-from);

        }

        return new long[]{start,end};
    }

    private static List<String> readLinesFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
}
