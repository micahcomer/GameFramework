package com.mjc.gameframework.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Micah on 8/17/2015.
 */
public class EventBus {

    //Here is the plan for using event bus.
    //First, if a class is going to fire events, it defines those events as a subclass within its own class, with that subclass extending the abstract Event class defined below.
    // Then, in order to fire the event, it calls EventBus.fireEvent(event), passing an instantiation of the event it defined.
    //Second, if a class wants to listen for events, it calls EventBus.register(listener, eClass), as defined below.  It passes itself as a listener, and the class of the Event it
    //wants to listen for.  If it ever wants to stop listening for events, it calls deregister, passing itself as the listener to be deregistered, and the type of event it no longer
    //wants to receive events for.

    static HashMap<Class<? extends Event>, ArrayList<? extends EventListener>> map;

    public interface EventListener{
        public abstract <T extends Event> void onEvent(T event);
    }

    public abstract class Event{}

    public static synchronized <T extends EventListener>  void register(T listener, Class<? extends Event> eClass){

        //First, create the map if it doesn't exist.
        if (map == null){
            map = new HashMap<Class<? extends Event>, ArrayList<? extends EventListener>>();
        }

        //Second, try to get the ArrayList of listeners that listen to the specific type of event.
        //If no such list exists, create a new list and put it into the map.
        ArrayList list = map.get(eClass);
        if (list == null){
            list = new ArrayList<T>();
            map.put(eClass, list);
        }

        //Third, add the listener to the list.
        list.add(listener);
    }

    public static synchronized <T extends EventListener, K extends Event> void deregister(T listener, K event){
        if (map!=null){
            ArrayList list = map.get(event.getClass());
            if (list!=null){
                list.remove(listener);
            }
        }
    }

    public static synchronized <T extends Event> void fireEvent(T event){

        if (map != null){
            ArrayList<? extends EventListener> list = map.get(event.getClass());
            if (list != null){
                for (EventListener l: list){
                    l.onEvent(event);
                }
            }
        }
    }

}
