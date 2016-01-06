import java.util.HashSet;
import java.util.Iterator;

/*Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

For example,
Given n = 3,

You should return the following matrix:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]*/

public class SpiralMatrix {

	public static void main(String[] args) {
		int n = 3;
		int[][] matrix = {{0,0,0,5},{4,3,1,4},{0,1,1,4},{1,2,1,3},{0,0,1,1}};
		setZeroes(matrix);
		System.out.println(matrix);
		generateMatrix(n);
	}
	public static int[][] generateMatrix(int n) {
		if(n < 0) n = -n;
        int[][] res = new int[n][n];
        int count = 1;
        for(int i = 0; i < (n+1)/2; i++) {
            for(int trow=i; trow < n-i; trow++) {
                res[i][trow] = count;
                count++;
            }
            for(int rcol=i+1; rcol < n-i; rcol++) {
                res[rcol][n-1-i] = count;
                count++;
            }
            for(int drow=n-2-i; drow >= i; drow--) {
                res[n-1-i][drow] = count;
                count++;
            }
            for(int lcol=n-2-i; lcol >= i+1; lcol--) {
                res[lcol][i] = count;
                count++;
            }
            if(count > n*n) {
                break;
            }
        }
        return res;
    }
	
	public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstrowhaszero = false;
        boolean firstcolhaszero = false;
    
        //1 step: set first row mark is true if first row has 0
        //        set first col mark is true if first col has 0
        for(int col = 0; col < n; col++) {
            if(matrix[0][col] == 0) {
                firstrowhaszero = true;
                break;
            }
        }
        for(int row = 0; row < m; row++) {
            if(matrix[row][0] == 0) {
                firstcolhaszero = true;
                break;
            }
        }
        //2 step: find other row and col elem zero, store infoe in first row and column
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        //3 step: set zero except 1 row and 1 col
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        //4 step: last deal 1 row and 1 col
        if(firstrowhaszero) {
            for(int j = 0; j < n; j++) {
                if(matrix[0][j] != 0) {
                    matrix[0][j] = 0;
                }
            }
        }
        if(firstcolhaszero) {
            for(int i = 0; i < m; i++) {
                if(matrix[i][0] != 0) {
                    matrix[i][0] = 0;
                }
            }
        }
        
    }
	
	public static void setZeroes0ld(int[][] matrix) {
		int m = matrix.length;
        int n = matrix[0].length;
        HashSet<Integer> rowset = new HashSet<Integer>();
        HashSet<Integer> colset = new HashSet<Integer>();
        //int temp = Integer.MIN_VALUE;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == 0) {
                    if(!rowset.contains(i)) {
                        rowset.add(i);
                    }
                    if(!colset.contains(j)) {
                        colset.add(j);
                    }
                }
            }
        }
        Iterator<Integer> rowit = rowset.iterator();
        while(rowit.hasNext()){
			//System.out.println(iterator.next());
        	int i = rowit.next();
			for(int j = 0; j < n; j++) {
			    matrix[i][j] = 0;
			}
		}
		Iterator<Integer> colit = colset.iterator();
        while(colit.hasNext()){
			//System.out.println(iterator.next());
        	int j = colit.next();
			for(int i = 0; i < m; i++) {
			    matrix[i][j] = 0;
			}
		}
	}

}
