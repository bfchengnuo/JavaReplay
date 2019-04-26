package leetcode;

/**
 * Created by 冰封承諾Andy on 2019/3/7.
 * <p>
 * 数字转罗马文
 * https://leetcode-cn.com/problems/integer-to-roman
 */
public class Integer2Roman12 {
    public static void main(String[] args) {
        System.out.println(intToRoman(3));  // III
        System.out.println(intToRoman(4)); // IV
        System.out.println(intToRoman(9));  // IX
        System.out.println(intToRoman(58));  // LVIII
        System.out.println(intToRoman(1994));  // MCMXCIV
    }

    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] reps = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(reps[i]);
            }
        }

        return sb.toString();
    }
}
