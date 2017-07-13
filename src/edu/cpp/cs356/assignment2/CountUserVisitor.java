package edu.cpp.cs356.assignment2;

/**
 * Created by xinyuan_wang on 7/12/17.
 */
public class CountUserVisitor implements ComponentVisitor{

    private int counter;

    @Override
    public void visitUser(User user) {
        counter++;

    }

    @Override
    public void visitUserGroup(UserGroup userGroup) {

    }

    public int getCount()
    {
        int count = counter;
        counter = 0;
        return count;
    }
}
