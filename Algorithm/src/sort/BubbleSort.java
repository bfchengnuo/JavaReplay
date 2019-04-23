package sort;

/**
 * Created by 冰封承諾Andy on 2019/2/26.
 * 冒泡排序
 * 它重复地走访过要排序的数列，一次比较(相邻)两个元素，如果他们的顺序错误就把他们交换过来。
 * 走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。
 * 这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端
 * <p>
 * 对于包含大量的元素的数列排序是很没有效率的
 */
public class BubbleSort {

    public static void bubbleSort(int[] arr) {
        int size = arr.length;
        if (size <= 1) return;

        for (int i = 0; i < size - 1; i++) {
            // 提前退出冒泡循环的标志位
            boolean flag = false;
            for (int j = 0; j < size - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    // 表示有数据交换
                    flag = true;
                }
            }
            // 没有数据交换，提前退出
            if (!flag) break;
        }
    }


    public static void bubbleSort1(int[] data) {
        int temp;
        for (int i = 0; i < data.length - 1; i++) {
            // -1 避免越界
            for (int j = 0; j < data.length - 1 - i; j++) {
                if (data[j] > data[j + 1]) {
                    temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡的第二种形式（还可以把上面的改为 i-- 的形式，j < x）
     */
    public static void bubbleSort2(int[] data) {
        int temp;
        boolean flag;
        do {
            flag = false;
            for (int i = 0; i < data.length - 1; i++) {
                if (data[i] > data[i + 1]) {
                    temp = data[i];
                    data[i] = data[i + 1];
                    data[i + 1] = temp;
                    flag = true;  // 只要有交换就说明还没排序完
                }
            }
        } while (flag);
    }
}
