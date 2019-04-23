package tree;

/**
 * Created by 冰封承諾Andy on 2019/3/7.
 * <p>
 * 堆的一些简单实现
 */
public class Heap {
    // 数组，从下标 1 开始存储数据
    private int[] a;
    // 堆可以存储的最大数据个数
    private int n;
    // 堆中已经存储的数据个数
    private int count;

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public void insert(int data) {
        // 堆满了
        if (count >= n) return;
        ++count;
        a[count] = data;
        int i = count;
        // 自下往上堆化
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            // swap() 函数作用：交换下标为 i 和 i/2 的两个元素
            swap(a, i, i / 2);
            i = i / 2;
        }
    }

    public void removeMax() {
        // 堆中没有数据
        if (count == 0) return;
        a[1] = a[count];
        --count;
        heapify(a, count, 1);
    }

    // 自上往下堆化
    private void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) maxPos = i * 2;
            if (i * 2 + 1 <= n && a[maxPos] < a[i * 2 + 1]) maxPos = i * 2 + 1;
            if (maxPos == i) break;
            swap(a, i, maxPos);
            i = maxPos;
        }
    }
}
