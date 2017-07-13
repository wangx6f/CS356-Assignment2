package edu.cpp.cs356.assignment2;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;


/**
 * Created by Xinyuan Wang on 7/9/2017.
 */
public class TreeViewController{


    private TreeView treeView;

    private Server mServer;

    public TreeViewController(TreeView treeView,Server server)
    {
        this.treeView = treeView;
        this.mServer = server;
        updateTreeView();
    }

    public void updateTreeView()
    {
        treeView.setRoot(construct());
    }

    private TreeItem construct()
    {
        return construct(mServer.getRoot());
    }

    private TreeItem construct(Component rootComponent)
    {
        TreeItem rootItem = createItem(rootComponent);
        if(rootComponent.getChildren()!=null) {
            for (Component child : rootComponent.getChildren())
            {
                    rootItem.getChildren().add(construct(child));
            }
        }
        return rootItem;
    }


    private TreeItem createItem(Component component)
    {
       return new ComponentTreeItem(component);

    }

    public Component getSelected()
    {
        ComponentTreeItem selected = (ComponentTreeItem) treeView.getSelectionModel().getSelectedItem();
        if(selected==null)
            return null;
        else
            return selected.getComponent();
    }

}
