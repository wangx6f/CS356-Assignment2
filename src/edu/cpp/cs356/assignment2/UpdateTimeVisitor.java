package edu.cpp.cs356.assignment2;

public class UpdateTimeVisitor  implements ComponentVisitor{

    private long lastUpdatedTime;

    private String lastUpdatedUserID;


    @Override
    public void visitUser(User user) {
        long thisUpdatedTime = user.getLastUpdateTime();
        if(thisUpdatedTime>lastUpdatedTime){
            lastUpdatedTime=thisUpdatedTime;
            lastUpdatedUserID = user.getID();
        }

    }

    @Override
    public void visitUserGroup(UserGroup userGroup) {

    }

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public String getLastUpdatedUserID() {
        return lastUpdatedUserID;
    }
}
