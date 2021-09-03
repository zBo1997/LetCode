package Day5;

/**
 * @Classname TineTree
 * @Description 前缀数
 * @Date 2021/8/22 14:10
 * @Created by ZhuBo
 */
public class TineTree {

    public static class Node{
        private int pass;

        private int end;

        private Node[] next;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.next = new Node[26];//26长度的数组，表示不同的方向的数
        }

        public static class Trie1{
            /**
             * 从根节点开始出发
             */
            private Node root;

            public Trie1() {
                this.root = new Node();//头节点
            }

            /**
             * 添加节点
             * @param word
             */
            public void insert(String word){
                if(word == null){
                    return;
                }
                    char[] charArray = word.toCharArray();
                    Node node = root;
                    node.pass ++; //头节点的经过次数自增 ，记录一次
                    int index = 0 ;
                    for (int i = 0; i < charArray.length; i++) {
                        index = charArray[i] - 'a';//当前字符减去 a 标识当签字字符所对应路径的的节点
                        if(node.next[index] == null){
                            node.next[index] = new Node();
                        }
                        node = node.next[index];
                        node.pass ++;
                    }
                    node.end ++;
            }

            /**
             * 删除节点
             * @param word
             */
            public void delete (String word){
                if(search(word) < 0) {
                    return ;
                }
                char[] chars = word.toCharArray();
                int path = 0;
                Node node = root;//从跟节点开始
                node.pass--;
                for (int i = 0; i < chars.length; i++) {
                    path = chars[i] - 'a';
                    if(--node.next[path].pass == 0){
                        //如已此节点的父节的pass删除过后已经是0，
                        // 那么后续的节点就不可能再有字符出现。直接删除交给JVM来进行内存回收
                        node.next[path] = null;//制空
                        return;
                    }
                    node = node.next[path];
                }
                node.end--;
            }

            /**
             * 查找此字符串出现的次数
             * @param word
             * @return
             */
            public int search(String word){
                if(word == null){
                    return 0;
                }
                char[] chars = word.toCharArray();
                int path = 0;
                Node node = root;
                for (int i = 0; i < chars.length; i++) {
                    path = chars[i] - 'a';//找到对应的位置
                    if(node.next[path] == null){
                        return 0;
                    }
                    node = node.next[path];//转到下一个姐弟啊
                }
                return node.end;
            }
        }
    }

    public static void main(String[] args) {
        Node.Trie1 trie1 = new Node.Trie1();
        trie1.insert("abc");
        trie1.insert("abc");
        trie1.insert("bc");
        trie1.insert("abc");
        trie1.insert("c");
        trie1.delete("bc");
        System.out.println(trie1.search("bc"));

        System.out.println((int)'你');
    }
}
