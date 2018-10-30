package sort;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class SkipListTest {
    @Test
    public void test() {
        SkipList skipList = new SkipList();
        skipList.insert(10);
        skipList.insert(2);
        skipList.insert(30);
        skipList.insert(15);
        skipList.insert(20);
        skipList.insert(1);
        skipList.find(15);
        assertThat(skipList.find(10).getData(), is(10));
    }
}
