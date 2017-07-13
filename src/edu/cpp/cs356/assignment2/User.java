package edu.cpp.cs356.assignment2;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Xinyuan Wang on 7/9/2017.
 */
public class User extends Observable implements Component,Observer{

    private String userID;

    private List<Tweet> myTweetList;

    private List<User> followingList;

    public User(String userID)
    {
        this.userID = userID;
        this.myTweetList = new ArrayList<>();
        this.followingList = new ArrayList<>();
        followingList.add(this);
    }

    @Override
    public String getID() {
        return userID;
    }

    @Override
    public boolean addComponent(Component child){
        return false;
    }

    @Override
    public List<Component> getChildren() {
        return null;
    }

    @Override
    public void accept(ComponentVisitor cv) {
        cv.visitUser(this);
    }

    public void follow(User followingTarget)
    {
        if(!followingList.contains(followingTarget)) {
            followingList.add(followingTarget);
            followingTarget.attachObserver(this);
            notifyAllObserver(new UIUpdateIntent());
        }
    }

    public void postTextTweet(String context)
    {
        myTweetList.add(new TextTweet(userID,context));
        notifyAllObserver(new NewTweetIntent());
        notifyAllObserver(new UIUpdateIntent());

    }

    public List<User> getFollowingList() {
        return followingList;
    }

    @Override
    public void update(Intent intent) {

        if(intent instanceof NewTweetIntent)
            notifyAllObserver(new UIUpdateIntent());
    }

    public List<Tweet> getAllTweets()
    {
        return myTweetList;
    }
}
