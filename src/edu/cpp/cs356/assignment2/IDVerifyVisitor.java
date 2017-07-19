package edu.cpp.cs356.assignment2;

import java.util.ArrayList;
import java.util.List;

public class IDVerifyVisitor implements ComponentVisitor {


    private List<String> validID;

    private List<String> invalidID;

    public List<String> getValidID() {
        return validID;
    }

    public List<String> getInvalidID() {
        return invalidID;
    }

    public IDVerifyVisitor() {
        this.validID = new ArrayList<>();
        this.invalidID = new ArrayList<>();
    }


    @Override
    public void visitUser(User user) {
        checkID(user);
    }

    @Override
    public void visitUserGroup(UserGroup userGroup) {
        checkID(userGroup);
    }



    private void checkID(Component component) {
        String ID = component.getID();
        if(ID.contains(" ")) {
            invalidID.add(ID);
        } else{
            validID.add(ID);
        }
    }


}
