package leetcode;

/**
 * Created by 冰封承諾Andy on 2019/3/4.
 * <p>
 * 寻找两个有序数组中的中位数
 * https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 */
public class Median4TwoSortedArrays4 {
    public static void main(String[] args) {
        int[] nums1 = {1, 3};
        int[] nums2 = {2};

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};

        System.out.println(findMedianSortedArrays(nums1, nums2));
        System.out.println(findMedianSortedArrays(nums3, nums4));
        System.out.println(findMedianSortedArrays(new int[]{}, new int[]{1, 2, 3}));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums = new int[nums1.length + nums2.length];
        boolean flag = true;
        if (nums1.length == 0) {
            nums = nums2;
            flag = false;
        } else if (nums2.length == 0) {
            nums = nums1;
            flag = false;
        }

        int p1 = 0;
        int p2 = 0;
        int i = 0;
        while (flag) {
            if (nums1[p1] > nums2[p2]) {
                nums[i++] = nums2[p2++];
            } else {
                nums[i++] = nums1[p1++];
            }

            if (p1 == nums1.length) {
                // p2 全转移
                while (p2 != nums2.length) {
                    nums[i++] = nums2[p2++];
                }
                break;
            }
            if (p2 == nums2.length) {
                // p1 全转移
                while (p1 != nums1.length) {
                    nums[i++] = nums1[p1++];
                }
                break;
            }
        }

        if ((nums.length & 1) == 0) {
            return (nums[nums.length / 2] + nums[nums.length / 2 - 1]) / 2D;
        }
        return nums[nums.length / 2];
    }


    public static double findMedianSortedArraysBlog(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, left = (m + n + 1) / 2, right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    private static int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) return nums2[j + k - 1];
        if (j >= nums2.length) return nums1[i + k - 1];
        if (k == 1) return Math.min(nums1[i], nums2[j]);
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }

    /**
     * 复杂度要求，官方
     * <p>
     * 难就难在要在两个未合并的有序数组之间使用二分法
     *
     * @param A
     * @param B
     * @return
     */
    public static double findMedianSortedArraysOfficial(int[] A, int[] B) {
        int aLen = A.length;
        int bLen = B.length;
        // 保证 B 的长度大于等于 A 的长度
        // 否则，数组交换、长度交换
        if (aLen > bLen) {
            int[] temp = A;
            A = B;
            B = temp;

            int tmp = aLen;
            aLen = bLen;
            bLen = tmp;
        }

        int iMin = 0, iMax = aLen, halfLen = (aLen + bLen + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j - 1] > A[i]) {
                iMin = i + 1; // i is too small
            } else if (i > iMin && A[i - 1] > B[j]) {
                iMax = i - 1; // i is too big
            } else { // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((aLen + bLen) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == aLen) {
                    minRight = B[j];
                } else if (j == bLen) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
