package sort;

/**
 * Created by 冰封承諾Andy on 2019/2/26.
 * 插入排序
 * 首先，我们将数组中的数据分为两个区间，已排序区间和未排序区间；
 * 初始已排序区间只有一个元素，就是数组的第一个元素。插入算法的核心思想是取未排序区间中的元素，在已排序区间中找到合适的插入位置将其插入，并保证已排序区间数据一直有序。
 * 重复这个过程，直到未排序区间中元素为空，算法结束。
 *
 * 对于一个给定的初始序列，移动操作的次数总是固定的。
 */
public class InsertionSort {
    // 插入排序，a 表示数组，n 表示数组大小
    public static void insertionSort(int[] arr) {
        int size = arr.length;
        if (size <= 1) return;

        for (int i = 1; i < size; i++) {
            // 要插入的数
            int value = arr[i];
            // 有序区间的末尾
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (arr[j] > value) {
                    arr[j + 1] = arr[j];  // 数据移动
                } else {
                    break;
                }
            }
            // 插入数据
            arr[j + 1] = value;
        }
    }

    public static void insertionSort1(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void insertionSort2(int[] arr) {
        for (int i = 1; i < arr.length; i++ ) {
            int temp = arr[i];
            int j = i - 1;

            for (; j >= 0 && arr[j] > temp; j-- ) {
                arr[j + 1] = arr[j];
            }
            arr[j + 1] = temp;
        }
    }
}
