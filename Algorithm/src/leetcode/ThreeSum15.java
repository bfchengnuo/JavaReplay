package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和
 * https://leetcode-cn.com/problems/3sum/
 *
 * list.sort(Comparator.comparingInt(x -> x));
 * @author Created by 冰封承諾Andy on 2019/12/20.
 */
public class ThreeSum15 {
    public static void main(String[] args) {
        System.out.println(threeSum(new int[]{0,0,0,0}));
        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    /**
     * 超时.....
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ss = new ArrayList<>();
        Arrays.sort(nums);

        int t,s;
        for (int i = 0; i < nums.length - 2; i++) {
            // 跳过可能重复的
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            t = i + 1;
            s = t + 1;

            while (t < nums.length - 1) {
                // 跳过可能重复的
                if (t > i + 1 && nums[t] == nums[t - 1]) {
                    t++;
                    s = t + 1;
                    continue;
                }
                if (s > t + 1 && nums[s] == nums[s - 1]) {
                    s++;
                    if (s == nums.length) {
                        t++;
                        s = t + 1;
                    }
                    continue;
                }

                if (nums[i] + nums[t] + nums[s] == 0) {
                    ss.add(Arrays.asList(nums[i], nums[t], nums[s]));
                }

                s++;
                if (s == nums.length) {
                    t++;
                    s = t + 1;
                }
            }
        }

        return ss;
    }


    public static List<List<Integer>> threeSumExt(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ls = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            // 跳过可能重复的答案
            if (i == 0 || nums[i] != nums[i - 1]) {

                int l = i + 1, r = nums.length - 1, sum = 0 - nums[i];
                while (l < r) {
                    if (nums[l] + nums[r] == sum) {
                        ls.add(Arrays.asList(nums[i], nums[l], nums[r]));
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        l++;
                        r--;
                    } else if (nums[l] + nums[r] < sum) {
                        // 跳过重复值
                        while (l < r && nums[l] == nums[l + 1]) {
                            l++;
                        }
                        l++;
                    } else {
                        while (l < r && nums[r] == nums[r - 1]) {
                            r--;
                        }
                        r--;
                    }
                }
            }
        }
        return ls;
    }
}
