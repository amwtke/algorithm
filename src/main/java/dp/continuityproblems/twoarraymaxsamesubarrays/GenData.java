package dp.continuityproblems.twoarraymaxsamesubarrays;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GenData {
    public static int GenRandomInt(int max) {
        Random random = new Random();
        return Math.abs(random.nextInt()) % max;
    }

    public static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

    public static int[] genRandomArrays(int number, int max) {
        List<Integer> container = new LinkedList<>();
        for (int i = 0; i < number; i++) {
            container.add(GenRandomInt(max));
        }
        return convertIntegers(container);
    }
}
