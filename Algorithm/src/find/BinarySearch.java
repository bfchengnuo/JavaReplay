package find;

/**
 * Created by 冰封承諾Andy on 2019/3/2.
 * <p>
 * 二分查找
 */
public class BinarySearch {

    /**
     * 变体一：查找第一个值等于给定值的元素
     */
    public static int bsearchFirst(int[] a, int size, int value) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        if (low < size && a[low] == value) {
            return low;
        } else {
            return -1;
        }
    }

    public static int bsearchFirst2(int[] a, int size, int value) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (a[mid - 1] != value)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 变体二：查找最后一个值等于给定值的元素
     */
    public int bsearchLast(int[] a, int size, int value) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == size - 1) || (a[mid + 1] != value)) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * 变体三：查找第一个大于等于给定值的元素
     */
    public int bsearchGE(int[] a, int size, int value) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] >= value) {
                if ((mid == 0) || (a[mid - 1] < value)) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 变体四：查找最后一个小于等于给定值的元素
     */
    public int bsearchLE(int[] a, int size, int value) {
        int low = 0;
        int high = size - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else {
                if ((mid == size - 1) || (a[mid + 1] > value)) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
