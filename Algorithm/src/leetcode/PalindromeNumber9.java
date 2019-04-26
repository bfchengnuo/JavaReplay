package leetcode;

/**
 * Created by 冰封承諾Andy on 2019/2/27.
 * <p>
 * 回文数
 * <p>
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * true： 121
 * false： 10   -121
 */
public class PalindromeNumber9 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(-121));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        char[] chars = String.valueOf(x).toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - 1 - i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 如果该数字是回文，其后半部分反转后应该与原始数字的前半部分相同。
     * 如果我们把最后一位数字乘以10，再加上倒数第二位数字，就得到了我们想要的反转后的数字。 如果继续这个过程，我们将得到更多位数的反转数字。
     * 当原始数字小于反转后的数字时，就意味着我们已经处理了一半位数的数字。
     *
     * @param x
     * @return
     */
    public static boolean isPalindromeOfficial(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int t = 0;
        while (x > t) {
            t = t * 10 + x % 10;
            x /= 10;
        }
        // 当数字长度为奇数时，我们可以通过 t/10 去除处于中位的数字。
        return x == t || x == t / 10;
    }

}
