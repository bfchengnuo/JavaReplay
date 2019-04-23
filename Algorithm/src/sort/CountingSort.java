package sort;

/**
 * Created by 冰封承諾Andy on 2019/3/1.
 * <p>
 * 计数排序
 */
public class CountingSort {

    // 计数排序，arr 是数组，size 是数组大小。假设数组中存储的都是非负整数。
    public static void countingSort(int[] arr, int size) {
        if (size <= 1) return;

        // 查找数组中数据的范围
        int max = arr[0];
        for (int i = 1; i < size; ++i) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }

        // 申请一个计数数组 countArr，下标大小 [0,max]，初始化为 0
        int[] countArr = new int[max + 1];
        for (int i = 0; i <= max; ++i) {
            countArr[i] = 0;
        }

        // 计算每个元素的个数，放入 countArr 中
        for (int i = 0; i < size; ++i) {
            countArr[arr[i]]++;
        }

        // 依次累加
        for (int i = 1; i <= max; ++i) {
            countArr[i] = countArr[i - 1] + countArr[i];
        }

        // 临时数组 temp，存储排序之后的结果
        int[] temp = new int[size];
        // 计算排序的关键步骤
        for (int i = size - 1; i >= 0; --i) {
            int index = countArr[arr[i]] - 1;
            temp[index] = arr[i];
            countArr[arr[i]]--;
        }

        // 将结果拷贝给 arr 数组
        System.arraycopy(temp, 0, arr, 0, size);
    }
}
