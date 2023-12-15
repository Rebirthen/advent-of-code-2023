package advent.day9;

import advent.Reader;

import java.util.*;

public class Day9Calculator extends Reader {
    static Node node;
    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/advent/day9/input.txt");

        int res = calculate(lines);
        System.out.println(res);
    }

    private static int calculate(List<String> lines) {
        int res = 0;
        for (int i = 0; i < lines.size(); i++) {
            node = new Node(0);
            String[] nums = lines.get(i).split(" ");
            res+=bfs(nums);
        }

        return res;
    }

    private static int bfs(String[] strs) {
        Stack<int[]> stack = new Stack<>();
        stack.add(toIntArr(strs));

        boolean allZero = false;
        while(!allZero){
            int[] curr= stack.peek();
            int[] nums = new int[curr.length-1];
             allZero = true;
            for (int i = 0; i < curr.length-1; i++) {
                int val1 = curr[i];
                int val2 = curr[i+1];
                nums[i] = val2 - val1;
                allZero = allZero && nums[i] == 0;
            }
            stack.add(nums);

        }

        int res = 0;
        while(!stack.isEmpty()){
            int[] nums = stack.pop();
            res=nums[0]-res;
        }

        return res;



    }

    public static int[] toIntArr(String[] s) {
        int[] result = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            result[i] = Integer.parseInt(s[i]);
        }
        return result;
    }
}


class Node{
    Node left;
    Node right;
    int val;
    Node(int val){
        this.val = val;
    }
}
