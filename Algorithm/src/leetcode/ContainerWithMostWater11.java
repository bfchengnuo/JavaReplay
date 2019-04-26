package leetcode;

/**
 * Created by 冰封承諾Andy on 2019/3/15.
 * <p>
 * 盛最多水的容器
 * https://leetcode-cn.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater11 {
    public static void main(String[] args) {
        // 49
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
    }

    public static int maxArea(int[] height) {
        int area = 0, left = 0, right = height.length - 1;

        while (left < right) {
            area = Math.max(area, Math.min(height[left], height[right]) * (right - left));

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return area;
    }
}
