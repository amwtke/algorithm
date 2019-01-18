package dp.YH_triangle_path;

import lombok.Data;
import org.essentials4j.New;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
public class YHTriangle {
    private ArrayList<ArrayList<Integer>> innerList = new ArrayList<ArrayList<Integer>>();
    private int numberOfLevel = 0;
    private int numberOfNodes = 0;
    private boolean isPerfect = false;

    public void addAll(Integer... elements) {
        for (Integer element : elements) {
            addOne(element);
        }
    }

    public void addOne(Integer node) {
        if (numberOfLevel == 0 && numberOfNodes == 0) {
            ArrayList<Integer> tmp = new ArrayList<Integer>(1);
            tmp.add(node);
            innerList.add(tmp);
            numberOfNodes++;
            numberOfLevel++;
            isPerfect = true;
            return;
        }
        //满了
        if (innerList.get(numberOfLevel - 1).size() == numberOfLevel) {
            ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp.add(node);
            innerList.add(tmp);
            numberOfLevel++;
            isPerfect = false;
        } else {
            innerList.get(numberOfLevel - 1).add(node);
            isPerfect = innerList.get(numberOfLevel - 1).size() == numberOfLevel;
        }
        numberOfNodes++;
    }

    //level从0开始
    public Integer get(int levelIndex, int index) {
        if (levelIndex > numberOfLevel - 1 || levelIndex > innerList.size() - 1) {
            return null;
        }
        if (index > innerList.get(levelIndex).size() - 1) {
            return null;
        }
        return innerList.get(levelIndex).get(index);
    }

    public void print() {
        for (ArrayList<Integer> integers : innerList) {
            for (Integer integer : integers) {
                System.out.print(integer);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public int getLastLevelSize() {
        if (numberOfLevel > 0 && numberOfNodes > 0 && innerList.size() > 0) {
            return innerList.get(numberOfLevel - 1).size();
        }
        return -1;
    }
}
