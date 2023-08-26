package SystudyTest.tree_test;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson2.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;

public class Test {

  static final List<NodePO> nodePOs = Arrays.asList(
      new NodePO("1", "一级节点1", "0", "_0001"),
      new NodePO("2", "二级节点1.1", "1", "_0002"),
      new NodePO("3", "二级节点1.2", "1", "_0003"),

      new NodePO("4", "一级节点2", "0", "_0004"),
      new NodePO("5", "二级节点2.1", "4", "_0005"),
      new NodePO("6", "二级节点2.2", "4", "_0006"),
      new NodePO("7", "三级节点2.2.1", "6", "_0007")
  );


  /**
   * 根据orderNo排序树形结构的每一个层级
   *
   * @param list
   * @return
   */
  public static List<Map<String, Object>> sortJava8Map(List<Map<String, Object>> list) {
    if (CollectionUtils.isEmpty(list)) {
      return new ArrayList<>();
    }
    //关键之处，一行代码搞定
    list.sort(Comparator.comparing(m -> String.valueOf(m.get("orderNo"))));
    return list;
  }

  /**
   * 根据父级节点获取所有的子集节点
   *
   * @param parentNode
   * @param allList
   * @return
   */
  public static List<Map<String, Object>> getJava8Children(Map<String, Object> parentNode,
      List<Map<String, Object>> allList) {
    return allList.stream()
        .filter(
            curNode -> curNode.get("parentId") != null && Objects.equals(curNode.get("parentId"),
                parentNode.get("id")))
        .peek(m -> m.put("children", getJava8Children(m, allList))).collect(Collectors.toList());
  }

  public static List<NodePO> findSubjectTreeList() {

    // 最终返回结果
    List<NodePO> resultList = new ArrayList<>();

    // 查询所有课程分类并转换
    List<NodePO> allSubjectVoList = new ArrayList<>();
    for (NodePO eduSubject : nodePOs) {
      NodePO nodePo = new NodePO();
      BeanUtil.copyProperties(eduSubject, nodePo);
      allSubjectVoList.add(nodePo);
    }

    // 过滤一级分类
    for (NodePO subjectVo : allSubjectVoList) {
      if (Objects.deepEquals(subjectVo.getParentId(), "0")) {
        getChildSubjectList(subjectVo, allSubjectVoList);
        resultList.add(subjectVo);
      }
    }

    return resultList;
  }

  private static void getChildSubjectList(NodePO nodepo, List<NodePO>
      nodePoList) {

    // 过滤子节点，如果子节点不为空，开始递归查询所有节点，直到子节点为空停止
    List<NodePO> children = nodePoList.stream()
        .filter(e -> Objects.deepEquals(e.getParentId(), nodepo.getId()))
        .collect(Collectors.toList());
    if (!CollectionUtils.isEmpty(children)) {
      for (NodePO child : children) {
        getChildSubjectList(child, nodePoList);
      }
      nodepo.setChildren(children);
    }

  }

  public static void main(String[] args) {
    List<NodePO> subjectTreeList = findSubjectTreeList();
    System.out.println(JSONObject.toJSONString(subjectTreeList));
  }

}
