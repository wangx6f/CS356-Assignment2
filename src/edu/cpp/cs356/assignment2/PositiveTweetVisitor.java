package edu.cpp.cs356.assignment2;

/**
 * Created by xinyuan_wang on 7/12/17.
 */
public class PositiveTweetVisitor implements ComponentVisitor {


    private double positiveCounter;

    private double totalCounter;

    @Override
    public void visitUser(User user) {

        if(user.getAllTweets()!=null) {
            for(Tweet tweet:user.getAllTweets())
            {
                if(isPositive(tweet))
                    positiveCounter++;
                totalCounter++;
            }
        }

    }

    private boolean isPositive(Tweet tweet)
    {
        int hash = tweet.getContent().hashCode();
        if(hash%100>50)
            return false;
        else
            return true;
    }

    @Override
    public void visitUserGroup(UserGroup userGroup) {

    }

    public double getPercentage()
    {
        if(totalCounter==0)
            return 0;
        else {
            double percentage = positiveCounter / totalCounter;
            positiveCounter=0;
            totalCounter=0;
            return percentage;
        }
    }
}
