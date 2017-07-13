package edu.cpp.cs356.assignment2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Xinyuan Wang on 7/8/2017.
 */
public class AdminControlPanel extends Application {

    static final private int SCENE_HEIGHT = 500;
    static final private int SCENE_WIDTH = 600;
    static final private double SPACING = 20f;
    static final private double GAP_SPACING = 100f;

    private Server mServer;

    private TreeViewController treeViewController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        mServer = TwitterServer.getInstance();
        primaryStage.setScene(initializeUI());
        primaryStage.show();

    }

    private Scene initializeUI()
    {
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(initializeTreeView());
        borderPane.setCenter(initializeControlArea());
        Scene scene = new Scene(borderPane,SCENE_WIDTH,SCENE_HEIGHT);
        return scene;
    }


    private Node initializeTreeView()
    {
        TreeView treeView = new TreeView();
        treeViewController = new TreeViewController(treeView,mServer);
        treeView.getSelectionModel().selectFirst();
        return treeView;
    }

    private Node initializeControlArea()
    {
        TextField inputUserID = new TextField("User ID");
        Button addUser = new Button("Add User");
        addUser.setOnMouseClicked(event -> {
            this.addNewComponent(new User(inputUserID.getText()));
        });
        HBox addUserBox = new HBox(SPACING);
        addUserBox.getChildren().addAll(inputUserID,addUser);

        TextField inputGroupID = new TextField("Group ID");
        Button addGroup = new Button("Add Group");
        addGroup.setOnMouseClicked(event -> {
            this.addNewComponent(new UserGroup(inputGroupID.getText()));
        });
        HBox addUserGroupBox = new HBox(SPACING);
        addUserGroupBox.getChildren().addAll(inputGroupID,addGroup);

        Button userView = new Button("Open User View");
        userView.setOnMouseClicked(event -> {
            showUserView();
        });

        VBox topBox = new VBox(SPACING);
        topBox.setPadding(new Insets(SPACING));
        topBox.getChildren().addAll(addUserBox,addUserGroupBox,userView);

        Button totalUser = new Button("Show User Total");
        totalUser.setOnMouseClicked(mouseEvent -> {countUser();});
        Button totalGroup = new Button("Show Group Total");
        totalGroup.setOnMouseClicked(mouseEvent -> {countUserGroup();});
        Button totalTweet = new Button("Show Tweet Total");
        totalTweet.setOnMouseClicked(mouseEvent -> {countTweet();});
        Button positive = new Button("Show Positive Percentage");
        positive.setOnMouseClicked(mouseEvent -> {countPositiveTweet();});
        VBox bottomBox = new VBox(SPACING);
        bottomBox.getChildren().addAll(totalUser,totalGroup,totalTweet,positive);
        bottomBox.setPadding(new Insets(SPACING));
        VBox mainBox = new VBox(GAP_SPACING);
        mainBox.getChildren().addAll(topBox,bottomBox);

        return mainBox;

    }

    private Component getSelectedComponent()
    {
            return treeViewController.getSelected();
    }

    private void addNewComponent(Component newComponent)
    {

        if(newComponent.getID().isEmpty())

            showAlert("Please enter new user ID.");
        else if(getSelectedComponent()==null)
            showAlert("Please Select a group.");
        else
        {
            try{
                mServer.addComponent(newComponent,getSelectedComponent());
                treeViewController.updateTreeView();
            } catch (ServerException e) {
                showAlert(e.getMessage());
            }

        }
    }

    private void showUserView()
    {
        Component selected = getSelectedComponent();
        if(selected instanceof User)
        new UserView(mServer,(User)selected).showUserWindows();
        else
            showAlert("Please select a user!");
    }

    private void showAlert(String message)
    {
        new Alert(Alert.AlertType.WARNING,message).show();
    }

    private void showMsgDialog(String title,String msg)
    {
        ButtonType buttonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setContentText(msg);
        dialog.getDialogPane().getButtonTypes().add(buttonType);
        dialog.show();
    }

    private void countUser()
    {
        CountUserVisitor countUserVisitor = new CountUserVisitor();
        mServer.traverse(countUserVisitor);
        showMsgDialog("Total User","The total number of user is "+countUserVisitor.getCount()+".");
    }

    private void countUserGroup()
    {
        CountUserGroupVisitor countUserGroupVisitor = new CountUserGroupVisitor();
        mServer.traverse(countUserGroupVisitor);
        showMsgDialog("Total User Group","The total number of user group is "+countUserGroupVisitor.getCount()+".");
    }

    private void countTweet()
    {
        CountTweetVisitor countTweetVisitor = new CountTweetVisitor();
        mServer.traverse(countTweetVisitor);
        showMsgDialog("Total Tweet","The total number of tweet is "+countTweetVisitor.getCount()+".");
    }


    private void countPositiveTweet()
    {
        PositiveTweetVisitor positiveTweetVisitor = new PositiveTweetVisitor();
        mServer.traverse(positiveTweetVisitor);
        showMsgDialog("Positive Tweet","The total number of positive tweet is "
                +positiveTweetVisitor.getPercentage()*100+"%."+
                "\n(positive tweet - hash code mod 100 >50)");
    }


}
