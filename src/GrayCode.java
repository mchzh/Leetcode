import java.util.LinkedList;
import java.util.List;

public class GrayCode {

	public List<Integer> grayCode(int n) {
        //n is n-1 gray code self + reverse with previou 1 bit add
        List<Integer> graylist = new LinkedList<Integer>();
        graylist.add(0);
        for(int i = 0; i < n; i++) {
            int size = graylist.size();
            for(int j = size-1; j >= 0; j--) {
                graylist.add(graylist.get(j) | 1<<i);
            }
        }
        return graylist;
        //another is DFS solution can output all possible
    }
}
