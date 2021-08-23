package Day5;

/**
 * @Classname HeapSort
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

            public Trie1(Node root) {
                this.root = new Node();//头节点
            }

            /**
             * 添加节点
             * @param word
             */
            public void insert(String word){
                if(word == null){
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
            }

            /**
             * 删除节点
             * @param word
             */
            public void delete (String word){

            }
        }
    }
}
