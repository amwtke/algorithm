import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Test1 {
    public void main(String[] args) {
        System.out.println("This is Test!");
    }

    @Test
    public void test1() {
        test t = new test();
        assertThat(t.getInt(), is(1));
    }

    @Test
    public void recursive() {
        Clazz clazz = new Clazz();
        System.out.println(clazz.fabonacci(5));
    }

    @Test
    public void test_logicOp() {
        System.out.println(8>>>1);
    }

    public static class Clazz {
        public int fabonacci(int n) {
            if (n == 0) {
                return 1;
            }
            if (n == 1) {
                return 1;
            }
            if (n < 0) {
                return 0;
            }
            synchronized (this) {
                int i = fabonacci(n - 1) + fabonacci((n - 2));
                return i;
            }
        }
    }
}
