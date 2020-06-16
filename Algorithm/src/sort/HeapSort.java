package sort;

/**
 * Created by 冰封承諾Andy on 2019/3/7.
 * <p>
 * 堆排序
 * 堆排序是一种原地的、不稳定的、时间复杂度为 O(nlogn) 的排序算法;
 * 在实际的软件开发中，快速排序的性能要比堆排序好
 *
 * see: https://bfchengnuo.com/2019/04/03/%E7%AE%97%E6%B3%95%E4%B9%8B%E7%BE%8E-%E6%8E%92%E5%BA%8F/#%E5%A0%86%E6%8E%92%E5%BA%8F
 */
public class HeapSort {

    // n 表示数据的个数，数组 a 中的数据从下标 1 到 n 的位置。
    public static void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapify(a, k, 1);
        }
    }

    /**
     * 构建堆
     * @param a 数组
     * @param n size
     */
    private static void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 1; --i) {
            heapify(a, n, i);
        }
    }

    private static void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) maxPos = i * 2 + 1;
            if (maxPos == i) break;
            swap(a, i, maxPos);
            i = maxPos;
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
