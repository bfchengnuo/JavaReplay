package com.bfchengnuo.uselibraries.apache.commons;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Created by 冰封承諾Andy on 2019/5/7.
 *
 * 测试 Apache 的 commons 库中的 ArrayUtils 工具使用
 */
public class ArrayUtilsTest {
    public static void main(String[] args) {
        int[] nums1 = { 1, 2, 3, 4, 5, 6 };
        // 通过常量创建新数组
        int[] nums2 = ArrayUtils.EMPTY_INT_ARRAY;

        // 比较两个数组是否相等，不过 isEquals 已被废弃，建议使用 Objects.deepEquals
        System.out.println("ArrayUtils.isEquals = " + ArrayUtils.isEquals(nums1, nums2));
        System.out.println("Objects.deepEquals = " + Objects.deepEquals(nums1, nums2));
        System.out.println();

        // 输出数组,第二参数为数组为空时代替输出
        System.out.println("ArrayUtils.toString = " + ArrayUtils.toString(nums1, "array is null"));
        System.out.println("ArrayUtils.toString = " + ArrayUtils.toString(null, "array is null"));
        System.out.println();

        // 克隆新数组,注意此拷贝为深拷贝
        int[] nums3 = ArrayUtils.clone(nums1);
        System.out.println("ArrayUtils.clone(nums1) = " + Arrays.toString(nums3));
        System.out.println();

        // 截取数组
        System.out.println("ArrayUtils.subarray(nums1, 1, 2) = " +
                Arrays.toString(ArrayUtils.subarray(nums1, 1, 2)));
        System.out.println();

        // 判断两个数组长度是否相等
        System.out.println("ArrayUtils.isSameLength = " + ArrayUtils.isSameLength(nums1, nums2));
        System.out.println();

        // 判断两个数组类型是否相等,注意 int 和 Integer 比较时不相等;
        // 比较的是 className
        System.out.println("ArrayUtils.isSameType = " + ArrayUtils.isSameType(nums1, nums2));
        System.out.println();

        // 反转数组
        ArrayUtils.reverse(nums1);
        System.out.println("反转数组：" + Arrays.toString(nums1));
        System.out.println();

        // 查找数组元素位置
        System.out.println("ArrayUtils.indexOf(nums1, 5) = " + ArrayUtils.indexOf(nums1, 5));
        System.out.println();

        // 查找数组元素最后出现位置
        System.out.println("ArrayUtils.lastIndexOf(nums1, 4) = " + ArrayUtils.lastIndexOf(nums1, 4));
        System.out.println();

        // 查找元素是否存在数组中
        System.out.println("ArrayUtils.contains(nums1, 2) = " + ArrayUtils.contains(nums1, 2));
        System.out.println();

        // 将基本数组类型转为包装类型
        Integer[] nums4 = ArrayUtils.toObject(nums1);
        System.out.println("基本类型转包装类型 ArrayUtils.toObject = " + Arrays.toString(nums4));
        System.out.println();

        // 判断是否为空, length=0 或 null 都属于
        System.out.println("ArrayUtils.isEmpty = " + ArrayUtils.isEmpty(nums1));
        System.out.println();

        // 并集操作,合并数组
        System.out.println("ArrayUtils.addAll(nums1, 100, 101) = " + Arrays.toString(ArrayUtils.addAll(nums1, 100, 101)));
        System.out.println();

        // 增加元素,在下标5中插入10,注意此处返回是新数组
        // 已过时，被 insert 取代
        int[] addArr = ArrayUtils.add(nums1, 5, 1111);
        int[] insertArr = ArrayUtils.insert(5, nums1, 1111);
        System.out.println("插入、增加元素 ArrayUtils.insert = " +
                Arrays.toString(addArr) + "\t" + Arrays.toString(insertArr));
        System.out.println();

        // 删除指定位置元素,注意返回新数组,删除元素后面的元素会前移,保持数组有序
        System.out.println("ArrayUtils.remove(nums1, 5) = " + Arrays.toString(ArrayUtils.remove(nums1, 5)));
        System.out.println();

        // 删除数组中值为 1 的元素,以值计算不以下标
        System.out.println("ArrayUtils.removeElement(nums1, 1) = " + Arrays.toString(ArrayUtils.removeElement(nums1, 1)));
    }
}
