package edu.cpp.cs356.assignment2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xinyuan Wang on 7/9/2017.
 */
public class Observable {

    private List<Observer> observerList = new ArrayList<>();

    public void notifyAllObserver(Intent intent)
    {
        for(Observer observer:observerList)
        {
            observer.update(intent);
        }
    }

    public void attachObserver(Observer observer)
    {

        if(!observerList.contains(observer))
            observerList.add(observer);
    }

    public void detachObserver(Observer observer)
    {
        if(observerList.contains(observer))
            observerList.remove(observer);
    }
}
