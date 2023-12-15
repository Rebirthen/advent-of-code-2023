package advent.day7;

import advent.Reader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Card2Calculator extends Reader {

    static char[] order = new char[]{'A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2'};
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/advent/day6/input.txt");
        for (int i = 1; i <= order.length; i++) {
            map.put( order[i-1], order.length-i);
        }
        int lowest = getBid(lines);
        System.out.println("result: " + lowest);
    }

    private static int getBid(List<String> lines) {
        int res =0;
        String[][] arr = new String[lines.size()][2];
        for (int i = 0; i < lines.size(); i++) {
            String[] game = lines.get(i).split("\\s+");
            arr[i] = game;
        }
        quickSort(arr, 0 , arr.length-1);
        for (int i = 1; i <=arr.length; i++) {
            res+=i*Integer.parseInt(arr[i-1][1]);
        }
        return res;
    }

    static void swap(String[][] arr, int i, int j)
    {
        String[] temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // This function takes last element as pivot,
    // places the pivot element at its correct position
    // in sorted array, and places all smaller to left
    // of pivot and all greater elements to right of pivot
    static int partition(String[][] arr, int low, int high)
    {
        // Choosing the pivot
        String pivot = arr[high][0];

        // Index of smaller element and indicates
        // the right position of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller than the pivot
            if (compare(arr[j][0], pivot)) {

                // Increment index of smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    private static boolean compare(String item, String pivot) {
        int first = firstOrder(item);
        int second = firstOrder(pivot);
        if(first == second){
            return secondOrder(item, pivot);
        }
        return first < second;
    }

    // The main function that implements QuickSort
    // arr[] --> Array to be sorted,
    // low --> Starting index,
    // high --> Ending index
    static void quickSort(String[][] arr, int low, int high)
    {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }


    private static int firstOrder(String s) {
        int max =0;
        Map<Character, Integer> charCountMap = new HashMap<>();
        // Count the occurrences of each character in the string
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
            max = Math.max(charCountMap.get(c), max);
        }


        if(max > 3){
            return max+1;
        }else{
            if(max==3) {
                if (charCountMap.size() == 2) {
                    return 4;
                }
                return 3;
            }
            if(max== 2){
                if (charCountMap.size() == 3) {
                    return 2;
                }
            }
        }

        // Sum the counts of characters with duplicates
        return max-1;
    }

    private static boolean secondOrder(String s1, String s2) {
        int i =0;
        while(i<5){
            int index1 = map.getOrDefault(s1.charAt(i), 0);//5
            int index2 = map.getOrDefault(s2.charAt(i), 0);//3
            if (index1 != index2) {
                return index1 < index2;
            }
            i++;
        }
        return true;
    }


}
