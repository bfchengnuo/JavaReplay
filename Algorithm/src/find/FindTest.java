package find;

/**
 * Created by 冰封承諾Andy on 2019/3/2.
 */
public class FindTest {
    public static void main(String[] args) {
        int[] data = {1, 2, 8, 10, 14, 63, 102};

        System.out.println(BinarySearch.bsearchFirst(data, data.length, 14));
    }
}
