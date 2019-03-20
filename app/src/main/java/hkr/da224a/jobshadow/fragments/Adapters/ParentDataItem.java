package hkr.da224a.jobshadow.fragments.Adapters;

import java.util.ArrayList;

public class ParentDataItem {
    private String parentName;
    private ArrayList<ChildDataItem> childDataItems;

    public ParentDataItem(String parentName, ArrayList<ChildDataItem> childDataItems) {
        this.parentName = parentName;
        this.childDataItems = childDataItems;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public ArrayList<ChildDataItem> getChildDataItems() {
        return childDataItems;
    }

    public void setChildDataItems(ArrayList<ChildDataItem> childDataItems) {
        this.childDataItems = childDataItems;
    }
}
