package sort;

/**
 * Created by 冰封承諾Andy on 2019/2/26.
 */
public class RunTest {
    public static void main(String[] args) {
        int[] data = {1, 2, 8, 10, 6, 74, 14, -1, -7, -45, 63, 102, 78, 44, 56, 55, 45, 108, 145, 78, 54, 3, 0, 74, 9, -5};
        int[] dataAbs = {1, 2, 8, 10, 6, 74, 14, 63, 102, 78, 44, 56, 55, 45, 108, 145, 78, 54, 3, 0, 74, 9};

        // 冒泡排序
        // BubbleSort.bubbleSort(data);

        // 插入排序
        // InsertionSort.insertionSort(data);

        // 选择排序
        // SelectionSort.selectionSort(data);

        // 归并排序
        // MergeSort.mergeSort(data, data.length);

        // 计数排序
        // CountingSort.countingSort(dataAbs, dataAbs.length);

        // 堆排序, 需要空出第一个来
        // HeapSort.sort(dataAbs, dataAbs.length - 1);

        // 希尔排序
        ShellSort.sort(data);

        for (int i : data) {
            System.out.printf("%d   ", i);
        }
    }
}
