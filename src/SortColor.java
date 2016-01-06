
public class SortColor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
