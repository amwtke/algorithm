//package codility;
//
//import org.junit.Test;
//
//import java.util.HashMap;
//
//public class Test2 {
//    public int solution(int N, int[] A, int[] B) {
//        return compute(N, A, B);
//    }
//
//    private int compute(int n, int[] a, int[] b) {
//        HashMap<Integer, Integer> map = rank(a, b);
//        return computeValue(map, a, b);
//    }
//
//    private HashMap<Integer, Integer> rank(int[] a, int[] b) {
//        HashMap<Integer, Integer> rankMap = new HashMap<>();
//        for (int i : a) {
//            if (rankMap.containsKey(i)) {
//                rankMap.put(i, rankMap.get(i) + 1);
//            } else {
//                rankMap.put(i, 1);
//            }
//        }
//        for (int i : b) {
//            if (rankMap.containsKey(i)) {
//                rankMap.put(i, rankMap.get(i) + 1);
//            } else {
//                rankMap.put(i, 1);
//            }
//        }
//    }
//
//    private int computeValue(HashMap<Integer, Integer> map, int[] a, int[] b) {
//        int retMax = 0;
//        for (int i = 0; i < a.length; i++) {
//            retMax = retMax + (map.get(a[i]) + map.get(b[i]));
//        }
//        return retMax;
//    }
//
//    @Test
//    public void test() {
//        int[] A = {2, 2, 1, 2};
//        int[] B = {1, 3, 4, 4};
//        System.out.println(solution(5, A, B));
//    }
//}
