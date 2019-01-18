package dp.YH_triangle_path;

import java.util.Random;

public class ConvertArryToYHTriangle {
    public static int sumOfNaturalNumber(int n) {
        return (int) ((Math.pow(n, 2) + n) / 2);
    }

    public static void main(String[] args) {
        YHTriangle yhTriangle = new YHTriangle();
        yhTriangle.addAll(5, 7, 8, 2, 3, 4, 4, 9, 6, 1, 2, 7, 9, 4, 5);
        yhTriangle.print();
        System.out.println(yhTriangle.getNumberOfLevel());
        System.out.println(yhTriangle.getNumberOfNodes());
        System.out.println(yhTriangle.isPerfect());
    }

    public static YHTriangle makeLargeYHTriangle(int lastLevelNumber) {
        YHTriangle yhTriangle = new YHTriangle();
        Random random = new Random();
        for (int i = 1; i <= lastLevelNumber; i++) {
            for (int j = 0; j < i; j++) {
                yhTriangle.addOne(random.nextInt(1000));
            }
        }
        return yhTriangle;
    }
}
