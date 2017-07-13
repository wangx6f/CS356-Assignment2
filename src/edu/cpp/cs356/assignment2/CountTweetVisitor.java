package edu.cpp.cs356.assignment2;

/**
 * Created by xinyuan_wang on 7/12/17.
 */
public class CountTweetVisitor implements ComponentVisitor {

    int counter;

    @Override
    public void visitUser(User user) {
        if(user.getAllTweets()!=null)
            counter+= user.getAllTweets().size();

    }

    @Override
    public void visitUserGroup(UserGroup userGroup) {

    }

    public int getCount()
    {
        int count = counter;
        counter=0;
        return count;
    }
}
