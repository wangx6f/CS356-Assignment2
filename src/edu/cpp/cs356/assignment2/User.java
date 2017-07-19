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

    private long creationTime;

    private long lastUpdateTime;


    public User(String userID)
    {
        creationTime = System.currentTimeMillis();
        lastUpdateTime=creationTime;
        this.userID = userID;
        this.myTweetList = new ArrayList<>();
        this.followingList = new ArrayList<>();
        followingList.add(this);
        attachObserver(this);
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

    @Override
    public long getCreationTime() {
        return creationTime;
    }

    public void follow(User followingTarget)
    {
        if(!followingList.contains(followingTarget)) {
            followingList.add(followingTarget);
            followingTarget.attachObserver(this);
            updateTime();
            notifyAllObserver(new UIUpdateIntent());
        }
    }

    public void postTextTweet(String context)
    {
        myTweetList.add(new TextTweet(userID,context));
        notifyAllObserver(new NewTweetIntent());

    }

    public List<User> getFollowingList() {
        return followingList;
    }

    @Override
    public void update(Intent intent) {

        if(intent instanceof NewTweetIntent) {
            updateTime();
            notifyAllObserver(new UIUpdateIntent());
        }
    }

    private void updateTime(){
        lastUpdateTime = System.currentTimeMillis();
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }


    public List<Tweet> getAllTweets()
    {
        return myTweetList;
    }
}
