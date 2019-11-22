package dp.continuityproblems.twoarraymaxsamesubarrays;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class CommonWay {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 2, 1};
        int[] b = {3, 2, 1};
        System.out.println(findLength(a, b));
    }

    public static int findLength(int[] A, int[] B) {
        int[][] table = new int[A.length][B.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                dp(A, B, i, j, table);
            }
        }
        Resutl resutl = getMax(table);
        log.info("sub arrays:{}", resutl.getMaxSubArrays());
        return resutl.getMax();
    }

    private static Resutl getMax(int[][] table) {
        Resutl resutl = new Resutl();
        int max = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                if (max < table[i][j]) {
                    max = table[i][j];
                    resutl.setMaxI(i);
                    resutl.setMaxJ(j);
                }
            }
        }
        resutl.setMax(max);
        for (int i = 0; i < resutl.getMax(); i++) {
            resutl.getMaxSubArrays().add(String.valueOf(table[resutl.getMaxI() - i][resutl.getMaxJ() - i]));
        }
        return resutl;
    }

    private static int dp(int[] a, int[] b, int i, int j, int[][] table) {
        int Aij;
        if (a[i] == b[j]) {
            Aij = 1;
        } else {
            Aij = -1;
        }

        if (i == 0 || j == 0) {
            table[i][j] = Aij;
        } else {
            table[i][j] = Math.max(dp(a, b, i - 1, j - 1, table) + Aij, Aij);
        }
        return table[i][j];
    }

    @Data
    static class Resutl {
        private int max;
        private int maxI;
        private int maxJ;
        private List<String> maxSubArrays = new LinkedList<>();
    }
}
