package edu.cpp.cs356.assignment2;

import java.time.LocalDateTime;

/**
 * Created by Xinyuan Wang on 7/9/2017.
 */
public interface Tweet extends Comparable {

    public Object getContent();

    public String getUserID();

    public LocalDateTime getDateTime();
}
