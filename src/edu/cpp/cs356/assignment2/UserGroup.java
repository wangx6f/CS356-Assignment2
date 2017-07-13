package edu.cpp.cs356.assignment2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xinyuan Wang on 7/9/2017.
 */
public class UserGroup implements Component {

    private String groupID;

    private List<Component> children;

    public UserGroup(String groupID) {
        this.groupID = groupID;
        children = new ArrayList<>();
    }

    @Override
    public String getID() {
        return groupID;
    }

    @Override
    public boolean addComponent(Component child) {

        children.add(child);
        return true;
    }

    @Override
    public List<Component> getChildren() {
        return children;
    }

    @Override
    public void accept(ComponentVisitor cv) {
        cv.visitUserGroup(this);
    }
}
