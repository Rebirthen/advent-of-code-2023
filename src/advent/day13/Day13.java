package advent.day13;

import advent.Reader;

import java.util.List;

public class Day13 extends Reader {
    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/advent/day13/simple.txt");
        int arrs  = populateOnsens(lines);

        System.out.println("result: "+  arrs);
    }

    private static int populateOnsens(List<String> lines) {
        int sum=0;
        int startRow=0;
        for (int i = 0; i < lines.size(); i++) {
            if(lines.get(i).trim().length() == 0) {
                sum += calculateDay13(lines, startRow, i);
                startRow = i+1;
            }

        }

        sum+=calculateDay13(lines, startRow, lines.size());

        return sum;
    }

    private static int calculateDay13(List<String> lines, int start, int end) {
        int sum = getRows(start,end,start+(end-start)/2, start+(end-start)/2+1, lines);
        if(sum==0) sum = getRows(start,end,start+(end-start)/2-1, start+(end-start)/2, lines);

        int len = lines.get(start).length();
        if(sum==0) sum = getColumns(start, end, len/2, len/2+1, lines);
        if(sum == 0) sum = getColumns(start, end, len/2-1, len/2, lines);
        return sum;
    }

    static  int getRows(int start, int end, int startRow, int endRow, List<String> lines){
        boolean rowReflect = true;
        int temp = startRow-start+1;
        while(start<=startRow && endRow<end) {
            rowReflect = true;

            for (int i = 0; i < lines.get(start).length(); i++) {
                rowReflect = rowReflect && lines.get(startRow).charAt(i) == lines.get(endRow).charAt(i);
            }

            if(!rowReflect) return 0;
            startRow--;
            endRow++;
        }
        return temp*100;
    }

    static int getColumns(int start, int end, int startCol, int endCol, List<String> lines){
        int len = lines.get(start).length();
        boolean colReflect =true;
        int temp = startCol+1;
        while(0<=startCol && endCol< len){
            colReflect =true;
            for (int i = start; i < end; i++) {
                colReflect = colReflect && lines.get(i).charAt(startCol) == lines.get(i).charAt(endCol);
            }

            if(!colReflect) return 0;
            startCol--;
            endCol++;
        }

        return temp;
    }


}
