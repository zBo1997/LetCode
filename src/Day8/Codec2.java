package Day8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
public class Codec2 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        LinkedList<String> queue = new LinkedList<>();
        if (root == null) {
            return "[]";
        }
        preSer(root, queue);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (!queue.isEmpty()) {
            sb.append(queue.poll() + ",");
        }
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("[]")) {
            return null;
        }
        String[] split = data.substring(1, data.length() - 1).split(",");
        LinkedList prelist = new LinkedList(new ArrayList(Arrays.asList(split)));
        if (prelist == null || prelist.size() == 0) {
            return null;
        }
        return preb(prelist);
    }

    public void preSer(TreeNode root, LinkedList<String> queue) {
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
        if (value == null || value.equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(value));
        root.left = preb(prelist);
        root.right = preb(prelist);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));