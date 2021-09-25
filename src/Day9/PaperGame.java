package Day9;

/**
 * @Classname PaperGame
 * @Description 【题目】
 * 请把一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，
 * 折痕突起的方向指向纸条的背面。如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，
 * 从上到下依次是下折痕、下折痕和上折痕。给定一个输入参数N，代表纸条都从下边向上方连续对折N次，
 * 请从上到下打印所有折痕的方向。
 * 例如：N=1时，打印：
 * down
 * N=2时，打印：
 * down
 * down
 * up
 * 可以自己拿一张纸来折折看，这是一个二叉树的问题，但这里只是利用二叉树的原理，并不是真的构造一棵树，采用递归的方法来打印
 * <p>
 * 解释：
 * 这道题的规律就是 采用了 二叉树的思想，每一次折痕，相对于上一次折痕基础上放出现 “凹” 下方出现“凸”折痕，是一个有规律的二叉树
 * 规律是 一个 一直是一个凹凸的二叉树，左边是“凹” 右边是“凸”
 * @Date 2021/9/6 22:16
 * @Created by ZhuBo
 */
public class PaperGame {

    /**
     * @param i    节点的层数
     * @param N
     * @param down
     */
    public static void printProcess(int i, int N, boolean down) {
        if (i > N) {
            return;
        }
        printProcess(i + 1, N, true);//凹节点
        System.out.println(down ? "凹" : "凸");
        printProcess(i + 1, N, false);//凸节点
    }

    public static void play(int n) {
        printProcess(1, n, true);
    }

    public static void main(String[] args) {
        int n = 3;
        play(n);
    }
}
