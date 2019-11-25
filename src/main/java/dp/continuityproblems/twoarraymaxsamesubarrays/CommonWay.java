package dp.continuityproblems.twoarraymaxsamesubarrays;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

import static dp.continuityproblems.twoarraymaxsamesubarrays.GenData.genRandomArrays;

@Slf4j
public class CommonWay {
    public static void main(String[] args) {
//        int[] a = {1, 0, 1, 0, 1, 1, 1, 1, 0, 0};
//        int[] b = {1, 1, 0, 1, 1, 0, 1, 0, 0, 1};

//        int[] a = {1, 0, 1, 0, 1, 1, 1, 1, 0};
//        int[] b = {1, 1, 0, 1, 1, 0, 1, 0, 0};
        long begin, end;
        int[] a = genRandomArrays(10000, 10);
        int[] b = genRandomArrays(10000, 10);


        begin = System.currentTimeMillis();
        int length = FastDpWay.findLength(a, b);
        end = System.currentTimeMillis();
        log.info("matrix way------->{}, time:{}", length, end - begin);

        begin = System.currentTimeMillis();
        Resutl length2 = findLength(a, b);
        end = System.currentTimeMillis();
        log.error("common way------->{},time:{}", length2.getMax(), end - begin);
//        log.info("a:{}", a);
//        log.info("b:{}", b);
    }

    public static Resutl findLength(int[] A, int[] B) {
        long begin, end;

        int delta =
//                -1;
        -Math.max(A.length, B.length);
        int[][] table = new int[A.length][B.length];
        intiTable(table);
        begin = System.currentTimeMillis();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                dp(A, B, i, j, table, delta);
            }
        }
        end = System.currentTimeMillis();
        log.info("computing:{}", end - begin);

        begin = System.currentTimeMillis();
        Resutl resutl = getMax(A, B, table);
        end = System.currentTimeMillis();
        log.info("result:{}", end - begin);
        return resutl;
    }

    private static void intiTable(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = -1;
            }
        }
    }

    private static Resutl getMax(int[] a, int[] b, int[][] table) {
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

        printSubArray(a, resutl.getMaxI(), resutl.getMax());
        printSubArray(b, resutl.getMaxJ(), resutl.getMax());
        return resutl;
    }

    private static void printSubArray(int[] a, int maxI, int max) {
        List<Integer> ret = new LinkedList<>();
        for (int i = 0; i < max; i++) {
            ret.add(a[maxI - i]);
        }
        log.info("arrays:{}", ret);
    }

    private static int dp(int[] a, int[] b, int i, int j, int[][] table, int delta) {
        if (table[i][j] != -1) {
            return table[i][j];
        }
        int Aij;
        if (a[i] == b[j]) {
            Aij = 1;
        } else {
            Aij = delta;
        }

        if (i == 0 || j == 0) {
            table[i][j] = Aij;
        } else {
            table[i][j] = Math.max(dp(a, b, i - 1, j - 1, table, delta) + Aij, Aij);
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
