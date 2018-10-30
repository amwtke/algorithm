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
}
