package Day12;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * @Classname LowestLexicography
 * @Description 并查集学习 练习题
 * @Date 2021/12/22 17：52
 * @Created by ZhuBo
 */
public class StudentUnIonSet_Test {


    static class Human<V> {
        Integer arg1;
        Integer arg2;
        Integer arg3;
        V type;

        public Human(Integer arg1, Integer arg2, Integer arg3, V type) {
            this.arg1 = arg1;
            this.arg2 = arg2;
            this.arg3 = arg3;
            this.type = type;
        }

        public void setType(V type) {
            this.type = type;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Human)) return false;
            Human<?> human = (Human<?>) o;
            return Objects.equals(arg1, human.arg1) && Objects.equals(arg2, human.arg2) && Objects.equals(arg3, human.arg3) && Objects.equals(type, human.type);
        }

        @Override
        public int hashCode() {
            return Objects.hash(arg1, arg2, arg3, type);
        }
    }

    static class HumanSet {

        /**
         * 存放当前节点 Key 是 Key 的类型
         */
        HashMap<Human, Human> humans;

        /**
         * 当前父节点大小
         */
        HashMap<Human, Integer> humanParentSize;

        /**
         * 扁平化路径
         * 便于找到当前人父节点
         */
        HashMap<Human, Human> parentMap;

        public HumanSet(List<Human> list) {
            //初始化时候，每一个节点的样本是他自己
            for (Human cur : list) {
                //初始化当前节点
                Human node = new Human(cur.arg1, cur.arg2, cur.arg3, cur.type);
                humans.put(cur, node);
                parentMap.put(node, node);
                humanParentSize.put(node, 1);
            }

        }

        public Human findLastFather(Human curHuman) {
            Stack<Human> path = new Stack<>();
            while (curHuman != parentMap.get(curHuman)) {
                path.push(curHuman);
                curHuman = parentMap.get(curHuman);
            }
            while (!path.isEmpty()) {
                parentMap.put(path.pop(), curHuman);
            }
            return curHuman;
        }

        /**
         * 然后查看 parentMap的 Size就可以查看对应重复的学生
         * @param a
         * @param b
         */
        public void union(Human a, Human b) {
            if (!parentMap.containsKey(a) && !parentMap.containsKey(b)) return ;
            Human fatherA = findLastFather(humans.get(a));
            Human fatherB = findLastFather(humans.get(b));
            if (!fatherB.equals(fatherA)){
                Integer aSize = humanParentSize.get(a);
                Integer bSize = humanParentSize.get(b);
                //判断二者大小是否相符 使得 小的 在 打的后面
                if (aSize >= bSize) {
                    parentMap.put(fatherB, fatherA);//如果b得大小比a的小，直接把b得头换成a，与a相连接
                    humanParentSize.put(fatherA, aSize + bSize);//换头后要记得换掉
                    parentMap.remove(fatherB);//记得要清除之前得B父节点记录
                } else {
                    //否则相反
                    parentMap.put(fatherA, fatherB);
                    humanParentSize.put(fatherB, aSize + bSize);
                    parentMap.remove(fatherA);
                }
            }
        }
    }

}
