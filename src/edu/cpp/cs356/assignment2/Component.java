package edu.cpp.cs356.assignment2;

import java.util.List;


/**
 * Created by Xinyuan Wang on 7/9/2017.
 */
public interface Component  {

    public String getID();

    public boolean addComponent(Component child);

    public List<Component> getChildren();

    public void accept(ComponentVisitor cv);


}
