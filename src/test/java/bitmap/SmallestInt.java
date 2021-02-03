package bitmap;

import org.junit.Test;
/*
Write a function:

class Solution { public int solution(int[] A); }

that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.

For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.

Given A = [1, 2, 3], the function should return 4.

Given A = [−1, −3], the function should return 1.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..100,000];
each element of array A is an integer within the range [−1,000,000..1,000,000].
Copyright 2009–2021 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 */

public class SmallestInt {
    public int solution(int[] A) {
        // write your code in Java SE 8
        return computeSmallestInt(A);
    }

    int computeSmallestInt(int[] A) {
        if (A == null) {
            return 0;
        }
        int min = 0;
        int max = -1000000;
        for (int a : A) {
            if (a < min) {
                min = a;
            }
            if (a > max) {
                max = a;
            }
        }
        if (max < 0) {
            return 1;
        }
        if (max == 1) {
            return 2;
        }

        int smallestInt = 0;
        int maxBitmapLength = getMaxBitmapLength(max);
        byte[] bimMap = formBitMap(A, maxBitmapLength);
        for (int i = 0; i < bimMap.length; i++) {
            byte b = bimMap[i];
            if ((int) b == 127) {
                continue;
            }
            if ((b | 1) != b) {
                smallestInt = i * 7 + 1;
                break;
            }
            if ((b | 2) != b) {
                smallestInt = i * 7 + 2;
                break;
            }
            if ((b | 4) != b) {
                smallestInt = i * 7 + 3;
                break;
            }
            if ((b | 8) != b) {
                smallestInt = i * 7 + 4;
                break;
            }
            if ((b | 16) != b) {
                smallestInt = i * 7 + 5;
                break;
            }
            if ((b | 32) != b) {
                smallestInt = i * 7 + 6;
                break;
            }
            if ((b | 64) != b) {
                smallestInt = i * 7 + 7;
                break;
            }
        }
        return smallestInt;
    }

    private byte[] formBitMap(int[] A, int maxBitmapLength) {
        byte[] bitmap = new byte[maxBitmapLength];
        for (int a : A) {
            if (a < 0) {
                continue;
            }
            int index = getIndex(a);
            int offset = a % 7;

            switch (offset) {
                case 0:
                    bitmap[index] = (byte) (bitmap[index] | 64);
                    break;
                case 1:
                    bitmap[index] = (byte) (bitmap[index] | 1);
                    break;
                case 2:
                    bitmap[index] = (byte) (bitmap[index] | 2);
                    break;
                case 3:
                    bitmap[index] = (byte) (bitmap[index] | 4);
                    break;
                case 4:
                    bitmap[index] = (byte) (bitmap[index] | 8);
                    break;
                case 5:
                    bitmap[index] = (byte) (bitmap[index] | 16);
                    break;
                case 6:
                    bitmap[index] = (byte) (bitmap[index] | 32);
                    break;
                default:
                    break;
            }
        }
        return bitmap;
    }

    private int getIndex(int a) {
        int index = a / 7;
        if (a % 7 == 0) {
            index = index - 1;
        }
        return index;
    }

    private int getMaxBitmapLength(int max) {
        return (max % 7 == 0) ? max / 7 : max / 7 + 1;
    }

    @Test
    public void test() {
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1000};
        System.out.println(solution(A));
    }
}
