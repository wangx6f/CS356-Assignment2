package edu.cpp.cs356.assignment2;

import java.time.LocalDateTime;

/**
 * Created by Xinyuan Wang on 7/9/2017.
 */
public class TextTweet implements Tweet {

    String userID;

    String textContent;

    LocalDateTime postDateTime;

    public TextTweet(String userID,String textContent)
    {
        this.userID = userID;
        this.textContent = textContent;
        postDateTime= LocalDateTime.now();
    }

    @Override
    public Object getContent() {
        return textContent;
    }

    @Override
    public String getUserID() {
        return userID;
    }

    @Override
    public LocalDateTime getDateTime() {
        return postDateTime;
    }

    @Override
    public int compareTo(Object o) {
        return ((Tweet)o).getDateTime().compareTo(this.getDateTime());
    }
}
