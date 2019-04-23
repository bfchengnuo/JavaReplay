package sort;

/**
 * 快速排序
 *
 * 如果要排序数组中下标从 p 到 r 之间的一组数据，我们选择 p 到 r 之间的任意一个数据作为 pivot（分区点）。
 * 我们遍历 p 到 r 之间的数据，将小于 pivot 的放到左边，将大于 pivot 的放到右边，将 pivot 放到中间。经过这一步骤之后，数组 p 到 r 之间的数据就被分成了三个部分，前面 p 到 q-1 之间都是小于 pivot 的，中间是 pivot，后面的 q+1 到 r 之间是大于 pivot 的。
 * 当区间缩小为 1 的时候就可以认为它有序了。
 *
 * 不稳定、是原地排序算法
 */
public class QuickSort {

    // 快速排序，a是数组，n表示数组的大小
    public static void quickSort(int[] a, int n) {
        quickSortInternally(a, 0, n - 1);
    }

    // 快速排序递归函数，p,r为下标
    private static void quickSortInternally(int[] a, int p, int r) {
        if (p >= r) return;

        int q = partition(a, p, r); // 获取分区点
        quickSortInternally(a, p, q - 1);
        quickSortInternally(a, q + 1, r);
    }

    private static int partition(int[] a, int p, int r) {
        int pivot = a[r];
        int left = p;
        for (int right = p; right < r; ++right) {
            if (a[right] < pivot) {
                if (left == right) {
                    ++left;
                } else {
                    int tmp = a[left];
                    a[left++] = a[right];
                    a[right] = tmp;
                }
            }
        }

        // 交换 pivot，返回缩小后区间的最后一个（新 pivot）
        int tmp = a[left];
        a[left] = a[r];
        a[r] = tmp;

        System.out.println("i=" + left);
        return left;
    }
}