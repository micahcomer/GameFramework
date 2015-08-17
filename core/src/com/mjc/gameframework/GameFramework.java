package com.mjc.gameframework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mjc.gameframework.ScreenManager.ScreenManager;
import com.mjc.gameframework.Thread.LogicThread;


public class GameFramework extends ApplicationAdapter {

	SpriteBatch batch;
	LogicThread logicThread;
	float deltaTime;
	ScreenManager manager;
	int fps = 60;
	float frameRateMillis;

	@Override
	public void create() {

		frameRateMillis = fps/1000;
		batch = new SpriteBatch();
		logicThread = new LogicThread(this);
		logicThread.run();
		manager = new ScreenManager();
	}

	@Override
	public void render() {

		if (Gdx.graphics.getDeltaTime()>frameRateMillis){
			batch.begin();
			manager.render(batch);
			batch.end();
		}
	}

	public void process(){
		deltaTime = Gdx.graphics.getDeltaTime();
		manager.process(deltaTime);
	}

	public void pause(){

		logicThread.PAUSED = true;
	}

	public void resume(){
		logicThread.PAUSED = false;
		logicThread.run();
	}

	public void stop(){
		logicThread.interrupt();
		logicThread = null;

	}



}
