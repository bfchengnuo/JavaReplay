package com.bfchengnuo.number;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 关于 BigDecimal 的日常使用
 *
 * 常用方法：
 *      求余数（%）： remainder
 *      相反数： negate
 *      加减乘除： add、subtract、multiply、divide
 *
 * 格式化： DecimalFormat
 *
 * 规则：
 *      BigDecimal.ROUND_HALF_UP： 遇到.5 的情况时往上近似，四舍五入
 *      BigDecimal.ROUND_HALF_DOWN： 向下取整
 *      ROUND_CEILING： 如果 BigDecimal 是正的，则做 ROUND_UP 操作；如果为负，则做 ROUND_DOWN 操作
 *      ROUND_DOWN： 从不在舍弃(即截断)的小数之前增加数字
 *      ROUND_FLOOR： 如果 BigDecimal 为正，则作 ROUND_UP ；如果为负，则作 ROUND_DOWN
 *
 * @author Created by 冰封承諾Andy on 2019/7/1.
 */
public class BigDecimalDemo {
    public static void main(String[] args) {
        // 构造方法优先使用 String，禁止使用 double 等
        BigDecimal bigDecimal = new BigDecimal("20");
        // 使用 valueOf 也是安全的，内部也是转换成 String 进行处理的
        BigDecimal bigDecimal2 = BigDecimal.valueOf(21.2D);

        System.out.println(bigDecimal.negate());

        DecimalFormat df = new DecimalFormat("######0.00");
        System.out.println(df.format(bigDecimal));
    }

    /**
     * 常用的四则运算工具类
     */
    private static class ArithUtil {

        // 除法运算默认精度
        private static final int DEF_DIV_SCALE = 10;

        private ArithUtil() {}

        /**
         * 精确加法
         */
        public static double add(double value1, double value2) {
            BigDecimal b1 = BigDecimal.valueOf(value1);
            BigDecimal b2 = BigDecimal.valueOf(value2);
            return b1.add(b2).doubleValue();
        }

        /**
         * 精确减法
         */
        public static double sub(double value1, double value2) {
            BigDecimal b1 = BigDecimal.valueOf(value1);
            BigDecimal b2 = BigDecimal.valueOf(value2);
            return b1.subtract(b2).doubleValue();
        }

        /**
         * 精确乘法
         */
        public static double mul(double value1, double value2) {
            BigDecimal b1 = BigDecimal.valueOf(value1);
            BigDecimal b2 = BigDecimal.valueOf(value2);
            return b1.multiply(b2).doubleValue();
        }

        /**
         * 精确除法 使用默认精度
         */
        public static double div(double value1, double value2) throws IllegalAccessException {
            return div(value1, value2, DEF_DIV_SCALE);
        }

        /**
         * 精确除法
         * @param scale 精度
         */
        static double div(double value1, double value2, int scale) throws IllegalAccessException {
            if(scale < 0) {
                throw new IllegalAccessException("精确度不能小于0");
            }
            BigDecimal b1 = BigDecimal.valueOf(value1);
            BigDecimal b2 = BigDecimal.valueOf(value2);
            // return b1.divide(b2, scale).doubleValue();
            return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
        }

        /**
         * 四舍五入
         * @param scale 小数点后保留几位
         */
        public static double round(double v, int scale) throws IllegalAccessException {
            return div(v, 1, scale);
        }

        /**
         * 比较大小
         */
        public static boolean equalTo(BigDecimal b1, BigDecimal b2) {
            if(b1 == null || b2 == null) {
                return false;
            }
            return 0 == b1.compareTo(b2);
        }
    }
}
