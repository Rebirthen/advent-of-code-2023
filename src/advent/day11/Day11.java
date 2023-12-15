package advent.day11;

import advent.Reader;
import com.sun.jdi.event.StepEvent;

import java.util.*;

public class Day11 extends Reader {
    static int m;
    static int n;
    static List<int[]> queue = new ArrayList<>();
    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/advent/day11/test1.txt");

        int[][] universe = getUniverse(lines);
        long distance  = calculateDay11(universe);
        System.out.println("result: "+  distance);
    }


    private static long calculateDay11(int[][] universe) {
        long sum = 0;
        for (int i = 0; i < queue.size(); i++) {
            for (int j = 0; j < queue.size(); j++) {
                if (i == j) continue;

                sum += minDistance(universe, queue.get(i)[0], queue.get(i)[1], queue.get(j)[0], queue.get(j)[1]);
                System.out.println(sum);
            }
        }

        return sum/2;

    }



    private static int[][] getUniverse(List<String> lines) {
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();
        m = lines.size();
        n = lines.get(0).length();
        int[][] grid = new int[m][n];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                if(lines.get(i).charAt(j) == '#'){
                    grid[i][j] =-1;
                    colSet.add(j);
                    rowSet.add(i);
                    queue.add(new int[]{i, j, 0});
                }
            }
        }
        int exp = 2;

        for (int i = 0; i <lines.size(); i++) {
            for (int j = 0; j < lines.get(0).length(); j++) {
                if(!rowSet.contains(i) || !colSet.contains(j)){
                    grid[i][j] = exp;
                }
            }

        }

        return grid;
    }

    static class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static int minDistance(int[][] matrix, int i, int j, int i1, int j1) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean[][] visited = new boolean[m][n];
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(i, j, 0));
        visited[i][j] = true;

        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            if (current.x == i1 && current.y == j1) {
                return current.dist;
            }

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (isValid(newX, newY, m, n) && !visited[newX][newY]) {
                    queue.add(new Point(newX, newY, current.dist +1+matrix[newX][newY]));
                    visited[newX][newY] = true;
                }
            }
        }

        // If no path is found
        return -1;
    }

    private static boolean isValid(int x, int y, int m, int n) {
        return x >= 0 && x < m && y >= 0 && y < n;
    }
}
