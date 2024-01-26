package SystudyTest.tree_test;

import com.alibaba.fastjson2.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NodeRelationPO {

  /**
   * 当前节点id
   */
  private String id;

  /**
   * 当前节点名称
   */
  private String name;

  /**
   * 子节点
   */
  private NodeRelationPO child;

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

  public NodeRelationPO getChild() {
    return child;
  }

  public void setChild(NodeRelationPO child) {
    this.child = child;
  }

  public static NodeRelationPO getPathToRootNested(String nodeId, List<NodePO> allNodes) {
    NodePO targetNode = findNodeById(nodeId, allNodes);
    if (targetNode == null) {
      return null;
    }

    List<NodePO> pathToRoot = buildPathToRoot(targetNode, allNodes);
    return buildNestedStructure(pathToRoot);
  }

  private static List<NodePO> buildPathToRoot(NodePO currentNode, List<NodePO> allNodes) {
    List<NodePO> path = new ArrayList<>();
    path.add(currentNode);

    if (!Objects.equals(currentNode.getParentId(), "0")) {
      NodePO parent = findNodeById(currentNode.getParentId(), allNodes);
      if (parent != null) {
        path.addAll(buildPathToRoot(parent, allNodes));
      }
    }

    return path;
  }

  private static NodeRelationPO buildNestedStructure(List<NodePO> path) {
    NodePO rootNode = path.get(path.size() - 1);
    NodeRelationPO nestedStructure = new NodeRelationPO();
    nestedStructure.setId(rootNode.getId());
    nestedStructure.setName(rootNode.getName());

    NodeRelationPO currentNode = nestedStructure;
    for (int i = path.size() - 2; i >= 0; i--) {
      NodePO nodePO = path.get(i);
      NodeRelationPO childNode = new NodeRelationPO();
      childNode.setId(nodePO.getId());
      childNode.setName(nodePO.getName());

      currentNode.setChild(childNode);
      currentNode = childNode;
    }

    return nestedStructure;
  }

  private static NodePO findNodeById(String nodeId, List<NodePO> nodes) {
    return nodes.stream()
        .filter(node -> Objects.equals(node.getId(), nodeId))
        .findFirst()
        .orElse(null);
  }

  public static void main(String[] args) {
    List<NodePO> nodePOs = Arrays.asList(
        new NodePO("1", "一级节点1", "0", "_0001"),
        new NodePO("2", "二级节点1.1", "1", "_0002"),
        new NodePO("3", "二级节点1.2", "1", "_0003"),

        new NodePO("4", "一级节点2", "0", "_0004"),
        new NodePO("5", "二级节点2.1", "4", "_0005"),
        new NodePO("6", "二级节点2.2", "4", "_0006"),
        new NodePO("7", "三级节点2.2.1", "6", "_0007")
    );

    String targetNodeId = "7"; // Change this to the desired node id
    NodeRelationPO nestedStructure = getPathToRootNested(targetNodeId, nodePOs);
    System.out.println(JSONObject.toJSONString(nestedStructure));
  }
}

