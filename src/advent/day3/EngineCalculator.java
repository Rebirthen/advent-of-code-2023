package advent.day3;


import advent.Reader;

import java.util.List;


public class EngineCalculator extends Reader {
    static char[][] matrix;
    static int freq = 0;
    public static void main(String[] args) {
        List<String> lines = getListFromFile("src/advent/day3/input.txt");

       // int sum = getSumOfParts(lines);
        int sum = getProductOfParts(lines);

        System.out.println("sum"+ sum);
    }

    private static int getProductOfParts(List<String> lines) {
        int sum = 0;
        matrix = new char[lines.get(0).length()][lines.size()];
        populateMatrix(lines);


        return calculateProduct();
    }

    private static int calculateProduct() {
        int sum =0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if( matrix[i][j]=='*'){
                    int product = getProduct(i, j, 0, true);
                    if(freq == 2) sum+=product;
                }
            }
        }
        return sum;
    }

    private static int getProduct(int i, int j, int sum, boolean first) {
        char ch = matrix[i][j];

        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length || ch == '.' || (!first && !Character.isDigit(ch)) ) return 1;

        if(first){
            freq =0;
            return getProduct( i, j + 1, 0, false)*
                    getProduct( i + 1, j, 0, false)*
                    getProduct(i - 1, j, 0, false)*
                    getProduct( i, j - 1, 0, false)*

                    getProduct(i + 1, j + 1, 0, false)*
                    getProduct(i - 1, j - 1, 0, false)*
                    getProduct( i + 1, j - 1, 0, false)*
                    getProduct( i - 1, j + 1, 0, false);

        }else{
            freq++;
            return getFullNumber(i, j);
        }





    }

    private static int getSumOfParts(List<String> lines) {
        int sum = 0;
        matrix = new char[lines.get(0).length()][lines.size()];
        populateMatrix(lines);


        return calculate();
    }

    private static int calculate() {
        int sum =0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(!Character.isDigit(matrix[i][j]) && matrix[i][j]!='.'){
                    sum+=getSum(i, j, 0, true);
                }
            }
        }
        return sum;
    }


    private static int getSum( int i, int j, int sum, boolean first) {
        char ch = matrix[i][j];
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[i].length || ch == '.' || (!first && !Character.isDigit(ch)) ) return 0;

            if(first){
                freq = 0;
                sum=getSum( i, j + 1, 0, false)+
                getSum( i + 1, j, 0, false)+
                getSum(i - 1, j, 0, false)+
                getSum( i, j - 1, 0, false)+

                getSum(i + 1, j + 1, 0, false)+
                getSum(i - 1, j - 1, 0, false)+
                getSum( i + 1, j - 1, 0, false)+
                getSum( i - 1, j + 1, 0, false);

            }else{
                freq++;
                return getFullNumber(i, j);
            }





        return sum;

    }

    private static int getFullNumber(int i, int j) {
        int n=0;
        int right = j;
        int left =j;
        while(left>0 && right<matrix[i].length-1){
            if(!Character.isDigit(matrix[i][left-1]) && !Character.isDigit(matrix[i][right+1])){
                break;
            }
            if(Character.isDigit(matrix[i][left-1])) left--;
            if(Character.isDigit(matrix[i][right+1])) right++;

        }

        while(left<=right){
            n += (matrix[i][left] -'0')*Math.pow(10, right-left);
            matrix[i][left] = '.';
            left++;
        }

        return n;
    }

    private static void populateMatrix(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                matrix[i][j] = line.charAt(j);
            }
        }
    }
}
