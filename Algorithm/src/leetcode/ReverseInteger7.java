package leetcode;

/**
 * Created by 冰封承諾Andy on 2019/2/27.
 * <p>
 * 整数反转
 * <p>
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 123 -> 321
 * -123 -> -321
 * 120 -> 21
 * <p>
 * 范围为 [−2^31,  2^31 − 1]。
 */
public class ReverseInteger7 {
    public static void main(String[] args) {
        // 2^31-1=2147483647,  -2^31=-2147483648
        // System.out.println(2 << 30);
        // System.out.println(Integer.MAX_VALUE);

        System.out.println(reverse(123));
        System.out.println(reverse(-123));
        System.out.println(reverse(120));

        System.out.println(reverseFinal(123));
        System.out.println(reverseFinal(-123));
        System.out.println(reverseFinal(120));
    }

    private static int reverseFinal(int x) {
        long result = 0;
        while (x != 0) {
            result = result * 10 + x % 10;
            x /= 10;
        }

        return result == (int) result ? (int) result : 0;
    }

    public static int reverse(int x) {
        String fh = "";
        if (x < 0) {
            x = Math.abs(x);
            fh = "-";
        }
        char[] s = String.valueOf(x).toCharArray();
        char[] rs = new char[s.length];
        for (int i = s.length - 1, j = 0; i >= 0; i--) {
            rs[j++] = s[i];
        }

        try {
            return Integer.valueOf(fh + String.valueOf(rs));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /**
     * 官方推荐答案
     * @param x
     * @return
     */
    public int reverseOfficial(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = rev * 10 + pop;
        }
        return rev;
    }
}
