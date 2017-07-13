package edu.cpp.cs356.assignment2;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Xinyuan Wang on 7/9/2017.
 */
public class TwitterServer implements Server {

    private final String ROOT_ID = "Root";

    private Map<String,Component> componentMap;

    private static TwitterServer instance;

    private TwitterServer()
    {
        componentMap = new HashMap<>();
        componentMap.put(ROOT_ID,new UserGroup(ROOT_ID));
    }

    public static TwitterServer getInstance()
    {
        if(instance==null)
        {
            synchronized (TwitterServer.class)
            {
                if(instance==null)
                    instance = new TwitterServer();
            }
        }
        return instance;
    }

    @Override
    public void addComponent(Component newComponent, Component parentComponent) throws ServerException {
        if(componentMap.containsKey(newComponent.getID()))
            throw new ServerException(ServerException.NOT_UNIQUE_ID);
        else if(parentComponent.addComponent(newComponent))
            componentMap.put(newComponent.getID(),newComponent);
        else
            throw new ServerException("Unable to add as a child of "+parentComponent.getID()+" !");

    }

    @Override
    public Component getRoot()
    {
        return  componentMap.get(ROOT_ID);
    }

    @Override
    public User getUserByID(String userID) throws ServerException {
        if(!componentMap.containsKey(userID))
            throw new ServerException(ServerException.NO_ID_MATCH);
        else
        {
            Component component = componentMap.get(userID);
            if(component instanceof User)
                return (User)component;
            else
                throw new ServerException(ServerException.NOT_USER);

        }
    }

    @Override
    public void traverse(ComponentVisitor visitor) {
        traverse(this.getRoot(),visitor);

    }

    private void traverse(Component root,ComponentVisitor visitor) {

        root.accept(visitor);
        if(root.getChildren()!=null)
        for(Component child:root.getChildren())
            traverse(child,visitor);
    }


}
