package dp.YH_triangle_path;

import java.util.ArrayList;

public class ShortestPath {

    private int MAXVALUE = Integer.MAX_VALUE;
    private int minDistForTraceBackWay = MAXVALUE;

    public Integer DPWay(YHTriangle yhTriangle) throws Exception {
        int minValue = MAXVALUE;
        if (!checkValidation(yhTriangle)) {
            return null;
        }
        int numberOfLevel = yhTriangle.getNumberOfLevel();
        Integer[][] states = new Integer[numberOfLevel][numberOfLevel];
        for (int i = 0; i < numberOfLevel; i++) {
            for (int j = 0; j < numberOfLevel; j++) {
                states[i][j] = null;
            }
        }

        for (int level = 0; level < numberOfLevel; level++) {
            ArrayList<Integer> levelNumbers = yhTriangle.getInnerList().get(level);
            for (int i = 0; i < levelNumbers.size(); i++) {
                Integer value = yhTriangle.get(level, i);
                if (value == null) {
                    throw new Exception("value is null!");
                }
                if (level == 0 && i == 0) {
                    states[level][i] = value;
                    continue;
                }
                int fatherLevel = level - 1;
                int leftFatherIndex = i - 1;
                int rightFatherIndex = i;
                //left side numbers
                if (leftFatherIndex < 0 && rightFatherIndex == 0) {
                    states[level][i] = states[fatherLevel][rightFatherIndex] + value;
                } else if (yhTriangle.getInnerList().get(fatherLevel).size() == rightFatherIndex) {
                    states[level][i] = states[fatherLevel][leftFatherIndex] + value;
                } else {

                    int leftFatherShortestValue = states[fatherLevel][leftFatherIndex];
                    int rightFatherShortestValue = states[fatherLevel][rightFatherIndex];
                    int minFathers = Math.min(leftFatherShortestValue, rightFatherShortestValue);
                    states[level][i] = minFathers + value;
                }

                if (level == yhTriangle.getInnerList().size() - 1) {
                    if (minValue > states[level][i]) {
                        minValue = states[level][i];
                    }
                }
            }
        }
        return minValue;
    }

    public Integer traceBackWay(YHTriangle yhTriangle) {
        if (!checkValidation(yhTriangle)) {
            return null;
        }
        minDistForTraceBackWay = MAXVALUE;
        int dist = 0;
        traceBackWayInner(0, 0, dist, yhTriangle);
        return minDistForTraceBackWay;
    }

    private void traceBackWayInner(int level, int index, int dist, YHTriangle yhTriangle) {
        int lastLevel = yhTriangle.getNumberOfLevel() - 1;
        int value = yhTriangle.get(level, index);
        int tmpDist = dist + value;
        if (level == lastLevel) {
            if (tmpDist < minDistForTraceBackWay) {
                minDistForTraceBackWay = tmpDist;
            }
            return;
        }
        traceBackWayInner(level + 1, index, tmpDist, yhTriangle);
        traceBackWayInner(level + 1, index + 1, tmpDist, yhTriangle);
    }

    private boolean checkValidation(YHTriangle yhTriangle) {
        if (!yhTriangle.isPerfect()) {
            return false;
        }
        int numberOfLevel = yhTriangle.getNumberOfLevel();
        int lastLevelSize = yhTriangle.getLastLevelSize();
        if (numberOfLevel != lastLevelSize) {
            return false;
        }
        return true;
    }

//    @Data
//    @AllArgsConstructor
//    static class TravelObject {
//        int level;
//        int index;
//    }
}
