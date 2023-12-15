package advent.day4;

import advent.Reader;

import java.util.Arrays;
import java.util.List;

public class CardCalculator extends Reader {
    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/advent/day4/input.txt");

        // int sum = getSumOfParts(lines);
        int sum = getScratchcards(lines);

        System.out.println("sum"+ sum);
    }

    private static int getWines(List<String> lines) {
        int res = 0;
        boolean[] pool = null;
        for(String line: lines){
            int sum = 0;
            pool = new boolean[100];
            String[] games = line.split(":")[1].trim().split("\\|");
            for(String item:games[0].split(" ")){
                if(item!="") pool[Integer.parseInt(item)] = true;
            }

            for(String item: games[1].trim().split(" ")){
                if(item!="" && pool[Integer.parseInt(item)]){
                    sum++;
                }
            }
            res+=sum;
        }
        return res;
    }

    private static int getScratchcards(List<String> lines) {
        int res = 0;
        boolean[] pool = null;
        int[] copies = new int[lines.size()];
        for(int i =0;i< lines.size(); i++){
            String line = lines.get(i);
            int sum = 0;
            pool = new boolean[100];
            String[] games = line.split(":")[1].trim().split("\\|");
            for(String item:games[0].split(" ")){
                if(item!="") pool[Integer.parseInt(item)] = true;
            }

            for(String item: games[1].trim().split(" ")){
                if(item!="" && pool[Integer.parseInt(item)]){
                    sum++;
                }
            }
            int j =i+1;
            int cur = sum;
            while(sum>0){
                copies[j]+=copies[i]+1;
                j++;
                sum--;
            }
            res+=copies[i]+1;
        }
        return res;
    }
}
