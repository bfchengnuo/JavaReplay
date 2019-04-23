package sort;

/**
 * 归并排序
 *
 * 分治思想，将数组分为两半，使用递归思想进行分别排序，最后合并在一起
 *
 * 然后重点说下将两个已经有序的数组拼装起来，我们可以先申请一个临时数组 tmp，大小与 A[p…r] 相同。我们用两个游标 i 和 j，分别指向 A[p…q] 和 A[q+1…r] 的第一个元素。
 * 比较这两个元素 A[i] 和 A[j]，如果 `A[i]<=A[j]`，我们就把 A[i] 放入到临时数组 tmp，并且 i 后移一位，否则将 A[j] 放入到数组 tmp，j 后移一位。
 * 直到其中一个子数组中的所有数据都放入临时数组中，再把另一个数组中的数据依次加入到临时数组的末尾，这个时候，临时数组中存储的就是两个子数组合并之后的结果了。最后再把临时数组 tmp 中的数据拷贝到原数组 A[p…r] 中。
 *
 * 稳定、不是原地排序算法
 */
public class MergeSort {

    // 归并排序算法, a是数组，n表示数组大小
    public static void mergeSort(int[] a, int n) {
        mergeSortInternally(a, 0, n - 1);
    }

    // 递归调用函数
    private static void mergeSortInternally(int[] a, int p, int r) {
        // 递归终止条件
        if (p >= r) return;

        // 取p到r之间的中间位置q,防止（p+r）的和超过int类型最大值
        int q = p + (r - p) / 2;
        // 分治递归
        mergeSortInternally(a, p, q);
        mergeSortInternally(a, q + 1, r);

        // 将A[p...q]和A[q+1...r]合并为A[p...r]
        merge(a, p, q, r);
    }

    private static void merge(int[] arr, int s1, int middle, int arrEnd) {
        int start1 = s1;
        int start2 = middle + 1;

        // 申请一个大小跟a[p...r]一样的临时数组
        int[] tmp = new int[arrEnd - s1 + 1];
        // 临时数组的索引
        int k = 0;

        while (start1 <= middle && start2 <= arrEnd) {
            if (arr[start1] <= arr[start2]) {
                tmp[k++] = arr[start1++];
            } else {
                tmp[k++] = arr[start2++];
            }
        }

        // 判断哪个子数组中有剩余的数据，默认设为第一个剩余
        int start = start1;
        int end = middle;
        if (start2 <= arrEnd) {
            // 改变为第二个剩余
            start = start2;
            end = arrEnd;
        }

        // 将剩余的数据拷贝到临时数组tmp
        while (start <= end) {
            tmp[k++] = arr[start++];
        }

        // 将tmp中的数组拷贝回a[p...r]
        for (start1 = 0; start1 <= arrEnd - s1; ++start1) {
            arr[s1 + start1] = tmp[start1];
        }
    }

}