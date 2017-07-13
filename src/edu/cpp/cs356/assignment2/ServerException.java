package edu.cpp.cs356.assignment2;

/**
 * Created by xinyuan_wang on 7/10/17.
 */
public class ServerException extends Exception {

    public static final String NOT_UNIQUE_ID = "The ID already exists!";

    public static final String NO_ID_MATCH = "No ID matched!";

    public static final String NOT_USER = "This ID does not belong to a user!";

    private static final String DEFAULT_ERROR = "Error on server!";

    public ServerException()
    {
        super(DEFAULT_ERROR);
    }

    public ServerException(String message)
    {
        super(message);
    }

}
