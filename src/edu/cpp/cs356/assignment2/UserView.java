package edu.cpp.cs356.assignment2;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by wxy03 on 7/10/2017.
 */
public class UserView implements Observer {

    static final private int SCENE_HEIGHT = 500;
    static final private int SCENE_WIDTH = 600;
    static final private double SPACING = 10f;

    private Server mServer;

    private User mUser;

    private ListView followingListView;

    private ListView newsFeedListView;


    public UserView(Server server, User thisUser)
    {
        mServer=server;
        mUser=thisUser;
        mUser.attachObserver(this);

    }

    public void showUserWindows() {

        BorderPane root = new BorderPane();
        root.setCenter(initializeUI());
        updateFollowingInfo();
        Scene scene = new Scene(root,SCENE_WIDTH,SCENE_HEIGHT);
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.setTitle(mUser.getID());
        newStage.setOnCloseRequest(event -> {mUser.detachObserver(this);});
        newStage.show();
    }

    private Node initializeUI()
    {

        TextField userIDInput = new TextField("User ID");
        Button followUser = new Button("Follow User");
        followUser.setOnMouseClicked(event -> {
            follow(userIDInput.getText());
        });
        HBox followUserBox = new HBox(SPACING);
        followUserBox.getChildren().addAll(userIDInput,followUser);


        Label followingLabel = new Label("Currently following:");
        followingLabel.setPadding(new Insets(0,0,SPACING,0));
        followingListView = new ListView();
        VBox followingListBox = new VBox(followingLabel,followingListView);


        TextArea tweetMessageInput = new TextArea("Tweet Message");
        tweetMessageInput.setWrapText(true);
        Button postNewTweet = new Button("Post Tweet");
        postNewTweet.setOnMouseClicked(mouseEvent -> {
            mUser.postTextTweet(tweetMessageInput.getText());
        });
        VBox postTweetBox = new VBox(SPACING);
        postTweetBox.getChildren().addAll(tweetMessageInput,postNewTweet);


        Label newsFeedLabel = new Label("News feed:");
        newsFeedLabel.setPadding(new Insets(0,0,SPACING,0));
        newsFeedListView = new ListView();
        VBox newsFeedListBox = new VBox(newsFeedLabel,newsFeedListView);


        VBox mainBox = new VBox(SPACING);
        mainBox.setPadding(new Insets(SPACING));
        mainBox.getChildren().addAll(followUserBox,followingListBox,postTweetBox,newsFeedListBox);


        return mainBox;



    }


    private void follow(String userID)
    {
        try {
            mUser.follow(mServer.getUserByID(userID));
        }catch (ServerException e)
        {
            showAlert(e.getMessage());
        }
    }

    private void updateFollowingInfo()
    {
        List<String> userIDList = new ArrayList<>();
        List<Tweet> tweetList = new ArrayList<>();
        for(User followingUser:mUser.getFollowingList())
        {
            userIDList.add(followingUser.getID());
            for(Tweet tweet:followingUser.getAllTweets())
                tweetList.add(tweet);
        }

        List tweetListAsString = new ArrayList<>();

        Collections.sort(tweetList);

        for(Tweet tweet:tweetList)
        {
            tweetListAsString.add(formatTweet(tweet));
        }

        followingListView.setItems(FXCollections.observableList(userIDList));
        newsFeedListView.setItems(FXCollections.observableList(tweetListAsString));
    }


    private String formatTweet(Tweet tweet)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("["+tweet.getDateTime().toString()+"] ");
        stringBuilder.append(tweet.getUserID()+": ");
        stringBuilder.append(tweet.getContent());
        return stringBuilder.toString();

    }

    private void showAlert(String message)
    {
        new Alert(Alert.AlertType.WARNING,message).show();
    }

    @Override
    public void update(Intent intent) {
        if(intent instanceof UIUpdateIntent)
        updateFollowingInfo();
    }

}
