
public class BackTracking {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// every bit positon has  0 or 1, total n bits
	// 0,0,0
	// 0,0,1
	// 0,1,0
	// 0,1,1
	// 1,0,0
	// 1,0,1
	// 1,1,0
	// 1,1,1
	public void GenerateBinarySequence(int n) {
		if(n <= 0) return;
		int[] result = new int[n];
		GenerateBinarySequence(result, 0);
		
	}
	private void GenerateBinarySequence(int[] result, int current) {
		if(current == result.length) {
			PrintArray(result);
			return;
		}
		for(int i = 0; i < 2; i++) {
			result[current] = i;
			GenerateBinarySequence(result, current+1);
		}
	}
	private void PrintArray(int[] result) {
		for(int i : result) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
	
	// follow up is every bit has m value from 0 to m-1
	public void GenerateMarySequence(int n, int m) {
		if(n <= 0) return;
		int[] result = new int[n];
		GenerateMarySequence(result, 0, m);
		
	}
	private void GenerateMarySequence(int[] result, int current, int m) {
		if(current == result.length) {
			PrintArray(result);
			return;
		}
		for(int i = 0; i < m; i++) {
			result[current] = i;
			GenerateMarySequence(result, current+1, m);
		}
	}
	
	// combinations  0 0 0/0 0 1/0 0 2
	public void Combinations(String str) {
		if(str == null || str.length() == 0) {
			return;
		}
		int[] result = new int[str.length()];
		char[] arr = str.toCharArray();
		Combinations(result, 0, arr);
	}
	private void Combinations(int[] result, int current, char[] arr) {
		if(current == result.length) {
			PrintCombinations(result, arr);
			return;
		}
		for(int i = 0; i < arr.length; i++) {
			result[current] = i;
			Combinations(result, current+1, arr);
		}
	}
	private void PrintCombinations(int[] result, char[] arr) {
		for(int i = 0; i < result.length; i++) {
			System.out.print(arr[result[i]] + " ");
		}
		System.out.println();
	}
	
	// Generate Subsets
	public void GenerateSubsets(String str) {
		if(str == null || str.length() == 0) {
			return;
		}
		int[] result = new int[str.length()];
		char[] arr = str.toCharArray();
		GenerateSubsets(result, 0, arr);
	}
	private void GenerateSubsets(int[] result, int current, char[] arr) {
		if(current == result.length) {
			PrintSubsets(result, arr);
			return;
		}
		for(int i = 0; i < 2; i++) {
			result[current] = i;
			GenerateSubsets(result, current+1, arr);
		}
	}
	private void PrintSubsets(int[] result, char[] arr) {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(int i = 0; i < result.length; i++) {
			if(result[i] == 1) {
				sb.append(arr[i]);
			}
			//System.out.print(result[i] + " ");
		}
		sb.append("}");
		System.out.println(sb.toString());
		// shortcut note 1: instead a variable name of the whole
		// select this variable and push combinate keys "Alt + Shift + R" , then input new
		// variable name
		// "Ctrl + Shift + R" is a open source window
		/*int intchange;
		intchange = 0;
		intchange = 1;
		intchange += intchange;*/
	}
	
	// GenerateSubsetsSumEqualK
	public void GenerateSubsetsSumEqualK(int[] arr, int k) {
		if(arr == null || arr.length == 0) {
			return;
		}
		int[] result = new int[arr.length];
		
		GenerateSubsetsSumEqualK(result, 0, arr, k);
	}
	private void GenerateSubsetsSumEqualK(int[] result, int current, int[] arr, int k) {
		if(current == result.length) {
			PrintSubsetsSumEqualK(result, arr, k);
			return;
		}
		for(int i = 0; i < 2; i++) {
			result[current] = i;
			GenerateSubsetsSumEqualK(result, current+1, arr, k);
		}
	}
	private void PrintSubsetsSumEqualK(int[] result, int[] arr, int k) {
		int sum = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for(int i = 0; i < result.length; i++) {
			if(result[i] == 1) {
				sb.append(arr[i] + ",");
				sum += arr[i];
			}
			
			//System.out.print(result[i] + " ");
		}
		sb.append("}");
		
		if(sum == k) {
			System.out.println(sb.toString());
		}
		
		/*for(int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
		System.out.println();*/
	}
	
	// Permutation  0 1 2/0 2 1/1 0 2	
	public void Permutation(String str) {
		if(str == null || str.length() == 0) {
			return;
		}
		int[] result = new int[str.length()];
		char[] arr = str.toCharArray();
		Permutation(result, 0, arr);
	}
	private void Permutation(int[] result, int current, char[] arr) {
		if(current == result.length) {
			PrintCombinations(result, arr);
			return;
		}
		for(int i = 0; i < arr.length; i++) {
			if(IsValid(result, current, i)) {
				result[current] = i;
				Permutation(result, current+1, arr);
			}		
		}
	}
	private boolean IsValid(int[] result, int current, int num) {
		for(int i = 0; i < current; i++) {
			if(result[i] == num) {
				return false;
			}
		}
		return true;
	}
	
	// PrintSubsetsSizeK
	public void PrintSubsetsSizeK(String str, int k) {
		if(str == null || str.length() == 0 || k <= 0) {
			return;
		}
		char[] result = new char[k];
		char[] arr = str.toCharArray();
		PrintSubsetsSizeK(arr, k, result, 0, 0);
	}
	private void PrintSubsetsSizeK(char[] arr, int k, char[] result, int current, int indexInArray) {
		if(current == k) {
			//PrintCombinations(result, arr);
			String s = new String(result);
			System.out.println(s);
			return;
		}
		if(indexInArray >= arr.length) {
			return;
		}
		result[current] = arr[indexInArray];
		PrintSubsetsSizeK(arr, k, result, current+1, indexInArray+1);
		PrintSubsetsSizeK(arr, k, result, current, indexInArray+1);
	}
	
	// Solve Maze
	public boolean SolveMaze(int[][] arr) {
		if(arr == null || arr.length == 0 || arr[0].length == 0) {
			return false;
		}
		char[] result = new char[arr.length + arr[0].length];
		return SolveMaze(arr, 0, 0, result, 0, -1, -1);
	}
	private boolean SolveMaze(int[][] arr, int i, int j, char[] result, int current, int iPrev, int jPrev) {
		// arr is i row and j column
		if(i < arr.length || j > arr[0].length) {
			return false;
		}
		// exit condition of success
		if(i == arr.length && j == arr[0].length && arr[i][j] == 1) {
			if(j > jPrev && jPrev != -1) {
				result[current] = 'R';
			}
			if(i > iPrev && iPrev != -1) {
				result[current] = 'D';
			}
			String s = new String(result);
			System.out.println(s);
			return true;
		}
		if(arr[i][j] == 0) return false;
		// every path element deal logical
		if(j > jPrev && jPrev != -1) {
			result[current] = 'R';
		}
		if(i > iPrev && iPrev != -1) {
			result[current] = 'D';
		}
		
		// recursion dfs
		// right direction to go
		if( SolveMaze(arr, i, j+1, result, current+1, i, j) ) {
			return true;
		}
		// down direction to go
		if( SolveMaze(arr, i+1, j, result, current+1, i, j) ) {
			return true;
		}
		return false;
	}
	
	// nQueens problem
	public void nQueens(int n) {
		if(n <= 0) {
			return;
		}
		int[] result = new int[n];
		nQueens(result, n, 0);
	}
	private void nQueens(int[] result, int n, int current) {
		if(current == n) {
			//print result
			PrintNQueens(result);
		}
		for(int i = 0; i < n; i++) {
			if(IsValidPosition(result, current, i)) {
				result[current] = i;
				nQueens(result, n, current + 1);
			}
		}
	}
	private boolean IsValidPosition(int[] result, int current, int num) {
		for(int i = 0; i < current; i++) {
			if(result[i] == num || Math.abs(current - i) == Math.abs(result[i] - num)) {
				return false;
			}
		}
		return true;
	}
	private void PrintNQueens(int[] result) {
		for(int i = 0; i < result.length; i++) {
			System.out.println(i + " " + result[i]);
		}
		System.out.println("Next Value");
	}
	
	class Cell {
		int row;
		int col;
		public Cell(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}
	};
	// Solve Sudoku problem
	public boolean SolveSudoku(int[][] arr) {
		if(arr == null || arr[0].length == 0 || arr.length == 0) {
			return false;
		}		
		return SolveSudoku(arr, new Cell(0, 0));
	}
	private boolean SolveSudoku(int[][] arr, Cell cell) {
		// if among arr find 0, it show this is not result, or else is a result of execution
		if(!FindLocation(arr)) {
			//print result
			PrintSudokuContent(arr);
			return true;
		}
		// if arr[cur] already has a value, there is nothing to solve here,
		// continue on to next cell
		if(arr[cell.row][cell.col] != 0) {
			// has value, not to try 1-9, go to next value
			return SolveSudoku(arr, getNextCell(cell));
		}
		// this is where each possible value is being assigned to the cell, and
		// checked if a solutions could be arrived at.	  
		// if arr[cur] doesn't have a value
		// try each possible value
		for(int i = 1; i <= 9; i++) {
			// check if valid, if valid, then update
			if(sudoIsValid(arr, cell, i)) {
				arr[cell.row][cell.col] = i;
				 // continue with next cell
				if( SolveSudoku(arr, getNextCell(cell)) ) {
					return true;
				} else {
					arr[cell.row][cell.col] = 0; // backtracking previous status
				}
			} else {
				continue;
			}
		}
		return false;
	}
	private boolean FindLocation(int[][] arr) {
		if(arr == null || arr[0].length == 0 || arr.length == 0) return false;
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				if(arr[i][j] == 0) return true;
			}
		}
		return false;
	}
	// simple function to get the next cell
	 // read for yourself, very simple and straight forward
	private Cell getNextCell(Cell curr) {
		int row = curr.row;
		int col = curr.col;
		col++;
		
		if(col > 8) {
			col = 0;
			row++;
		} 
		if(row > 8) return null;
		
		Cell next = new Cell(row, col);
		return next;
	}
	/**
	  * Utility function to check whether @param value is valid for @param cell
	  */
	private boolean sudoIsValid(int[][] arr, Cell pos, int value) {
		int row = pos.row;
		int col = pos.col;
		
		// check this row
		for(int index = 0; index < col; index++) {
			if(arr[row][index] == value) return false;
		}
		// check this col
		for(int index = 0; index < row; index++) {
			if(arr[index][col] == value) return false;
		}
		// check the triple of located
		int startrow = row / 3;
		int startcol = col / 3;
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(arr[startrow*3 + i][ startcol*3 + j] == value) 
					return false;
			}
		}
		return true;
	}
	// print the final result of arr
	private void PrintSudokuContent(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				switch(j) {
				case 0: { System.out.print("{ " + arr[i][j] + ","); break; }
				case 8: { System.out.print(arr[i][j] + " }"); break;}
				default: { System.out.print(arr[i][j] + ","); }
				}
				//System.out.print(arr[i][j] + ",");
			}
			System.out.println();
		}
	}
}
