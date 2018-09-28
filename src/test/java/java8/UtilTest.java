package java8;

import org.junit.Test;

import java.util.Arrays;

public class UtilTest {
    int[] data = {4,12,1,3,5,7,9};

    @Test
    public void parallelSort(){
        Arrays.parallelSort(data);
        System.out.println(Arrays.toString(data));
    }

    @Test
    public void testCollectPrallel() {
        Arrays.parallelPrefix(data, Integer::sum);
        System.out.println(Arrays.toString(data));
    }
}
