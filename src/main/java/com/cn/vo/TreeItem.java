package com.cn.vo;

import java.util.List;
import java.util.Map;

/**
 * Created by home on 2017/7/7.
 */
public class TreeItem {
    private String id; //节点的 id，它对于加载远程数据很重要。
    private String text;//要显示的节点文本。
    private String state;//节点状态，'open' 或 'closed'，默认是 'open'。当设置为 'closed' 时，该节点有子节点，并且将从远程站点加载它们。
    private Boolean checked;//指示节点是否被选中。
    private Map attributes;//给一个节点添加的自定义属性。
    private List<TreeItem> children;//定义了一些子节点的节点数组。

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Map getAttributes() {
        return attributes;
    }

    public void setAttributes(Map attributes) {
        this.attributes = attributes;
    }

    public List<TreeItem> getChildren() {
        return children;
    }

    public void setChildren(List<TreeItem> children) {
        this.children = children;
    }
}
