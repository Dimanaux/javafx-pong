package com.example.pong;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractObservable implements Observable {
    private List<Observer> observers = new LinkedList<>();

    @Override
    public List<Observer> getObservers() {
        return observers;
    }
}
