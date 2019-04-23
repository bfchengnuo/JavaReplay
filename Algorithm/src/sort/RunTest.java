package sort;

/**
 * Created by 冰封承諾Andy on 2019/2/26.
 */
public class RunTest {
    public static void main(String[] args) {
        int[] data = {1, 2, 8, 10, 6, 74, 14, -1, -7, -45, 63, 102, 78, 44, 56, 55, 45, 108, 145, 78, 54, 3, 0, 74, 9, -5};
        int[] dataAbs = {-1, 1, 2, 8, 10, 6, 74, 14, 63, 102, 78, 44, 56, 55, 45, 108, 145, 78, 54, 3, 0, 74, 9};

        // BubbleSort.bubbleSort(data);
        // InsertionSort.insertionSort(data);
        // SelectionSort.selectionSort(data);
        // MergeSort.mergeSort(data, data.length);
        // CountingSort.countingSort(dataAbs, dataAbs.length);
        // 需要空出第一个来
        HeapSort.sort(dataAbs, dataAbs.length - 1);

        for (int i : dataAbs) {
            System.out.printf("%d   ", i);
        }
    }
}
