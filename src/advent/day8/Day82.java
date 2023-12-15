package advent.day8;

import advent.Reader;

import java.util.*;


public class Day82 extends Reader {

    static  List<String> start = new ArrayList<>();

    static  Map<String, Node> nodeMap = new HashMap<>();

    static Map<String, String[]> stringMap = new HashMap<>();

    static Node[] root;
    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/advent/day8/test3.txt");

        int steps = calculateSteps2(lines);
        System.out.println(steps);
    }

    private static int calculateSteps2(List<String> lines) {
        int res = 0;
        String[] instructions = lines.get(0).split("");
        populate(lines);

        return res;
    }

    private static int getIndex(String[] instructions, int i) {
        if(instructions[i].equals("R")) return 1;
        return 0;
    }

    private static void populate(List<String> lines) {

        for (int i = 2; i < lines.size(); i++) {
            String[] steps = lines.get(i).split(" = ");

            if(steps[0].charAt(2) == 'A'){
                start.add(steps[0]);
            }

            stringMap.put(steps[0], steps[1].replaceAll("[()]", "").split(",\\s+"));

        }

        root = new Node[start.size()];


        for (int i = 0; i < start.size(); i++) {

            Node curr = nodeMap.getOrDefault(start.get(i),new Node(start.get(i)));
            root[i] = curr;
            nodeMap.put(start.get(i), curr);

            populateChildren(curr, stringMap.get(start.get(i)));
        }


    }

    private static void populateChildren(Node curr, String[] strings) {
        int i =0;

        for (String str: strings){
            if(curr.value.equals(str)) continue;
            if(nodeMap.containsKey(str)) {
                curr.paths[i++] = nodeMap.get(str);
                return;
            }


            Node node = new Node(str);
            nodeMap.put(str, node);

            curr.paths[i++] = node;
            populateChildren(node, stringMap.get(str));

            if(str.charAt(2)=='Z'){
                node.isEnd = true;
            }

        }

    }


}

class Node {
    Node[] paths;
    String value;
    boolean isEnd = false;

    Node(String value){
        this.value = value;
        paths = new Node[2];
    }
}