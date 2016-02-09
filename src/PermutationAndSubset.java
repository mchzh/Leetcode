import java.util.ArrayList;
import java.util.Collections;

public class PermutationAndSubset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(1);
		nums.add(2);
		nums.add(2);
		permuteUnique(nums);
		int[] A = {1,3,3,4,5};
		totalOccurrence(A, 3);
		int[] num = {4,4,5,5,6,6,7,7,0,0,1,1,2,2};
		findMin(num);
	}
	 /**
     * @param num: a rotated sorted array
     * @return: the minimum number in the array
     */
    public static int findMin(int[] num) {
        // write your code here
        // middle crossover point is the minimum
        if(num == null || num.length == 0) {
            return -1;
        }
        int start = 0, end = num.length - 1;
        if(num[start] < num[end]) {
            return num[start];
        }
        while(start + 1 < end) {
            int middle = start + (end - start) / 2;
            if(num[middle] >= num[middle + 1]) {
                return num[middle+1];
            } else {
                if(num[middle]>= num[end] && num[middle] <= num[middle + 1]) {
                    start = middle;
                } else {
                    end = middle;
                }
            }
            
        }
        return num[start] <= num[end] ? num[start] : num[end];
    }
	 /**
     * @param A an integer array sorted in ascending order
     * @param target an integer
     * @return an integer
     */
    public static int totalOccurrence(int[] A, int target) {
        // Write your code here
    	if(A == null || A.length == 0) {
            return 0;
        }
        int left = 0, right = A.length - 1;
        int count = 0;
        while( left + 1 < right ) {
            int middle = left + (right - left) / 2;
            if( A[middle] == target) {
                count++;
                /*int lpointer = middle, rpointer = middle;
                while( lpointer-1 >= 0 && A[lpointer-1] == target) {
                    lpointer--;
                    count++;
                }
                while( rpointer+1 < A.length && A[rpointer+1] == target) {
                    rpointer++;
                    count++;
                }
                break;*/
            } else {
                if( target > A[middle]) {
                    left = middle;
                } else {
                    right = middle;
                }
            }
        }
        if( A[left] == target ) count++;
        if( A[right] == target ) count++;
        return count;
    }
    
	public static ArrayList<ArrayList<Integer>> permuteUnique(ArrayList<Integer> nums) {
        // write your code here
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if(nums == null || nums.size() == 0) {
            return result;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        Collections.sort(nums);
        dfs(result, list, nums);
        return result;
    }
    private static void dfs(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, ArrayList<Integer> ele) {
        if(list.size() == ele.size()) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        for(int i = 0; i < ele.size(); i++) {
            if(list.contains(ele.get(i))) {
                continue;
            }
            list.add(ele.get(i));
            dfs(result, list, ele);
            list.remove(list.size() - 1);
        }
    }

}
