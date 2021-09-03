package Day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringJoiner;

public class Codec {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        LinkedList<String> queue = new LinkedList<>();
        if (root == null) {
            return "[]";
        }
        preSer(root, queue);
        StringJoiner sj = new StringJoiner(",", "[", "]");
        while (!queue.isEmpty()) {
            sj.add(queue.poll());
        }
        return sj.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] split = data.substring(1, data.length() - 1).split(",");
        LinkedList prelist = new LinkedList(new ArrayList(Arrays.asList(split)));
        if (prelist == null || prelist.size() == 0) {
            return null;
        }
        return preb(prelist);
    }

    public static void preSer(TreeNode root, LinkedList<String> queue) {
        if (root == null) {
            queue.add(null);//不要忘记空对象
        } else {
            queue.add(String.valueOf(root.val));
            preSer(root.left, queue);
            preSer(root.right, queue);
        }
    }

    public static TreeNode preb(LinkedList<String> prelist) {
        String value = prelist.poll();
        if (value == null) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(value));
        root.left = preb(prelist);
        root.right = preb(prelist);
        return root;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        String serialize = serialize(treeNode);
        System.out.println(serialize);
    }
}