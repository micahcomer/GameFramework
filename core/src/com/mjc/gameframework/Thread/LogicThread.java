package com.mjc.gameframework.Thread;

import com.mjc.gameframework.GameFramework;

/**
 * Created by Micah on 8/17/2015.
 */
public class LogicThread extends Thread {
    GameFramework framework;
    public boolean PAUSED = false;

    public LogicThread(GameFramework framework){
        super();
        this.framework = framework;
    }

    @Override
    public void run(){
        while(!PAUSED)
        framework.process();
    }
}
