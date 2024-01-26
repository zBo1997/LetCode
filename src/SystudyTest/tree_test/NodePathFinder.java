package SystudyTest.tree_test;

import com.alibaba.fastjson2.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class NodePathFinder {

    static class NodePO {
        String id;
        String name;
        String parentId;

        public NodePO(String id, String name, String parentId) {
            this.id = id;
            this.name = name;
            this.parentId = parentId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }

    public static List<NodePO> findPathToRoot(List<NodePO> allNodes, String targetId) {
        Map<String, NodePO> nodeMap = new HashMap<>();
        for (NodePO node : allNodes) {
            nodeMap.put(node.id, node);
        }

        List<NodePO> path = new LinkedList<>();
        NodePO currentNode = nodeMap.get(targetId);

        while (currentNode != null) {
            path.add(currentNode);
            currentNode = nodeMap.get(currentNode.parentId);
        }

        return path;
    }

    public static void main(String[] args) {
        List<NodePO> nodePOs = Arrays.asList(
            new NodePO("1", "一级节点1", "0"),
            new NodePO("2", "二级节点1.1", "1"),
            new NodePO("3", "二级节点1.2", "1"),
            new NodePO("4", "一级节点2", "0"),
            new NodePO("5", "二级节点2.1", "4"),
            new NodePO("6", "二级节点2.2", "4"),
            new NodePO("7", "三级节点2.2.1", "6")
        );

        List<String> targetIds = Arrays.asList("1","7");
        List<List<NodePO>> result = new ArrayList<>();
        for (String targetId : targetIds) {
            List<NodePO> pathToRoot = findPathToRoot(nodePOs, targetId);
            result.add(pathToRoot);
        }
        System.out.println(JSONObject.toJSONString(result));

    }
}