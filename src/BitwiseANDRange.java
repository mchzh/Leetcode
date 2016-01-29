import java.util.Arrays;
import java.util.HashMap;

/*Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

 *For example, given the range [5, 7], you should return 4.*/
/*Repeated DNA Sequences 求重复的DNA序列， Single Number 单独的数字,   Single Number II 单独的数字之二 ， Grey Code 格雷码，和 Reverse Bits 翻转位 */
public class BitwiseANDRange {

	public static void main(String[] args) {
		/*int m = 2000;
		int n = 7;
		int[] nbits = {1,1,1,1,1,1,2};
		int[] L = {-1,0,1,2,2,2,3,3,3,4,4,4,5,5,6,90,92,93,101};
		int k = 2;
		int[][] matrix = {{1,3,5,7},{10,11,16,20},{23,30,34,50}};
		int[] nums = {88,4,24,82,86,1,56,74,71,9,8,18,26,53,77,87,60,27,69,17,76,23,67,14,98,13,10,83,20,43,39,29,92,31,0,30,90,70,37,59};
		longestIncreasingSubsequence(nums);*/
		int[] nums = {1,2};
		int i = 0;
		System.out.println("nums -> "+nums[i++]);
		int[] A = {13,52,42,21,58};
		jump(A);
		int[] B = {1,5,10};
		int sum = 20;
		minPatches(B, sum);
		String s1 = "aa";
		String s2 = "a";
		String s3 = "aaa";
		isInterleave(s1, s2, s3);
		//String s = "aab";
		String s = "caaaa";
		find(s);
		//searchRange(L,k);
		//searchMatrix(matrix,k);
		//int[][] matrix = {{1,1,1,1},{2,2,2,2,},{3,3,3,3,},{4,4,4,4}};
		//woodCut(L,k);
		//System.out.println("matrix 0 -> "+matrix[0]);
		//System.out.println("matrix 1 -> "+matrix[1]);
		//System.out.println("matrix 2 -> "+matrix[2]);
		//System.out.println("matrix 3 -> "+matrix[3]);
		//singleNumber(nbits);
		//int count = bitonepattern(3);
		//System.out.println("bit 1 count -> "+count);
		//int s = 2147483647;
		//int pm = pow2num(s);
		//System.out.println(pm);
		//int result = rangeBitwiseAnd(m,s);
		//System.out.println("final -> "+result);
		//rangeBitwiseAnd(m,n);
	}
	/**
     * given a string consists of alpabetic characters, find the most oddly occurring character
     */
	public static char find(String s) {
        char[] array =  s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int count = 1;
        char res = array[0];
        for(int i = 0; i < s.length(); i++) {
                if(!map.containsKey(array[i])) {
                        map.put(array[i], 1);
                } else {
                        map.put(array[i], map.get(array[i]) + 1);
                }
                int temp = map.get(array[i]);
                if(temp % 2 == 1 && temp >= count) {
                        count = temp;
                        res = array[i];
                }
        }
        return res;
}
	
	/**
     * Determine whether s3 is formed by interleaving of s1 and s2.
     * @param s1, s2, s3: As description.
     * @return: true or false.
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        // write your code here
        if( (s1.length() + s2.length()) != s3.length() ) {
            return false;
        }
        if(s1 == null || s1.length() == 0) {
            if(s2.equals(s3)) {
                return true;
            } else {
                return false;
            }
        }
        if(s2 == null || s2.length() == 0) {
            if(s1.equals(s3)) {
                return true;
            } else {
                return false;
            }
        }
        // state: f[i][j] shows s1's the first ith and s2's the first jth interleaving
        //        compose s3's the first i+j th string;
        // formula: f[i][j] = (f[i-1][j] && s1[i-1] == s3[i+j-1]) || 
        //                    (f[i][j-1] && s2[j-1] == s3[i+j-1])
        // initialize: f[i][0] = (s1[0--i-1] == s3[0--i-1])
        //             f[0][j] = (s2[0--j-1] == s3[0--j-1])
        // answer: f[n][m], n = sizeof(s1) m = sizeof(s2);
        
        int n = s1.length();
        int m = s2.length();
        boolean[][] f = new boolean[n + 1][m + 1];
        f[0][0] = true;
        //initialize
        for( int i = 1; i < n+1; i++ ) {
        	System.out.println("s1 and s3 substring -> "+s1.substring(0,i) +" "+ s3.substring(0,i));
            if( s1.substring(0,i).equals(s3.substring(0,i)) ) {
                f[i][0] = true;
            }
        }
        for( int j = 1; j < m+1; j++ ) {
            if( s2.substring(0,j).equals(s3.substring(0,j)) ) {
                f[0][j] = true;
            }
        }
        
        for(int i = 1; i < n+1; i++) {
            for(int j = 1; j < m+1; j++) {
                //f[i][j] = (f[i-1][j] && (s1.charAt(i-1) == s3.charAt(i+j-1))) || 
                //          (f[i][j-1] && (s2.charAt(j-1) == s3.charAt(i+j-1)));
                if( (f[i-1][j] && (s1.charAt(i-1) == s3.charAt(i+j-1))) ||
                    (f[i][j-1] && (s2.charAt(j-1) == s3.charAt(i+j-1))) ) {
                    f[i][j] = true;
                }
            }
        }
        return f[n][m];
    }
    
	public static int minPatches(int[] nums, int n) {
        int cnt = 0,i=0;
        for(long known_sum = 1;known_sum <= n;){
            if(i < nums.length && known_sum >= nums[i]){
                known_sum += nums[i++];
            }else{
                known_sum <<= 1;
                cnt++;
            }
        }
        return cnt;
    }
	/**
     * @param A: A list of lists of integers
     * @return: An integer
     */
    public static int jump(int[] A) {
        // write your code here
        // a little person jump inside so it is a coordinate dp problem
        if(A == null || A.length == 0) {
            return 0;
        }
        // state: the i th number can use mini step to jump reach this place how many
        // formula: f[i] = min(f[i], f[j]+1), j < i, j+f[j] > i(j can reach i)
        // initialize: f[0] = 0
        // answer: return f[len - 1] coordinate array len is equal sequence length
        int len = A.length;
        int[] dp = new int[len];
        dp[0] = 0;
        for(int i = 1; i < len; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        for(int i = 1; i < len; i++) {
            for(int j = 0; j < i; j++) {
                if(A[j]+j >= i) {
                    dp[i] = Math.min(dp[i], dp[j]+1);
                }
            }
        }
        return dp[len - 1];
    }
	/**
     * @param nums: The integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
	public static int longestIncreasingSubsequence(int[] nums) {
        // write your code here
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int dp = 0;
        for(int i = 0; i < nums.length-1; i++) {
            if(nums[i] <= nums[i+1]) {
                int temp = 1;
                for(int j = i+1; j < nums.length; j++) {
                    //88,4,24,82,86,1,56,74,71,9,8,18,26,53,77,87,60,27,69,17,76,23,67,14,98,13,10,83,20,43,39,29,92,31,0,30,90,70,37,59
                	System.out.println("nums j -> "+nums[j]);
                	System.out.println("nums j-1 -> "+nums[j-1]);
                    if(nums[j] >= nums[j-1]) {
                        temp++;
                    } 
                    
                }
                dp = Math.max(dp, temp);
            }
        }
        return dp;
    }
	/** 
     *@param A : an integer sorted array
     *@param target :  an integer to be inserted
     *return : a list of length 2, [index1, index2]
     */
    public static int[] searchRange(int[] A, int target) {
        // write your code here
        int index1 = -1;
        int index2 = -1;
        int[] res = {index1, index2};
        if(A == null || A.length == 0) {
            return res;
        }
        int start = 0;
        int start1 = 0;
        int end = A.length - 1;
        int end1 = A.length - 1;
        while((start + 1 < end) && (start1 + 1 < end1)) {
            int mid = start + (end - start) / 2;
            if(target <= A[mid]) {
                end = mid;
            } else {
                start = mid;
            }
            int mid1 = start1 + (end1 - start1) / 2;
            if(target >= A[mid1]) {
                start1 = mid1;
            } else {
                end1 = mid1;
            }
        }
        if(A[start] == target) { 
            index1 = start;
        } else {
            if(A[end] == target) index1 = end;
        }
        
        if(A[end1] == target) {
            index2 = end1;
        } else {
            if(A[start1] == target) index2 = start1;
        }
        
        res[0] = index1;
        res[1] = index2;
        return res;
    }
	/**
     * @param matrix, a list of lists of integers
     * @param target, an integer
     * @return a boolean, indicate whether matrix contains target
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
        if(matrix == null || matrix.length == 0) return false;
        int row = matrix.length;
        int col = matrix[0].length;
        if(row == 0 || col == 0) return false;
        int start = 0;
        int end = row*col -1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            if(matrix[mid/col][mid%col] == target) {
                return true;
            } else {
                if(matrix[mid/col][mid%col] > target) {
                    end = mid;
                } else {
                    start = mid;
                }
            }
        }
        int xpos = start/col;
        int ypos = start%col;
        System.out.println("start pos element -> "+matrix[xpos][ypos]);
        if(matrix[start/col][start%col] == target) { return true; }
        if(matrix[end/col][end%col] == target) { return true; }
        return false;
    }
	public static int woodCut(int[] L, int k) {
        // write your code here
        Arrays.sort(L);
        int start = 0;
        int end = L[0];
        while(start + 1 < end) {
            int mid = start + (end - start) / 2;
            int count = 0;
            for(int i = 0; i < L.length; i++) {
                count += L[i] / mid;
            }
            if(count >= k) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return end;
    }
	public static int singleNumber(int[] A) {  
        if(A.length==0) {  
            return 0;  
        }  
        int ones=0, twos=0, threes=0;  
        for (int i=0;i<A.length;i++){  
            twos |= ones & A[i];  
            ones = ones ^ A[i];  
            threes = ones & twos;  
            ones = ones & ~threes;  
            twos = twos & ~threes;  
        }  
        return ones;  
    }  
	public static int bitonepattern(int n) {
        if(n < 1) return 0;
        if(n < 1) return 0;
        if(n == 1) return 1;
        return (int) (10*bitonepattern(n-1)+Math.pow(10,n-1));
    }
	public static int rangeBitwiseAnd(int m, int n) {
        if(n < m || n <= 0 || m <= 0) return 0;
        if(m == n) return m;
        //int pow1 = pow2num(m);
        int pow2 = pow2num(n) + 1;
        int[] res = new int[pow2];
        for(int i = 0; i < pow2; i++) res[i] = 1;
        for(int i = m; i <= n; i++) {
            setbitand(m,res);
        }
        int last = 0;
        for(int i = 0; i < pow2; i++) {
            last += res[i] == 1 ? (int)Math.pow(2,res[i]) : 0;
        }
        return last;
        //return 0;
    }
    private static void setbitand(int input, int[] record) {
        int index = 0;
        while(input != 1) {
            if(input % 2 == 0) {
                if(record[index] != 0) {
                    record[index] = 1;
                }
            }
            index++;
            input /= 2;
        }
    }
    private static int pow2num(int num) {
        int ponent = 0;
        while(num/2 >= 1) {
            ponent++;
            num /= 2;
        }
        return ponent;
    }

}
