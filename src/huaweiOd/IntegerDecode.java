package huaweiOd;

import java.util.Scanner;

public class IntegerDecode {

    static char[] charArray =
        {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'K', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z'};

    private static void solve(int number) {
        String numStr = Integer.toBinaryString(number);
        boolean statu;
        StringBuilder stringBuilder = new StringBuilder();
        // 这里需要先先找到低7位
        while (0 < numStr.length()) {
            if (7 < numStr.length()) {
                statu = true;
                // 这里2进制不足7位时补0
            } else {
                statu = false;
                int midIdx = 7 - numStr.length();
                for (int j = 0; j < midIdx; ++j) {
                    numStr = "0" + numStr;
                }
            }
            String subStr = numStr.substring(numStr.length() - 7);
            numStr = numStr.substring(0, numStr.length() - 7);
            if (statu) {
                subStr = "1" + subStr;
            } else {
                subStr = "0" + subStr;
            }
            String resultStr = Integer.toHexString(Integer.parseInt(subStr, 2));
            // 这里16进制不足2位补0
            if (2 > resultStr.length()) {
                resultStr = "0" + resultStr;
            }
            for (int j = 0; j < resultStr.length(); ++j) {
                char c = resultStr.charAt(j);
                if (c <= 'z' && c >= 'a') {
                    c = charArray[c - 'a'];
                }
                stringBuilder.append(c);
            }
        }
        // 每个字符如果是小写要转成大写
        System.out.println(stringBuilder);

    }

    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            int number = scan.nextInt();
            if (number < 0) {
                System.out.println("input error");
                return;
            }
            solve(number);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("input error");
        }
    }
}

