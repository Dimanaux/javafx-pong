package sample;

import java.util.List;

public interface Observable {
    List<Observer> getObservers();

    default void addObserver(Observer o) {
        getObservers().add(o);
    }

    default void removeObserver(Observer o) {
        getObservers().remove(o);
    }

    default void next(String s) {
        getObservers().forEach(o -> o.onNext(s));
    }
}
