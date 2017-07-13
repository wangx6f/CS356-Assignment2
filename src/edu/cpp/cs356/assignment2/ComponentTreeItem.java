package edu.cpp.cs356.assignment2;

import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;


/**
 * Created by wxy03 on 7/9/2017.
 */
public class ComponentTreeItem extends TreeItem {

    private Component component;

    private final Node userIcon = new ImageView(new Image(new File("user.png").toURI().toString()));

    private final Node userGroupIcon = new ImageView(new Image(new File("user_group.png").toURI().toString()));



    public ComponentTreeItem(Component component)
    {
        this.component = component;
        this.setValue(component.getID());
        if(component instanceof User) {
           setGraphic(userIcon);
        }
        else if(component instanceof  UserGroup) {
            setExpanded(true);
            setGraphic(userGroupIcon);
        }
    }

    public Component getComponent() {
        return component;
    }
}
