package com.example.pong;

import javafx.scene.Scene;

import java.util.List;

public class SceneObservable implements Observable {
    Scene scene;
    @Override
    public void next(String s) {

    }

    @Override
    public List<Observer> getObservers() {
        return null;
    }
}
