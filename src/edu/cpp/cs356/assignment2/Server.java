package edu.cpp.cs356.assignment2;


/**
 * Created by Xinyuan Wang on 7/9/2017.
 */
public interface Server {

    public void addComponent(Component newComponent,Component parentComponent) throws ServerException;

    public Component getRoot();

    public User getUserByID(String userID) throws ServerException;

    public void traverse(ComponentVisitor visitor);




}
