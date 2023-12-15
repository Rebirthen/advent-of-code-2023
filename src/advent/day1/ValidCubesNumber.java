package advent.day1;

import advent.Reader;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


public class ValidCubesNumber extends Reader {
    static int red = 12;
    static int blue = 14;
    static int green = 13;

    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/day1/input.txt");

        int sum = getMultipliedSumOfMinimumSet(lines);

        System.out.println("sum"+ sum);
    }

    private static int getMultipliedSumOfMinimumSet(List<String> lines){
        int sum = 0;
        int i = 1;
        for (String line : lines) {
            String[] parts = line.trim().split(":"); // ["3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",...
            line = parts[1].trim();
            String[] games = line.trim().split(";"); //["3 blue, 4 red"...
            boolean valid = true;
            int r = 0, g = 0, b = 0;
            for (String game : games) {

                String[] rounds = game.split(",");//[3 blue,...

                for (String round : rounds) {
                    String[] cubes = round.trim().split(" ");//[3, blue
                    int number = Integer.valueOf(cubes[0]);
                    switch (cubes[1]) {
                        case "red":
                            r = Math.max(r, number);
                            break;
                        case "blue":
                            b = Math.max(b, number);
                            break;
                        case "green":
                            g = Math.max(g, number);
                            break;
                    }

                }

            }
            sum+=r*g*b;
            i++;
        }
        return sum;
    }

    private static int getSum(List<String> lines) {
        int sum = 0;
        int i = 1;
        for (String line : lines) {
            String[] parts = line.trim().split(":"); // ["3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",...
            line = parts[1].trim();
            String[] games = line.trim().split(";"); //["3 blue, 4 red"...
            boolean valid = true;
            for (String game : games) {
                int r = 0, g = 0, b = 0;
                String[] rounds = game.split(",");//[3 blue,...

                for (String round : rounds) {
                    String[] cubes = round.trim().split(" ");//[3, blue
                    int number = Integer.valueOf(cubes[0]);
                    switch (cubes[1]) {
                        case "red":
                            valid = valid && red >= number;
                            break;
                        case "blue":
                            valid = valid && blue >= number;
                            break;
                        case "green":
                            valid = valid && green >= number;
                            break;
                    }
                    if(!valid) break;

                }

                if(!valid) break;


            }
            if(valid) sum += i;

            i++;
        }
        return sum;
    }



}
