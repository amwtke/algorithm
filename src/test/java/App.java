package com.thoughtWorks.trains;

import java.util.*;

/**
 * @description: thoughtWorks problem one： trains
 * jvm：jdk1.8
 * @author: wpli
 * @date: 2018-12-06 19:33:27
 **/
public class App {
    private static List<String> stops = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E"));
    private static int[][] lengthMap = {
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1}};

    /**
     * @description: 主函数入口
     * @author: wpli
     * @date: 2018-12-06 19:34:38
     **/
    public static void main(String[] args) {
        App app = new App();
        Scanner sc = new Scanner(System.in);
        System.out.print("Graph: ");
        String inputStr = sc.nextLine();
        app.handleInputString(inputStr);
//        System.out.println("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");
//        app.handleInputString("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");

        System.out.println("Output #1: " + app.distanceOfRouteABC());
        System.out.println("Output #2: " + app.distanceOfrouteAD());
        System.out.println("Output #3: " + app.distanceOfRouteADC());
        System.out.println("Output #4: " + app.distanceOfRouteAEBCD());
        System.out.println("Output #5: " + app.distanceOfRouteAED());
        System.out.println("Output #6: " + app.countRoutesFromCToCWithMax3Stops());
        System.out.println("Output #7: " + app.countRoutesFromAToCWith4Stops());
        System.out.println("Output #8: " + app.shortestLengthFromAToC());
        System.out.println("Output #9: " + app.shortestLengthFromBToB());
        System.out.println("Output #10: " + app.countRoutesFromCToCWithMax30Length());
    }

    /**
     * @description: handle the input string, init the length map
     * @author: wpli
     * @date: 2018-12-06 22:01:16
     **/
    private void handleInputString(String inputString) {
        if (null == inputString || inputString.isEmpty()) {
            System.out.println("wrong input, input string can't be null or empty.");
            return;
        }
        String[] strArray = inputString.split(",");
        for (int i = 0; i < strArray.length; i++) {
            String tempStr = strArray[i].trim();
            if (tempStr.length() < 3) {
                System.out.println("wrong route, route[" + tempStr + "] is not a correct route.");
                continue;
            }
            updateLengthMap(tempStr.substring(0, 1), tempStr.substring(1, 2), Integer.parseInt(tempStr.substring(2)));
        }
    }

    private void updateLengthMap(String beginStop, String endStop, int length) {
        if (beginStop.equals(endStop)) {
            System.out.println("wrong stop, the start stop and end stop can't be the same.");
            return;
        }
        if (length < 0) {
            System.out.println("wrong length, length[" + length + "] is not less than 0.");
            return;
        }
        int beginStopId = stops.indexOf(beginStop);
        int endStopId = stops.indexOf(endStop);
        if (-1 == beginStopId) {
            System.out.println("wrong stop, the start stop[" + beginStop + "] is not in the stop list.");
            return;
        } else if (-1 == endStopId) {
            System.out.println("wrong stop, the end stop[" + endStop + "] is not in the stop list.");
            return;
        } else {
            lengthMap[beginStopId][endStopId] = length;
        }
    }

    /**
     * @description: get distance by path
     * @author: wpli
     * @date: 2018-12-06 22:01:47
     * @return: distance of the path, return "NO SUCH ROUTE" if route not exist
     **/
    private String getDistanceOfPath(String path) {
        if (null == path || path.isEmpty() || 1 == path.length()) {
            System.out.println("wrong path, path can't be null or empty or only one stop.");
            return "NO SUCH ROUTE";
        }
        int distance = 0;
        for (int i = 0; i < path.length() - 1; i++) {
            int beginStopId = stops.indexOf(path.substring(i, i + 1));
            int endStopId = stops.indexOf(path.substring(i + 1, i + 2));
            if (-1 == lengthMap[beginStopId][endStopId]) {
                return "NO SUCH ROUTE";
            } else {
                distance += lengthMap[beginStopId][endStopId];
            }
        }
        return String.valueOf(distance);
    }

    /**
     * @description: 深度优先搜索
     * @author: wpli
     * @date: 2018-12-06 23:01:07
     * @param:
     * @return:
     **/
    private int dfs(String endStop, String path, int maxStopCount) {
        if (path.length() - 1 > maxStopCount) {
            return 0;
        }
        if (path.length() > 1) {
            int beginStopId = stops.indexOf(path.substring(path.length() - 2, path.length() - 1));
            int endStopId = stops.indexOf(path.substring(path.length() - 1));
            if (-1 == lengthMap[beginStopId][endStopId]) {
                return 0;
            }
        }
        if (path.length() > 1 && path.endsWith(endStop)) {
            return 1;
        }
        int tripCount = 0;
//       int newStopId = stops.indexOf(String.valueOf(path.charAt(path.length()-1)));
        for (int i = 0; i < stops.size(); i++) {
            tripCount += dfs(endStop, path + stops.get(i), maxStopCount);
        }
        return tripCount;
    }

    private int bfs(String beginStop, String endStop, int stopCount) {
        if (stopCount < 0) {
            return 0;
        }
        if (0 == stopCount && beginStop.endsWith(endStop)) {
            return 1;
        }
        int tripCount = 0;
        int id = stops.indexOf(beginStop.substring(beginStop.length() - 1));
        for (int i = 0; i < stops.size(); i++) {
            if (lengthMap[id][i] > 0) {
                tripCount += bfs(beginStop + stops.get(i), endStop, stopCount - 1);
            }
        }
        return tripCount;
    }

    /**
     * @description: 按深度优先算法搜索无环路径及其距离的map
     * @author: wpli
     * @date: 2018-12-07 10:08:15
     **/
    private HashMap<String, Integer> getRouteDistanceMapByDfs(String endStop, String path, int distance) {
        if (path.endsWith(endStop) && distance > 0) {
            HashMap<String, Integer> pathMap = new HashMap<String, Integer>();
            pathMap.put(path, distance);
            return pathMap;
        }
        int lastStopId = stops.indexOf(path.substring(path.length() - 1));
        HashMap<String, Integer> allPathMap = new HashMap<String, Integer>();
        for (int i = 0; i < stops.size(); i++) {
            String newStop = stops.get(i);
            int newDistance = lengthMap[lastStopId][i];
            if (newDistance > 0) {
                if (path.indexOf(newStop) > 0) {
                    continue;
                }
                HashMap<String, Integer> newPathMap = getRouteDistanceMapByDfs(endStop, path + newStop, distance + newDistance);
                allPathMap.putAll(newPathMap);
            }
        }
        return allPathMap;
    }

    /**
     * @description: 获取不超过指定距离的所有路径及其距离的map
     * @author: wpli
     * @date: 2018-12-07 10:06:26
     **/
    private HashMap<String, Integer> getRouteDistanceWithMaxDistanceByDfs(String endStop, String path, int maxDistance, int distance) {
        HashMap<String, Integer> allPathMap = new HashMap<String, Integer>();
        if (path.endsWith(endStop) && distance < maxDistance && distance > 0) {
            allPathMap.put(path, distance);
        }
        int lastStopId = stops.indexOf(path.substring(path.length() - 1));
        for (int i = 0; i < stops.size(); i++) {
            String newStop = stops.get(i);
            int newDistance = lengthMap[lastStopId][i];
            if (newDistance > 0 && distance + newDistance < maxDistance) {
                HashMap<String, Integer> newPathMap = getRouteDistanceWithMaxDistanceByDfs(endStop, path + newStop, maxDistance, distance + newDistance);
                allPathMap.putAll(newPathMap);
            }
        }
        return allPathMap;
    }


    /**
     * @description: 最短路径算法
     * @author: wpli
     * @date: 2018-12-06 23:32:37
     * @param:
     * @return:
     **/
    private int dijkstra() {
        return 0;
    }

    /**
     * @description: output #1 The distance of the route A-B-C.
     * @author: wpli
     * @date: 2018-12-06 21:51:02
     * @return: distance of A-B-C, return "NO SUCH ROUTE" if not exist
     **/
    private String distanceOfRouteABC() {
        return getDistanceOfPath("ABC");
    }

    /**
     * @description: output #2 The distance of the route A-D.
     * @author: wpli
     * @date: 2018-12-06 21:51:02
     * @return: distance of A-D, return "NO SUCH ROUTE" if not exist
     **/
    private String distanceOfrouteAD() {
        return getDistanceOfPath("12");
    }

    /**
     * @description: output #3 The distance of the route A-D-C.
     * @author: wpli
     * @date: 2018-12-06 21:51:02
     * @return: distance of A-D-C, return "NO SUCH ROUTE" if not exist
     **/
    private String distanceOfRouteADC() {
        return getDistanceOfPath("ADC");
    }

    /**
     * @description: output #4 The distance of the route A-E-B-C-D.
     * @author: wpli
     * @date: 2018-12-06 21:51:02
     * @return: distance of A-E-B-C-D, return "NO SUCH ROUTE" if not exist
     **/
    private String distanceOfRouteAEBCD() {
        return getDistanceOfPath("AEBCD");
    }

    /**
     * @description: output #5 The distance of the route A-E-D.
     * @author: wpli
     * @date: 2018-12-06 21:51:02
     * @return: distance of A-E-D, return "NO SUCH ROUTE" if not exist
     **/
    private String distanceOfRouteAED() {
        return getDistanceOfPath("AED");
    }

    /**
     * @description: output #6 The number of trips starting at C and ending at C with a maximum of 3 stops.
     * In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
     * @author: wpli
     * @date: 2018-12-06 21:51:02
     * @return: number of trips
     **/
    private String countRoutesFromCToCWithMax3Stops() {
        int tripCount = 0;
//       int newStopId = stops.indexOf(String.valueOf(path.charAt(path.length()-1)));
        for (int i = 0; i < stops.size(); i++) {
            tripCount += dfs("C", "C" + stops.get(i), 3);
        }
        return String.valueOf(tripCount);
    }

    /**
     * @description: output #7 The number of trips starting at A and ending at C with exactly 4 stops.
     * In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
     * @author: wpli
     * @date: 2018-12-06 21:51:02
     * @return: number of trips
     **/
    private String countRoutesFromAToCWith4Stops() {
//        bfs("A", "C", 4);
        int tripCount = 0;
        int id = stops.indexOf("A");
        for (int i = 0; i < stops.size(); i++) {
            if (lengthMap[id][i] > 0) {
                tripCount += bfs("A" + stops.get(i), "C", 3);
            }
        }
        return String.valueOf(tripCount);
    }

    /**
     * @description: output #8 The length of the shortest route (in terms of distance to travel) from A to C.
     * @author: wpli
     * @date: 2018-12-06 21:51:02
     * @return: length of the shortest route
     **/
    private String shortestLengthFromAToC() {
        HashMap<String, Integer> pathMap = getRouteDistanceMapByDfs("C", "A", 0);
        return String.valueOf(pathMap.values().stream().min(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }

        }).get());
    }

    /**
     * @description: output #9 The length of the shortest route (in terms of distance to travel) from B to B.
     * @author: wpli
     * @date: 2018-12-06 21:51:02
     * @return: length of the shortest route
     **/
    private String shortestLengthFromBToB() {
        HashMap<String, Integer> pathMap = getRouteDistanceMapByDfs("B", "B", 0);
        return String.valueOf(pathMap.values().stream().min(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }

        }).get());
    }

    /**
     * @description: output #10 The number of different routes from C to C with a distance of less than 30.
     * In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
     * @author: wpli
     * @date: 2018-12-06 21:51:02
     * @return: number of different routes
     **/
    private String countRoutesFromCToCWithMax30Length() {
        HashMap<String, Integer> pathMap = getRouteDistanceWithMaxDistanceByDfs("C", "C", 30, 0);
        return String.valueOf(pathMap.values().stream().count());
    }
}
