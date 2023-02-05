package com.gmail.shaltynovm.project2;

import com.gmail.shaltynovm.project2.engine.GameEngine;
import com.gmail.shaltynovm.project2.field.Island;
import com.gmail.shaltynovm.project2.render.GameRender;

public class Main {
    public static void main(String[] args)  {
        Island island = new Island(5,5);
        GameRender gameRender = new GameRender();
        GameEngine gameEngine = new GameEngine(1000,island,gameRender);
        gameEngine.start();
    }
}
