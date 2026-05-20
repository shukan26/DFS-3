import java.util.Arrays;

/**
 * Uses DFS + backtracking to try placing each matchstick into one of the 4 sides while ensuring
 * no side exceeds the target length. Sorting in descending order helps prune invalid states early.
 * Time: O(4^n) in the worst case due to backtracking, Space: O(n) recursion stack.
 */
public class MatchSticksToSquare {

    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        int maxLength = 0;

        for (int num : matchsticks) {
            sum += num;
            maxLength = Math.max(maxLength, num);
        }

        int lengthOfEachSide = sum / 4;

        if (sum % 4 != 0) {
            return false;
        }

        if (maxLength > lengthOfEachSide) {
            return false;
        }

        Arrays.sort(matchsticks);
        reverse(matchsticks);

        return helper(matchsticks, 0, new int[4], lengthOfEachSide);
    }

    private boolean helper(int[] matchsticks, int idx, int[] square, int lengthOfEachSide) {

        if (idx == matchsticks.length) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            if (square[i] + matchsticks[idx] <= lengthOfEachSide) {

                square[i] += matchsticks[idx];

                if (helper(matchsticks, idx + 1, square, lengthOfEachSide)) {
                    return true;
                }

                square[i] -= matchsticks[idx];
            }
        }

        return false;
    }

    private void reverse(int[] arr) {
        int low = 0, high = arr.length - 1;

        while (low < high) {
            swap(arr, low, high);
            low++;
            high--;
        }
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
