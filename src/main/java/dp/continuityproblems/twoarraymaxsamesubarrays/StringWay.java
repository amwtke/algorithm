package dp.continuityproblems.twoarraymaxsamesubarrays;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class StringWay {
    public static void main(String[] args) {
        StringWay stringWay = new StringWay();

        String sourceStr = "for(int i=0;i<10;i++){\n" +
                "    doSomething(i)\n" +
                "}";
        String targetStr = "for(int aa=0;aa<10;aa++){\n" +
                "    doSomething(aa)\n" +
                "}";
        stringWay.find(sourceStr.trim().toCharArray(), targetStr.trim().toCharArray());

//        char[] source = {'d', 'f', 'a', 'a', 'c', 'c', 'b', 'a'};
//        char[] target = {'d', 'f', 'a', 'b', 'c', 'c', 'b', 'a'};
//        stringWay.find(source, target);
        printSubArray(stringWay);
    }

    private int equalDelta = 1;
    int delta =
            -1;
    //        -Math.max(this.source.length, this.target.length);
    private int maxValue;
    private int maxI, maxJ;
    private CommonWay.Result result = new CommonWay.Result();
    private char[] source;
    private char[] target;
    private int[][] table;

    public CommonWay.Result find(char[] sourceChars, char[] targetChars) {
        this.source = sourceChars;
        this.target = targetChars;
        long begin, end;


        int sourceSize = source.length;
        int targetSize = target.length;
        table = new int[sourceSize][targetSize];
        intiTable(table);
        begin = System.currentTimeMillis();
        for (int i = 0; i < sourceSize; i++) {
            for (int j = 0; j < targetSize; j++) {
                dp(source, target, i, j, table, delta);
            }
        }
        end = System.currentTimeMillis();
        log.info("computing:{}", end - begin);

        return result;
    }

    private int dp(char[] a, char[] b, int i, int j, int[][] table, int delta) {
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
        if (maxValue < table[i][j]) {
            maxValue = table[i][j];
            maxI = i;
            maxJ = j;
        }
        return table[i][j];
    }

    private void intiTable(int[][] table) {
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[i].length; j++) {
                table[i][j] = -1;
            }
        }
    }

    public static void printSubArray(StringWay stringWay) {
        int[][] table = stringWay.getTable();
        int maxCountingLeng = 0;
        for (int i = 0; i < stringWay.getSource().length; i++) {
            int value = table[stringWay.getMaxI() - i][stringWay.getMaxJ() - i];
            if (value >= stringWay.getEqualDelta()) {
                maxCountingLeng++;
            } else {
                break;
            }
        }

        log.info("max value is:{}.", stringWay.getMaxValue());
        StringBuilder sb = new StringBuilder(10);

        for (int i = 0; i < maxCountingLeng; i++) {
            int index = stringWay.getMaxI() - maxCountingLeng + 1 + i;
            sb.append(stringWay.getSource()[index]);
        }
        log.info("sub arrays source:{}", sb);

        sb = new StringBuilder(10);
        for (int i = 0; i < maxCountingLeng; i++) {
            int index = stringWay.getMaxJ() - maxCountingLeng + 1 + i;
            sb.append(stringWay.getTarget()[index]);
        }
        log.info("sub arrays target:{}", sb);

        sb = new StringBuilder(10);
        for (int i = 0; i < stringWay.table.length; i++) {
            sb.append('\n');
            for (int j = 0; j < stringWay.table[i].length; j++) {
                int i1 = stringWay.table[i][j];
                if (i1 >= 0) {
                    sb.append('+').append(i1).append(' ');
                } else {
                    sb.append(i1).append(' ');
                }

            }
        }
        log.info("table is:{}", sb);
    }

    @Data
    static class Result {
        private int max;
        private int maxI;
        private int maxJ;
    }
}
