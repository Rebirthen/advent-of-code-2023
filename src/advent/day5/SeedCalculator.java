package advent.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeedCalculator {
    static boolean found = false;
    public static void main(String[] args) {
        try {
            List<String> lines = readLinesFromFile("src/advent/day5/input.txt");
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

            for (long i = 0; i < count; i++) {
                min = Math.min(min,caculateLocation(Long.parseLong(s[first]) + i, lines));
                System.out.println(LocalDateTime.now() +" in progress ->"+ Long.parseLong(s[first]+i) + " ----> "+ second);
            }

            first = second + 1;
            second = first + 1;
        }

        //other



        return min;
    }

    private static long caculateLocation(long start, List<String> lines) {
        long end = start;
        found = false;
        for (int i = 2; i < lines.size(); i++) {
            if (lines.get(i).isEmpty()) {
                start = end;
                found = false;
                end = 0;
            } else if (!found && Character.isDigit(lines.get(i).charAt(0))) {
                end = calculate(start, lines.get(i));
            }
        }

        return end;
    }

    private static long calculate(long start, String line) {
        long end =0;
        String[] range = line.split(" ");
        long to = Long.parseLong(range[0]); // 50
        long from = Long.parseLong(range[1]); //98
        long count = Long.parseLong(range[2]); //2

        if(from<=start && start<=from+count){
            found = true;
            end = to+(start-from);
        }

        if(end == 0){
            return start;
        }

        return end;
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
