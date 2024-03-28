package huaweiOd;

import java.util.Scanner;

//最少监控器
//在一长方形停车场内，每个车位上方都有对应监控器，
// 当且仅当在当前车位或者前后左右四个方向任意一个车位范围停车时，监控器才需要打开，
// 给出某一时刻停车场的停车分布，请统计最少需要打开多少个监控器。
public class TVCount {

    //输入的行数 和  输入的列数
    static int rowCount, colCount; // 行数和列数
    //使用一个二维数组实现画布，我们要在这个画布上画出停车场
    static int[][] grid = new int[25][25];

    public static void main(String[] args) {
        drawPostion();

        //设置结果
        int result = 0;
        for (int row = 1; row <= rowCount; row++) {
            for (int col = 1; col <= colCount; col++) {
                if (grid[row][col] == 1) {//如果当前位置停车 直接+1
                    result++;
                }
                //判断相邻的停车位是否存在车
                //首先列举出 如何判断相邻车位
                if (row > 1 && grid[row - 1][col] == 1 ||  //上方
                    col > 1 && grid[row][col - 1] == 1 ||  //左侧
                    col < colCount && grid[row][col - 1] == 1 || //右侧
                    row < rowCount && grid[row + 1][col] == 1 ||  //下方
                    grid[row][col] == 1) {
                    result++;
                }
            }
        }
        System.out.println(result);

    }

    /**
     * 画出位置
     */
    private static void drawPostion() {
        Scanner scanner = new Scanner(System.in);
        rowCount = scanner.nextInt();
        colCount = scanner.nextInt();
        for (int row = 1; row <= rowCount; row++) {
            for (int col = 1; col <= colCount; col++) {
                //每次输入都会给出对应的这一行车位是否停车
                grid[row][col] = scanner.nextInt();
            }
        }
    }
}
