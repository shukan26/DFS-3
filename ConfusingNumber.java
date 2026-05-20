/**
 * Uses BFS to generate all valid numbers using rotatable digits {0,1,6,8,9} and checks
 * whether the rotated number differs from the original number to identify confusing numbers.
 * Time: O(5^d * d), Space: O(5^d) where d is the number of digits in n.
 */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ConfusingNumber {
    HashMap<Integer, Integer> map;

    public int confusingNumberII(int n) {
        this.map = new HashMap<>();

        map.put(0, 0);
        map.put(1, 1);
        map.put(6, 9);
        map.put(8, 8);
        map.put(9, 6);
        int count = 0;

        Queue<Long> q = new LinkedList<>();
        q.add(0L);

        while (!q.isEmpty()) {
            long currNum = q.poll();

            if (isConfusing((int) currNum)) {
                count++;
            }

            for (int key : map.keySet()) {
                long newNum = currNum * 10 + key;
                if (newNum != 0 && newNum <= n) {
                    q.add(newNum);
                }
            }

        }
        return count;

    }

    private boolean isConfusing(int num) {
        int temp = num;
        int result = 0;
        while (num > 0) {
            int lastDigit = num % 10;
            result = result * 10 + map.get(lastDigit);
            num = num / 10;
        }

        return result != temp;

    }
}
