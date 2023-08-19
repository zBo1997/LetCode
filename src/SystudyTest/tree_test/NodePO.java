package SystudyTest.tree_test;

import java.util.List;

public class NodePO {

    /**
     * 当前节点id
     */
    private String id;

    /**
     * 当前节点名称
     */
    private String name;

    /**
     * 父级节点id
     */
    private String parentId;

    /**
     * 当前节点序号
     */
    private String orderNo;

    /**
     * 子集节点
     */
    private List<NodePO> children;


    public NodePO(String id,String name,String parentId,String orderNo){
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.orderNo = orderNo;
    }

    public NodePO() {
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public List<NodePO> getChildren() {
        return children;
    }

    public void setChildren(List<NodePO> children) {
        this.children = children;
    }
}
