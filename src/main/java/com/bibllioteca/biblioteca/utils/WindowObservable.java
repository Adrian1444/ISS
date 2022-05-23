package com.bibllioteca.biblioteca.utils;

import java.util.ArrayList;
import java.util.List;

public abstract class WindowObservable {
    List<WindowObserver> observers = new ArrayList<>();

    public void addObserver(WindowObserver observer){
        observers.add(observer);
    }

    public void notifyObservers(){
        for(WindowObserver observer:observers)
            observer.update();
    }
}
