package com.example.pong;

import javafx.scene.Scene;

import java.util.LinkedList;
import java.util.List;

public class SceneObservable implements Observable {
    List<Observer> observers = new LinkedList<>();

    public SceneObservable(Scene scene) {
        scene.setOnKeyPressed(k -> next(k.getCode().toString()));
        scene.setOnKeyPressed(e -> this.next(e.getCode().toString()));
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }
}
