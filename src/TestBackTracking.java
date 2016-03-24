
public class TestBackTracking {
	public static void main(String[] args) {
		//int[] stocksie = {39, 20, 45, 51, 36, 23, 54, 69};
		int[] stocksie = {100, 180, 260, 310, 40, 535, 695};
		// backtracking test code region
		BackTracking back = new BackTracking();
		int grid[][] = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, //
				   { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, //
				   { 0, 8, 7, 0, 0, 0, 0, 3, 1 }, //
				   { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, //
				   { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, //
				   { 0, 5, 0, 0, 9, 0, 6, 0, 0 }, //
				   { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, //
				   { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, //
				   { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };
		//PrintTwoArrayContent(grid);
		//back.GenerateBinarySequence(3);
		//back.GenerateMarySequence(3, 3);
		//back.Combinations("ABC");
		//back.GenerateSubsets("ABCDE");
		//int[] arr = {100, 180};
		//back.GenerateSubsetsSumEqualK(stocksie, 280);
		//back.Permutation("ABC");
		//back.PrintSubsetsSizeK("ABCDE", 3);
		//back.nQueens(4);
		if(back.SolveSudoku(grid)) 
			System.out.println("successful get sudoku!");
	}
}
