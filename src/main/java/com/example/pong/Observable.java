package com.example.pong;

import java.util.List;
import java.util.Objects;

public interface Observable {
    List<Observer> getObservers();

    default void addObserver(Observer o) {
        getObservers().add(o);
    }

    default void removeObserver(Observer o) {
        getObservers().remove(o);
    }

    default void next(String s) {
        getObservers().stream().filter(Objects::nonNull).forEach(o -> o.onNext(s));
    }
}
