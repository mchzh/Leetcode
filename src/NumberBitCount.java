import java.util.ArrayList;

public class NumberBitCount {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*String a = "1010";
		String b = "1011";
		addBinary(a, b);
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(1);
		nums.add(-1);
		nums.add(-2);
		nums.add(1);
		minSubArray(nums);
		String strdeal = "peymrzknlxtrutjiybqemquchgvtmmtpjvunvekszrkatctcirxwuqknrycpdtcuadblzkkleduezgspoxhhssoipbmdgrqggpfdsanolzczpaggwxrlaleaqtnzxclmxwjucnujsptnbmmjzzjhypnlsoxjveywsufegzlfnyvkcnfevkshbckfropoydkdlblppllgefagjgpajsplvxknvtlgtjyhmnwxcpjjzcizihycvsnhnnmqohivekitxzuo";
		removeDuplicateLetters(strdeal);*/
		String strtemp1 = "bb";
		longestPalindromeT(strtemp1);
	}
	//5. Longest Palindromic Substring
	
	public static String longestPalindromeT(String s) {
        String str = "";
        if(s == null || s.length() == 0) {
            return str;
        }
        int len = Integer.MIN_VALUE;
        for(int i = 0; i < s.length(); i++) {
            for(int j = i; j < s.length(); j++) {
                if(isPalindrome(s, i, j)) {
                    
                    if(len <= j-i) {
                        str = s.substring(i, j+1);
                    }
                    len = Math.max(len, j-i);
                }
            }
        }
        return str;
    }
    private static boolean isPalindrome(String strPal, int i, int j) {
        if(i == j) {
            return true;
        }
        while(i < j) {
            if(strPal.charAt(i) != strPal.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
	// 316. Remove Duplicate Letters
	public static String removeDuplicateLetters(String s) {
        // one method is first sort and deal
        // another method is use hashset
        if(s == null || s.length() == 0) {
            return null;
        }
        String str = "";
        char[] charconst = new char[26];
        for(int i = 0; i < s.length(); i++) {
            charconst[s.charAt(i) - 'a'] = s.charAt(i);
        }
        int i = 0;
        while(charconst[i] != 0) {
            str += charconst[i];
            i++;
        }
        return str;
        
    }

	 /**
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public static int minSubArray(ArrayList<Integer> nums) {
        // write your code
        // f[i] = Math.min(nums[i], f(i-1)+nums[i]);
        int[] f = new int[nums.size()];
        f[0] = nums.get(0);
        for(int i = 1; i < nums.size(); i++) {
            f[i] = Math.min(nums.get(i), f[i-1] + nums.get(i));
        }
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < nums.size(); i++) {
            if(f[i] <= result) {
                result = f[i];
            }
        }
        return result;
        //return f[nums.size() - 1];
    }
	// leetcode 67: Add Binary
	public static String addBinary(String a, String b) {
        // this problem consider carry control 
        // to get the matlength between the two string and let the shorter sting left is keep zero
        // get the suitbale length and to every position to process add operation
        // use a string to store the result of addding
        int lena = a.length();
        int lenb = b.length();
        int len = Math.max(lena, lenb);
        String result = "";
        int carry = 0;  // carry bit
        int apos = 0, bpos = 0;
        for(int i = len-1; i >= 0; i--) {  //from right to left order of string
            if(lena < len) {
                if( i < len - lena) {
                    apos = 0;
                } else {
                    apos = a.charAt(i - len + lena) - '0';
                }
            } else {
                apos = a.charAt(i) - '0';
            }
            if(lenb < len) {
                if( i < len - lenb) {
                    bpos = 0;
                } else {
                    bpos = b.charAt(i - len + lenb) - '0';
                }
            } else {
                bpos = b.charAt(i) - '0';
            }
            
            int temp = apos + bpos + carry;
            carry = temp / 2;
            //result += temp % 2;
            result = temp % 2 + result;
        }
        return ( carry == 0 ) ? result : "1" + result;
	}
}
