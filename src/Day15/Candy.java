package Day15;

/**
 * 分发糖果 n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。 你需要按照以下要求，给这些孩子分发糖果： 每个孩子至少分配到 1 个糖果。 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 */
public class Candy {

    public static void main(String[] args) {
        int[] test = {1,2,87,87,87,2,1};
        System.out.println(candy(test));
    }

    public static int candy(int[] ratings) {
        int[] index = new int[ratings.length];
        int num = ratings.length;
        for (int i = 1; i < ratings.length; i += 2) {
            int left_range_cur = i - 1;
            int right_range_cur = i + 1;
            int left_range = ratings[left_range_cur];
            int mid_range = ratings[i];
            int right_range = ratings[right_range_cur];
                if (left_range > mid_range && index[left_range_cur] != 1) {
                    num += 1;
                    index[left_range_cur] += 1;
                }
                if (mid_range > left_range && index[i] != 1) {
                    num += 1;
                    index[i] += 1;
                }
                if (right_range > mid_range && index[right_range_cur] != 1) {
                    num += 1;
                    index[right_range_cur ] += 1;
                }
                if (mid_range > right_range && index[i] != 1) {
                    num += 1;
                    index[i] += 1;
                }

        }

        return num;
    }
}
