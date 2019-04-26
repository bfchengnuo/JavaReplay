package leetcode;

/**
 * Created by 冰封承諾Andy on 2019/3/9.
 * <p>
 * Z 字形变化
 * https://leetcode-cn.com/problems/zigzag-conversion/
 */
public class ZigzagConversion6 {
    public static void main(String[] args) {
        // LCIRETOESIIGEDHN
        System.out.println(convert("LEETCODEISHIRING", 3));
        // LDREOEIIECIHNTSG
        System.out.println(convert("LEETCODEISHIRING", 4));
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;
        char[] rtnChar = new char[s.length()];
        char[] chars = s.toCharArray();
        int row = 0;
        int step = 1;

        int[] rowIndex = new int[numRows];
        for (int i = 0; i < chars.length; i++) {
            if (row != numRows - 1) {
                rowIndex[row + 1]++;
            }

            if (row == numRows - 1) {
                step = -1;
            } else if (row == 0) {
                step = 1;
            }

            row = row + step;
        }

        for (int i = 1; i < rowIndex.length; i++) {
            rowIndex[i] += rowIndex[i - 1];
        }

        row = 0;
        step = 1;
        for (char aChar : chars) {
            rtnChar[rowIndex[row]] = aChar;
            rowIndex[row]++;

            if (row == numRows - 1) {
                step = -1;
            } else if (row == 0) {
                step = 1;
            }

            row = row + step;
        }

        return String.valueOf(rtnChar);
    }

    public static String convertCommon(String s, int numRows) {
        if (numRows == 1) return s;
        int[] rowIdx = new int[numRows];
        char[] chars = new char[s.length()];
        int n = 0;
        int burketSize = numRows * 2 - 2;
        int burketNum = chars.length / burketSize;
        int rem = chars.length % burketSize;
        for (int i = 1; i < numRows; i++) {
            int flag = i == 1 ? 1 : 2;
            n = flag * burketNum + (rem >= i ? (1 + (burketSize - rem + 1 < i ? 1 : 0)) : 0);
            rowIdx[i] = rowIdx[i - 1] + n;
        }
        int flag = -1;
        int curRow = 0;
        for (char c : s.toCharArray()) {
            chars[rowIdx[curRow]] = c;
            rowIdx[curRow] = rowIdx[curRow] + 1;
            if (curRow == 0 || curRow == numRows - 1) flag = -flag;
            curRow += flag;
        }
        return new String(chars);
    }

    /**
     * 按行访问
     * 首先访问 行 0 中的所有字符，接着访问 行 1，然后 行 2，依此类推...
     * @param s
     * @param numRows
     * @return
     */
    public String convertOfficial(String s, int numRows) {

        if (numRows == 1) return s;

        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

}
