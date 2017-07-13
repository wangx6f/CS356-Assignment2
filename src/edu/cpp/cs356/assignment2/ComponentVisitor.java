package edu.cpp.cs356.assignment2;

/**
 * Created by xinyuan_wang on 7/12/17.
 */
public interface ComponentVisitor {

    public void visitUser(User user);

    public void visitUserGroup(UserGroup userGroup);
}
