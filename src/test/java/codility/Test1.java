package codility;

import org.junit.Test;

public class Test1 {
    public int solution(int[] A) {
        return compute(A, 0, null);
    }

    private int compute(int[] a, int i, Boolean isFirstCountIn) {
        if (i >= a.length) {
            return 0;
        }

        //last one
        if (i == (a.length - 1)) {
            if (isFirstCountIn) {
                return 0;
            }
            return (a[i] + a[0]) % 2 == 0 ? 1 : 0;
        }

        //count in i
        if (i == 0) {
            isFirstCountIn = true;
        }
        int countInMax = ((a[i] + a[i + 1]) % 2 == 0) ? compute(a, i + 2, isFirstCountIn) + 1 : compute(a, i + 2, isFirstCountIn);

        //not count in i
        if (i == 0) {
            isFirstCountIn = false;
        }
        int notCountInMax = compute(a, i + 1, isFirstCountIn);
        return Math.max(countInMax, notCountInMax);
    }

    private int computeDp(int[] a, int i, Boolean isFirstCountIn, int[] dp) {
        if (i >= a.length) {
            return 0;
        }

        //last one
        if (i == (a.length - 1)) {
            if (isFirstCountIn) {
                return 0;
            }
            return (a[i] + a[0]) % 2 == 0 ? 1 : 0;
        }

        //count in i
        if (i == 0) {
            isFirstCountIn = true;
        }
        int countInMax = ((a[i] + a[i + 1]) % 2 == 0) ? compute(a, i + 2, isFirstCountIn) + 1 : compute(a, i + 2, isFirstCountIn);

        //not count in i
        if (i == 0) {
            isFirstCountIn = false;
        }
        int notCountInMax = compute(a, i + 1, isFirstCountIn);
        return Math.max(countInMax, notCountInMax);
    }

    @Test
    public void test() {
        int[] a = {5, 5, 5, 5, 5, 5};
        int[] a1 = new int[5];
        System.out.println(a1[2]);
        System.out.println(solution(a));
    }
}
