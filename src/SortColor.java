import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class SortColor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		SortColor3Poi sortcolor = new SortColor3Poi();
		int[] nums = {0,2,2,2,2,1,0,1,0,0,0,1,0,2,0};
		String str = nums.toString();
		System.out.println("Arrya is -> " + str);
		sortcolor.sortColors(nums);
		//int[] numbersold = {0,4,3,0};
		//int[] numbers = {2,7,11,15};
		int[] numberts = {2,7,11,15};
		//twoSum(numbers, 9);
		threeSumClosest(numberts,3);
		int[] arr = {1,0,-1,0,-2,2};
		fourSum(arr, 0);
		String str4 = " 1 ";
		reverseWords(str4);
		int[] narray = {1,2,0,1};
		longestConsecutive(narray);
		String str1 = "AAAAAAAAAABBBAAAAAAAAA";
		String str2 = "BBBBAAAAAAAAA";
		compareStrings(str1, str2);
	}
	 /**
     * @param A : A string includes Upper Case letters
     * @param B : A string includes Upper Case letter
     * @return :  if string A contains all of the characters in B return true else return false
     */
    public static boolean compareStrings(String A, String B) {
        // write your code here
        // bit-vector
        //HashSet<Character> charset = new HashSet<Character>();
        /*for(int i = 0; i < A.length(); i++) {
            Upperchararray[A.charAt(i)]++;
        }
        for(int i = 0; i < B.length(); i++) {
            Upperchararray[A.charAt(i)]++;
        }*/
        if(A == null || B == null) {
            return false;
        }
        for(int i = 0; i < A.length(); i++) {
            for(int j  = 0; j < B.length(); j++) {
                if(A.charAt(i) == B.charAt(j)) {
                    String part1 = B.substring(0, j);
                    String part2 = B.substring(j+1, B.length());
                    B = new String();
                    B = part1 + part2;
                    break;
                }
            }
        }
        if(B.length() != 0) {
            return false;
        }
        return true;
    }
	/**
     * @param nums: A list of integers
     * @return an integer
     */
    public static int longestConsecutive(int[] num) {
        // write you code here
        // use hastable or hashset to reach the purpose of using the space to get time-effiency
        // 遇到不能排序又要复杂度O()有序的问题，只能增加空间复杂度，用hashset或者hashtable 
        HashSet<Integer> setlong = new HashSet<Integer>();
        if( num== null || num.length == 0 ) {
            return 0;
        }
        
        // initialize
        for(int i : num) {
            setlong.add(i);
        }
        //  loop array and find the consecutive sequence
        int prelen = 0;
        for(int val : num) {
            int curlen = 1;
            while( setlong.contains(val-1) ) {
                setlong.remove(val);
                curlen++;
                val--;
            }
            while( setlong.contains(val+1) ) {
                setlong.remove(val);
                curlen++;
                val++;
            }
            //setlong.remove(val);
            prelen = Math.max(curlen, prelen);
        }
        return prelen;
    }
	public static String reverseWords(String s) {
        /*if(s == null || s.length() == 0) {
            return s;
        }
        ArrayList<String> str = new ArrayList<String>();
        str = s.split(" ");
        str.reverse();
        return str.toString();*/
        StringBuilder result = new StringBuilder();
        if(s == null || s.length() == 0) {
            return result.toString();
        }
        String[] word = s.split(" ");
        // reverse the word
        for(int i = word.length - 1; i >= 0; i--) {
            if(word[i].length() > 0 ) {
                if(i == 0) {
                    result.append(word[i]);
                } else {
                    result.append(word[i]+ " ");
                }
                
            } 
            
        }
        // remove the end space
        return result.toString();
    }
	
	public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length < 4) {
            return result;
        }
        List<Integer> list = new ArrayList<Integer>();
        int len = nums.length;
        Arrays.sort(nums);
        for(int i = 0; i < len - 3; i++) {
            for(int j = i + 1; j < len -2; j++) {
                int left = j + 1, right = len -1;
                while(left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if(sum == target) {
                        if(list.isEmpty()) {
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[left]);
                            list.add(nums[right]);
                            result.add(new ArrayList(list));
                        } else {
                            list.remove(list.size() - 1);
                        }
                        break;
                    } else {
                        if(sum < target) {
                            left++;
                        } else {
                            right--;
                        }
                    }
                }
            }
        }
        return result;
    }
	/**
     * @param numbers: Give an array numbers of n integer
     * @param target : An integer
     * @return : return the sum of the three integers, the sum closest target.
     */
    public static int threeSumClosest(int[] numbers ,int target) {
        // write your code here
        // first solve 2sum closest problem
        // http://www.cnblogs.com/grandyang/p/4501934.html
        if(numbers == null || numbers.length == 0) {
            return 0;
        }
        int len = numbers.length;
        int sum;
        Arrays.sort(numbers);
        int closest = Integer.MAX_VALUE;
        for(int i = 0; i < len; i++) {
            sum = target - numbers[i];
            int left = i + 1, right = len - 1;
            while(left < right) {
            	int twosum = numbers[left] + numbers[right];
                if(twosum < sum) {
                    left++;
                } else {
                    right--;
                }
                closest = Math.min(closest, Math.abs(twosum - sum));
                //System.out.println(numbers[left] + numbers[right]);
            }
        }
        return closest+target;
    }
	public static int[] twoSum(int[] numbers, int target) {
		int[] result = {0,0};
        if(numbers == null || numbers.length == 0) {
            return result;
        }
        
        int left = 0;
        int right = numbers.length - 1;
        Arrays.sort(numbers);
        while(left < right) {
            if(numbers[left] + numbers[right] < target) {
                left++;
            } else {
                if(numbers[left] + numbers[right] > target) {
                    right--;
                } else {
                    if(numbers[left] + numbers[right] == target) {
                        result[0] = left+1;
                        result[1] = right+1;
                    }
                }
            }
        }
        return result;
        // write your code here
        /*int[] result = {0,0};
        if(numbers == null || numbers.length == 0) {
            return result;
        }
        for(int i = 0; i < numbers.length; i++) {
            for(int j = 0; j < numbers.length; j++) {
                if( (numbers[i] + numbers[j]) == target ) {
                    if(i <= j) {
                        result[0] = i + 1;
                        result[1] = j + 1;
                    } else {
                        result[0] = j + 1;
                        result[1] = i + 1;
                    }
                    
                }
            }
        }
        return result;*/
    }

	/*Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

	*Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

	*Note:
	*You are not suppose to use the library's sort function for this problem.

	*click to show follow up.

	*Subscribe to see which companies asked this question

	*Show Tags
	 Show Similar Problems*/

	public void sortColors(int[] nums) {
        // three pointer to solute, left is point the first no red from front forward
        // right is point the first no blue from back to front
        // and the third pointer is to iterate the array
        if(nums == null || nums.length == 0)
            return;
        int left = 0;
        int right = nums.length - 1;
        /*while(nums[left] == 0) {
            left++;
        }*/
        while(right > 0 && nums[right] == 2) {
            right--;
        }
        int roll = left;
        while(roll <= right) {
            if(nums[roll] == 0) {
                swap(nums,roll,left);
                left++;
                roll++;
            } else {
                if(nums[roll] == 2) {
                    swap(nums,roll,right);
                    right--;
                } else {
                    roll++;
                }
            }
        }
    }
    private void swap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }
}

class SortColor3Poi {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    /*public void sortColors(int[] nums) {
        // write your code here
        // two-pass, one-pass to calculate the number of every element, the second pass to put into
        int len = nums.length;
        int redsum = 0, whitesum = 0, bluesum = 0;
        for(int i = 0; i < len; i++) {
            switch( nums[i] ) {
                case 0 :
                    redsum++;
                    break;
                case 1 :
                    whitesum++;
                    break;
                case 2 :
                    bluesum++;
                    break;
                default :
                    break;
            }
        }
        for(int i = 0; i < len; i++) {
            if(i < redsum) {
                nums[i] = 0;
            } else {
                if(i < redsum + whitesum) {
                    nums[i] = 1;
                } else {
                    nums[i] = 2;
                }
            }
        }
    }*/
    public void sortColors(int[] nums) {
        // write your code here
        // one-pass need three-pointer notred, notblue and forward;
        // notred is the first position of not 0;
        // notblue is the first position of not 2;
        if(nums == null && nums.length == 0) {
            return;
        }
        int notred = 0, notblue = nums.length - 1;
        int len = nums.length;
        while(notred < len && nums[notred] == 0) {
            notred++;
        }
        while(notblue >= 0 && nums[notblue] == 2) {
            notblue--;
        }
        // from the notred position to iterator
        int i = notred;
        /*while( i <= notblue ) {
            if(nums[i] == 0) {
                swap(nums, notred, i);
                notred++;
                i++;
            } else {
                if(nums[i] == 2) {
                    swap(nums, i, notblue);
                    notblue--;
                } else {
                    if(nums[i] == 1) {
                        i++;
                    }
                }
            
            }
            
        }*/
        while( i <= notblue ) {
            if(nums[i] == 0) {
                swap(nums, notred, i);
                notred++;
                i++;
            } 
            if(nums[i] == 2) {
                swap(nums, i, notblue);
                notblue--;
            } 
            if(nums[i] == 1) {
               i++;
            }  
        }
        
    }
    private void swap(int[] A, int left, int right) {
        int temp = A[left];
        A[left] = A[right];
        A[right] = temp;
    }
}
