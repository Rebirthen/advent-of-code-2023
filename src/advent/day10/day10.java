package advent.day10;

import advent.Reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class day10 extends Reader {
    static int UP = 0;
    static int DOWN =1;
    static int LEFT = 2;
    static int RIGHT =3;
    static int max = Integer.MIN_VALUE;
    static int x;
    static int y;
    static double dist;
    static Map<Character, boolean[]> map =
            Map.of('|', new boolean[]{true, true, false, false},
            '-', new boolean[]{false, false, true, true},
            'L', new boolean[]{true, false, false, true},
            'J', new boolean[]{true, false, true, false},
            'F', new boolean[]{false, true, false, true},
            '.', new boolean[]{false, false, false, false},
                    '7', new boolean[]{false, true, true, false},
                    'S', new boolean[]{true, true, true, true}
    );
    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/advent/day10/input.txt");

        int steps = calculateDay10(lines);
        System.out.println(steps);
    }

    private static int calculateDay10(List<String> lines) {
        int m = lines.size();
        int n = lines.get(0).length();
        char[][] matrix = new char[m][n];
        int row = 0;
        int col = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = lines.get(i).charAt(j);
                if(matrix[i][j] == 'S') {
                    row = i;
                    col = j;
                }
            }
        }
        x = row;
        y = col;
        dist = 0;
        dfs(matrix, row, col, 0);
        return (max+1)/2;
    }

    private static void dfs(char[][] matrix, int row, int col, int count) {
        max = Math.max(count, max);
        char temp = matrix[row][col];
        matrix[row][col] = '.';
        System.out.println(row+"-"+col+"----max---->"+max);
        if(map.get(temp)[DOWN] && row+1<matrix.length && matrix[row+1][col] !='.') dfs(matrix, row+1, col, count+1);
        if(map.get(temp)[UP]  && row-1>=0 && matrix[row-1][col] !='.') dfs(matrix, row-1, col, count+1);
        if(map.get(temp)[LEFT] && col-1>=0 && matrix[row][col-1] !='.') dfs(matrix, row, col-1, count+1);
        if(map.get(temp)[RIGHT] && col+1<matrix[0].length && matrix[row][col+1] !='.') dfs(matrix, row, col+1, count+1);


    }

    private static double calculateDistance(
            double x1,
            double y1,
            double x2,
            double y2) {

        double ac = Math.abs(y2 - y1);
        double cb = Math.abs(x2 - x1);

        return Math.hypot(ac, cb);
    }

    private static boolean isFar(
            double x1,
            double y1,
            double x2,
            double y2) {

        return x1<=x2 && y1<=y2;
    }

}
