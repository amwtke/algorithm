package dp;

import dp.YH_triangle_path.ConvertArryToYHTriangle;
import dp.YH_triangle_path.ShortestPath;
import dp.YH_triangle_path.YHTriangle;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DpTest {
    @Test
    public void testDp() throws Exception {
        ShortestPath shortestPath = new ShortestPath();
        YHTriangle yhTriangle = ConvertArryToYHTriangle.makeLargeYHTriangle(50);
//        yhTriangle.addAll(5, 7, 8, 2, 3, 4, 4, 9, 6, 1, 2, 7, 9, 4, 5);
        long begin = System.currentTimeMillis();
        Integer result = shortestPath.DPWay(yhTriangle);
        long end = System.currentTimeMillis();
        System.out.println("total time:" + (end - begin));
//        assertThat(result, is(20));
        System.out.println("DP result:" + result);
        begin = System.currentTimeMillis();
        Integer result2 = shortestPath.traceBackWay(yhTriangle);
        end = System.currentTimeMillis();
        System.out.println("tb result:" + result2);
        assertThat(result2.equals(result), is(true));
        System.out.println("total time:" + (end - begin));
    }

    @Test
    public void testMackYHTriangle() {
        YHTriangle yhTriangle = ConvertArryToYHTriangle.makeLargeYHTriangle(100);
        yhTriangle.print();
    }
}
