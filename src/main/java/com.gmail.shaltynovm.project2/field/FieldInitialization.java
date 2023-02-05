package com.gmail.shaltynovm.project2.field;

import activities.Activity;
import com.gmail.shaltynovm.project2.animals.Animal;
import com.gmail.shaltynovm.project2.animals.Grass;
import com.gmail.shaltynovm.project2.animals.predators.Bear;
import com.gmail.shaltynovm.project2.animals.predators.Eagle;
import com.gmail.shaltynovm.project2.animals.predators.Fox;
import com.gmail.shaltynovm.project2.animals.predators.Snake;
import com.gmail.shaltynovm.project2.animals.predators.Wolf;
import com.gmail.shaltynovm.project2.animals.vegans.Boar;
import com.gmail.shaltynovm.project2.animals.vegans.Buffalo;
import com.gmail.shaltynovm.project2.animals.vegans.Caterpillar;
import com.gmail.shaltynovm.project2.animals.vegans.Deer;
import com.gmail.shaltynovm.project2.animals.vegans.Duck;
import com.gmail.shaltynovm.project2.animals.vegans.Goat;
import com.gmail.shaltynovm.project2.animals.vegans.Horse;
import com.gmail.shaltynovm.project2.animals.vegans.Mouse;
import com.gmail.shaltynovm.project2.animals.vegans.Rabbit;
import com.gmail.shaltynovm.project2.animals.vegans.Sheep;
import com.gmail.shaltynovm.project2.render.GameRender;
import config.AnimalConfig;

import java.util.*;

public class FieldInitialization {

    public Bear getBear() {
        return new Bear(this, 100, 100);
    }

    public Snake getSnake() {
        return new Snake(this, 20, 20);
    }

    public Boar getBoar() {
        return new Boar(this, 60, 80);
    }

    public Buffalo getBuffalo() {
        return new Buffalo(this, 80, 80);
    }

    public Caterpillar getCaterpillar() {
        return new Caterpillar(this, 5, 5);
    }

    public Deer getDeer() {
        return new Deer(this, 70, 70);
    }

    public Duck getDuck() {
        return new Duck(this, 30, 20);
    }

    public Eagle getEagle() {
        return new Eagle(this, 30, 20);
    }

    public Fox getFox() {
        return new Fox(this, 50, 40);
    }

    public Goat getGoat() {
        return new Goat(this, 60, 60);
    }

    public Horse getHorse() {
        return new Horse(this, 80, 100);
    }

    public Mouse getMouse() {
        return new Mouse(this, 10, 5);
    }

    public Rabbit getRabbit() {
        return new Rabbit(this, 20, 20);
    }

    public Sheep getSheep() {
        return new Sheep(this, 60, 50);
    }

    public Wolf getWolf() {
        return new Wolf(this, 80, 50);
    }

    public Grass getGrass() {
        return new Grass(this, 50, 50);
    }

    private Map<Animal, String> animalStringMap;

    Island island;
    GameRender gameRender;

    public FieldInitialization(Island island, GameRender gameRender) {
        this.island = island;
        this.gameRender = gameRender;
    }

    AnimalConfig animalConfig = new AnimalConfig(this);

    public GameRender getGameRender() {
        return gameRender;
    }


    public Island getIsland() {
        return island;
    }

    public Activity getActivity() {
        return new Activity(getIsland(), animalConfig);
    }

    public AnimalConfig getAnimalConfig() {
        return animalConfig;
    }

    public void populateIsland(Island island) {
        for (int i = 0; i < island.xDimension; i++) {
            for (int j = 0; j < island.yDimension; j++) {
                populateCell(island.islandGrid[i][j]);
            }
        }
    }

    private void populateCell(Cell cell) {
        int animalCount = 0;
        for (Map.Entry<Animal, Integer> entry : animalConfig.getChanceToAppearFromTXT().entrySet()) {
            Animal key = entry.getKey();
            Integer value = entry.getValue();
            if (animalConfig.calculateChanceToEmerge(value)) {
                cell.addAnimal(key);
                key.setPosition(cell);
                System.out.print(key.getIcon());
                animalCount++;
            }
        }
        System.out.printf("\t" + "cell %s populated with %s animals ", cell, animalCount);
        System.out.println();
    }



    public Map<Animal, String> getAnimalStringMap() {
        animalStringMap = new HashMap<>() {{
            put(getBear(), "bear");
            put(getBoar(), "boar");
            put(getBuffalo(), "buffalo");
            put(getCaterpillar(), "caterpillar");
            put(getDeer(), "deer");
            put(getDuck(), "duck");
            put(getEagle(), "eagle");
            put(getFox(), "fox");
            put(getGoat(), "goat");
            put(getHorse(), "horse");
            put(getMouse(), "mouse");
            put(getRabbit(), "rabbit");
            put(getSheep(), "sheep");
            put(getSnake(), "snake");
            put(getWolf(), "wolf");
            put(getGrass(), "grass");
        }};
        return animalStringMap;
    }
}
