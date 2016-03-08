
public class ClosestNumber {

	public static void main(String[] args) {
		int[] ret = {1,2,3};
		kClosestNumbers(ret, 2, 3);
	}
	/**
     * @param A an integer array
     * @param target an integer
     * @param k a non-negative integer
     * @return an integer array
     */
    public static int[] kClosestNumbers(int[] A, int target, int k) {
        // Write your code here
        // corner check
        //find crosscover point
        int[] result = new int[k];
        int[] resultstoreelement = new int[k];
        int index = findcrosscover(A, target);
        // from before and back two direction to find the k number element
        //result[0] = index;
        int left = index;
        int right = index + 1;
        int count = 0;
        /*for(int i = 0; i < k - 1; i++) {
            
            if(right <= A.length -1 && target - A[left] >= A[right] - target) {
                result[i+1] = right;
                right++;
            } else {
                if(left >= 0 && target - A[left] < A[right] - target) {
                    result[i+1] = left;
                    left--;
                }
            }
        }*/
        // below is deal the two pointer to compare the element to make sure the difference how much select the samller
        while(left >= 0 && right < A.length && count < k) {
            if(target - A[left] <= A[right] - target) {
                result[count] = left; 
                resultstoreelement[count] = A[left];
                left--;
            } else {
                result[count] = right; 
                resultstoreelement[count] = A[right];
                right++;
            }
            count++;
        }
        // only left has element
        while(count < k && left >= 0) {
            result[count] = left;
            resultstoreelement[count] = A[left];
            left--;
            count++;
        }
        // only right has element
        while(count < k && right < A.length) {
            result[count] = right;
            resultstoreelement[count] = A[right];
            right++;
            count++;
        }
        return resultstoreelement;
    }
    private static int findcrosscover(int[] A, int target) {
        int left = 0;
        int right = A.length - 1;
        while(left + 1 < right) {
            int middle = left + (right - left) / 2;
            if(A[middle] == target) {
                return middle;
            } else {
                if(target > A[middle - 1] && target < A[middle]) {
                    return target - A[middle - 1] > A[middle] - target ? middle : middle - 1;
                } else {
                    if(target > A[middle] && target < A[middle + 1]) {
                        return target - A[middle] > A[middle + 1] - target ? middle + 1 : middle;
                    } else {
                        if(A[middle] > target) {
                            right = middle;
                        } else {
                            left = middle;
                        }
                    }
                }
            }
        }
        return target - A[left] > A[right] - right ? right : left;
    }
}
