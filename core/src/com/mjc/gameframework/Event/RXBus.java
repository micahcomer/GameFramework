package com.mjc.gameframework.Event;

import rx.Observable;
import rx.functions.Action1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Micah on 8/18/2015.
 */
public class RXBus {

    private final static Subject<Object, Object> _bus = new SerializedSubject<Object, Object>(PublishSubject.create());

    public static void send(Object o) {
        _bus.onNext(o);
    }

    public static Observable<Object> toObserverable() {
        return _bus;
    }

    public static void subscribe(Observable<Object> observable, Action1<Object> action1){
        observable.subscribe(action1);
    }
}
