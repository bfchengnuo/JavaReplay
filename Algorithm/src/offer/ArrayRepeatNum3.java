package offer;

/**
 * Created by 冰封承諾Andy on 2019/3/25.
 *
 * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。
 * 请找出数组中任意一个重复的数字。
 *
 * {2, 3, 1, 0, 2, 5}  opt: 2
 *
 * 对于这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上进行求解。
 */
public class ArrayRepeatNum3 {

    public static void main(String[] args) {
        System.out.println(duplicate(new int[]{2, 3, 1, 0, 2, 5}));
        System.out.println(duplicate(new int[]{2, 3, 1, 0, 5, 5}));
    }

    public static int duplicate(int[] nums) {
        int length = nums.length;
        if (length <= 0)
            return -1;
        for (int i = 0; i < length; i++) {
            // while
            if (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                swap(nums, i, nums[i]);
            }
        }
        return -1;
    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
