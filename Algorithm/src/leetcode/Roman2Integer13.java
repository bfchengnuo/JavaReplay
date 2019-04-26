package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 冰封承諾Andy on 2019/3/1.
 *
 * 罗马数转数字
 * https://leetcode-cn.com/problems/roman-to-integer/
 */
public class Roman2Integer13 {
    public static void main(String[] args) {
        // 3  4  9  58  1994
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));

    }

    public static int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>(7);
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        Integer last = 0;
        Integer sum = 0;
        for (char c : s.toCharArray()) {
            Integer now = map.get(c);
            if (last != 0 && now > last) {
                sum += now - last - last;
            } else {
                sum += now;
            }
            if (sum < 0 || sum >= 4000) {
                throw new RuntimeException("越界");
            }
            last = now;
        }
        return sum;
    }
}
