package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 冰封承諾Andy on 2019/2/27.
 * <p>
 * 两数之和
 * <p>
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p>
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 */
public class TwoSum1 {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;

        int[] ints = twoSumFinal(nums, target);
        for (int i : ints) {
            System.out.print(i + "  ");
        }
    }

    /**
     * hash 的创建耗时，可以使用数组来模拟 Map， arr[target] => index 更快
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSumFinal(int[] nums, int target) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (cache.containsKey(target - nums[i])) {
                return new int[]{i, cache.get(target - nums[i])};
            }
            cache.put(nums[i], i);
        }

        return null;
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int result = findNum(target - nums[i], nums, i);
            if (-1 != result) {
                return new int[]{i, result};
            }
        }
        return null;
    }

    public int findNum(int num, int[] nums, int index) {
        for (int i = 0; i < nums.length; i++) {
            if (i != index && num == nums[i]) {
                return i;
            }
        }

        return -1;
    }
}
