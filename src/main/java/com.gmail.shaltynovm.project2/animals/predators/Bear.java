package com.gmail.shaltynovm.project2.animals.predators;

import com.gmail.shaltynovm.project2.animals.Animal;
import com.gmail.shaltynovm.project2.animals.Predators;
import com.gmail.shaltynovm.project2.field.Cell;
import com.gmail.shaltynovm.project2.field.FieldInitialization;

import java.util.HashMap;
import java.util.Map;

public class Bear extends Predators {
    public Bear(FieldInitialization fieldInitialization, int energyCapacity, int givenEnergyIfEaten ) {
        super(fieldInitialization);
        this.energyCapacity = energyCapacity;
        this.givenEnergyIfEaten = givenEnergyIfEaten;
    }

    @Override
    public Animal getNewAnimal(FieldInitialization fieldInitialization) {
        return new Bear(fieldInitialization,5,5);
    }

    private int energyCapacity;
    private int givenEnergyIfEaten;


    public int getGivenEnergyIfEaten() {
        return givenEnergyIfEaten;
    }

    public void setEnergyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }
    private boolean breedableStatus = true;

    @Override
    public boolean getBreedableStatus() {
        return breedableStatus;
    }

    @Override
    public void setBreedableStatus(boolean breedableStatus) {
        this.breedableStatus = breedableStatus;
    }

    private Cell position;
    private Map<String,Integer> chanceToEat=new HashMap<>(){{
        put("snake",80);
        put("horse",40);
        put("deer",80);
        put("rabbit",80);
        put("mouse",90);
        put("sheep",70);
        put("boar",50);
        put("goat",70);
        put("buffalo",20);
        put("duck",10);
    }};

    @Override
    public Map<String, Integer> getChanceToEatList() {
        return chanceToEat;
    }

    @Override
    public Cell getPosition() {
        return position;
    }

    @Override
    public void setPosition(Cell positionCell) {
        this.position = positionCell;
    }

    @Override
    public String toString() {
        return "bear";
    }

    @Override
    public String getIcon() {
        return "🐻";
    }
}
