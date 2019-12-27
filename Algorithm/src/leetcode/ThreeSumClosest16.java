package leetcode;

import java.util.Arrays;

/**
 * 三数之和
 *
 * https://leetcode-cn.com/problems/3sum-closest/
 * @author Created by 冰封承諾Andy on 2019/12/26.
 */
public class ThreeSumClosest16 {
    public static void main(String[] args) {
        // 2  0  82
        System.out.println(threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(threeSumClosest(new int[]{0,2,1,-3}, 1));
        System.out.println(threeSumClosest(new int[]{1, 2, 4, 8, 16, 32, 64, 128}, 82));
    }

    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int resultNeg = nums[i] + nums[l] + nums[r];
                if (Math.abs(target - result) > Math.abs(target - resultNeg)) {
                    result = resultNeg;
                }

                if (resultNeg > target) {
                    r--;
                } else if (resultNeg < target) {
                    l++;
                } else {
                    return target;
                }
            }
        }

        return result;
    }
}
