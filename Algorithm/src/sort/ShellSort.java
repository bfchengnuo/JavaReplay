package sort;

/**
 * 希尔排序
 * 也称递减增量排序算法，是插入排序的一种更高效的改进版本。
 * 希尔排序是非稳定排序算法。
 *
 * 为了解决插入排序在大规模乱序情况下插入慢的问题，设立了一个 h，或者说起始点（步长）；
 * 首先按照 h 来划分区域（确定起始点），然后与左侧每个相隔 h 的元素进行排序。
 * 完毕后缩小 h 的范围，再次进行，直到 h = 1 时变为插入排序；
 * 可以保证最终的 h = 1 排序移动不会超过 h，避免大规模的移动损耗性能。
 * 小数组、部分有序，很适合插入，数组越大，优势越明显。
 *
 * @author 冰封承諾Andy
 * @date 2020/6/15
 */
public class ShellSort {
    public static void sort(int[] data) {
        int len = data.length;
        int h = 1;
        // 确定 h
        while (h < len / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            // 将数组变为 h 有序
            for (int i = h; i < len; i++) {
                // 将 data[i] 插入到合适的 data[i-h], data[i-2h]... 中
                for (int j = i; j >= h && data[j] < data[j - h]; j -= h) {
                    exch(data, j, j-h);
                }
            }
            h /= 3;
        }
    }

    private static void exch(int[] arr, int index1, int index2) {
        arr[index1] = arr[index1] ^ arr[index2];
        arr[index2] = arr[index1] ^ arr[index2];
        arr[index1] = arr[index1] ^ arr[index2];
    }
}
