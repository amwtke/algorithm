package dp.continuityproblems.twoarraymaxsamesubarrays;

public class FastDpWay {
    public static int findLength(int[] A, int[] B) {
        int[] state = new int[B.length];
        int maxValue = 0;
        for (int i = 0; i < A.length; i++) {
            int temp = 0;
            for (int j = 0; j < B.length; j++) {
                int value = A[i] == B[j] ? temp + 1 : 0;
                temp = state[j];
                state[j] = value;
                maxValue = Math.max(maxValue, state[j]);
            }
        }
        return maxValue;
    }
}
