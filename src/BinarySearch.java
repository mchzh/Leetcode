
public class BinarySearch {

	public static void main(String[] args) {
		//int[] a = {0,1,1,1,11,12,13};
		//hIndex(a);
		int[] nums = {1,1,1,1};
		removeDuplicates(nums);
	}
	public static int removeDuplicates(int[] nums) {
		int len = nums.length;
        if(len <= 2) return len;
        int lenmark = 0;
        for(int i = 0; i < len - 2; i++) {
            if(nums[i] == nums[i+2])
               lenmark++;
        }
        return len-lenmark;
    }
	 public static int hIndex(int[] citations) {
	        int left = 0;
	    int L = citations.length;
	    int right = L-1;
	    while(left <= right) {
	        int mid = left + (right-left)/2;
	        if(citations[mid] < L-mid)
	            left = mid+1;
	        else if(citations[mid] > L-mid)
	            right = mid-1;
	        else
	            return citations[mid];
	    }
	    return L-left;
	        //return hIndexdivide(citations, 0, citations.length-1);
	  }
	public static int hIndexold(int[] citations) {
        return hIndexdivide(citations, 0, citations.length-1);
    }
	
    private static int hIndexdivide(int[] citations, int left, int right) {
        int midh = 0;
        while(left <= right) {
            midh = left + (right - left)/2;
            if(citations[midh] >= midh) {
                if(citations[midh-1] > midh) {
                    right = midh -1;
                }
            } else {
                return 0;
            }    
        }
        return midh+1;
    }
        
}
