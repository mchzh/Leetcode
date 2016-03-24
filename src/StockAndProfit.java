
public class StockAndProfit {

	public static void main(String[] args) {
		//int[] stocksie = {39, 20, 45, 51, 36, 23, 54, 69};
		int[] stocksie = {100, 180, 260, 310, 40, 535, 695};
		int total = MaxProfitofStock(stocksie);
		System.out.println("Max profits is -> " + total);
		//findBestBuySellInterval(stocksie);
		String s = new String("Hello");
		System.out.println(s);
		foo(s);
		System.out.println(s);
		StringBuilder sb = new StringBuilder("Hello");
		System.out.println(sb);
		foosb(sb);
		System.out.println(sb);
		
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
		
		// strbuffer and strbuilder execute effective speed test
		int N = 77777777;
        long t;
        {
            StringBuffer sbf = new StringBuffer();
            t = System.currentTimeMillis();
            for (int i = N; i --> 0 ;) {
                sbf.append("");
            }
            System.out.println(System.currentTimeMillis() - t);
        }
        {
            StringBuilder sbd = new StringBuilder();
            t = System.currentTimeMillis();
            for (int i = N; i --> 0 ;) {
                sbd.append("");
            }
            System.out.println(System.currentTimeMillis() - t);
        }

	}

	public static void PrintTwoArrayContent(int[][] arr) {
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[0].length; j++) {
				switch(j) {
				case 0: { System.out.print("{ " + arr[i][j] + ","); }
				case 8: { System.out.print(arr[i][j] + " }"); }
				default: { System.out.print(arr[i][j] + ","); }
				}
				//System.out.print(arr[i][j] + ",");
			}
			System.out.println();
		}
	}
	/*StringBuffer is synchronized, StringBuilder is not.
	StringBuilder is faster than StringBuffer because it's not synchronized.. */
	//http://www.meetqun.com/thread-2178-1-1.html

	public static void foo(String s) {
		s = new String("World");
	}
	public static void foosb(StringBuilder sb) {
		//sb = new StringBuilder("World");
		sb.replace(0, sb.length(), "World");
	}
	/*Maximum profit question: 
		Suppose we have an array of integers which signify stock process over number of days. We want to figure out what is the maximum profit we can make by buying and selling stocks. If the array is in decreasing order then we cannot make any profit. 
		{39, 20, 45, 51, 36, 23, 54, 69} 
		In this example we should buy the stock at 20 and then sell at 51 then again we should buy at 23 and sell at 69.
		You have to print out the intervals, example 20, 51 and 23, 69*/

	// this problem is to image that buy from valley and sell in peak
	// so inside a loop to find a pair of valley and peak to deal
	public static int MaxProfitofStock(int[] stocks) {
		if(stocks == null || stocks.length <= 1) return 0;
		int length = stocks.length;
		int pos = 0;
		int profits = 0;
		int min = stocks[0];
		int max = stocks[0];
		boolean haspeak = false; //judge whether mark a processing from valley to peak
		while( pos < length-1 ) {
			// judge the next element is ascending or desceding
			// ascending is to find peak, and descending is to find valley
			
			// ascending a segment to get the top value
			int incre = pos + 1;
			while( stocks[incre] > stocks[pos] && (incre < length && pos < length - 1) ) {
				if(incre == length - 1) {
					pos++;
					break;
				}
				incre++;
				pos++;
				haspeak = true;
			}
			if(haspeak) {
				max = stocks[pos];
				profits += max - min; // between these elements to get max profit
			}
			
			// descending a segment to get the bottom value
			while( stocks[incre] < stocks[pos] && (incre < length && pos < length - 1) ) {
				if(incre == length - 1) {
					pos++;
					break;
				}
				incre++;
				pos++;
				haspeak = false;
			}
			if(!haspeak) {
				min = stocks[pos];
			}			
		}
		return profits;
	}
	
	/*Following is algorithm for this problem to print out the intervals of buy and sell.
	1. Find the local minima and store it as starting index. If not exists, return.
	2. Find the local maxima. and store it as ending index. If we reach the end, set the end as ending index.
	3. Update the solution (Increment count of buy sell pairs)
	4. Repeat the above steps if end is not reached.*/

	// This function finds the buy sell schedule for maximum profit
	public static void findBestBuySellInterval(int[] prices) {
		if(prices == null || prices.length <= 1) return;
		
		int len = prices.length;
		int countofpair = 0; // count of solution pairs
		Interval[] stocks = new Interval[len/2 + 1];
		// Traverse through given price array
		int pt = 0;
		while( pt < len - 1 ) {
			int buyofpair = 0, sellofpair = 0;
			// Find Local Minima. Note that the limit is (n-2) as we are
	        // comparing present element to the next element.
			while( pt < len - 1 && (prices[pt+1] <= prices[pt])) {
				pt++;
			}
			if(pt == len - 1) break;
			// Store the index of minima
			buyofpair = pt++;
			// Find Local Maxima.  Note that the limit is (n-1) as we are
	        // comparing to previous element
			while( pt < len && (prices[pt] >= prices[pt-1]) ) {
				pt++;
			}
			// Store the index of maxima
			sellofpair = pt - 1;
			stocks[countofpair] = new Interval(buyofpair, sellofpair);
			countofpair++;
		}
		if(countofpair == 0) {
			System.out.println("The prices of stocks not exists profits!");
		} else {
			for(Interval val : stocks) {
				System.out.println(val.buy + "," + val.sell);
			}
		}
	}
	// backtracking
	// 0 0 0
	public void GenerateBinarySequence(int n, int m) {
		if(n <= 0) return;
		int[] result = new int[n];
		GenerateBinarySequence(result, 0, m);
		
	}
	private void GenerateBinarySequence(int[] result, int current, int m) {
		if(current == result.length) {
			PrintArray(result);
			return;
		}
		for(int i = 0; i < m; i++) {
			result[current] = i;
			GenerateBinarySequence(result, current+1, m);
		}
	}
	private void PrintArray(int[] result) {
		for(int i : result) {
			System.out.println(i + " ");
		}
	}
	
	// A B C
	public void Combinations(String str) {
		if(str.length() == 0) return;
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
			Combinations(result, current + 1, arr);
		}
	}
	private void PrintCombinations(int[] result, char[] arr) {
		for(int i : result) {
			System.out.println(arr[result[i]] + " ");
		}
		for(int i = 0; i < result.length; i++) {
			System.out.println(arr[result[i]] + " ");
		}
	}
	
	/*#region GenerateSubsets
	
	#endregion*/
	
	
}
class Interval {
	protected int buy;
	protected int sell;
	public Interval(int buy, int sell) {
		this.buy = buy;
		this.sell = sell;
	}
}
//http://www.geeksforgeeks.org/stock-buy-sell/