package com.mjc.gameframework.ScreenManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.google.common.eventbus.EventBus;

import java.util.ArrayList;

public class ScreenManager {

    ArrayList<Screen> screens;
    EventBus bus;

    public static abstract class Screen{
        public static final int STATUS_INACTIVE = 0; //Don't render or process
        public static final int STATUS_HIDDEN = 1;  //Do process.  Don't render.
        public static final int STATUS_PAUSED = 2;  //Don't process.  Do render.
        public static final int STATUS_ACTIVE = 3;  //Render and process.
        public int status;
        public EventBus bus;
        public abstract void render(SpriteBatch batch);
        public abstract void process(float deltaTime);

        public final void deactivate(){
            status = STATUS_INACTIVE;
        }

        public final void hide(){
            status = STATUS_HIDDEN;
        }
        public final void pause(){
            status = STATUS_PAUSED;
        }
        public final void activate(){
            status = STATUS_ACTIVE;
        }

    }

    public ScreenManager(){
        screens = new ArrayList<Screen>();
        bus = new EventBus();
    }

    public void addScreen(Screen s){
        screens.add(s);
    }
    public void removeScreen(Screen s) {
        screens.remove(s);
    }

    public void render(SpriteBatch batch){
        for (Screen s:screens){
            if ((s.status==Screen.STATUS_PAUSED)||(s.status==Screen.STATUS_ACTIVE)){
                s.render(batch);
            }
        }
    }
    public void process(float del){
        for (Screen s:screens){
            if ((s.status==Screen.STATUS_HIDDEN)||(s.status==Screen.STATUS_ACTIVE)){
                s.process(del);
            }
        }
    }

}
