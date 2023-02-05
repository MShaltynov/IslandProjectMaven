package com.gmail.shaltynovm.project2.animals.predators;

import com.gmail.shaltynovm.project2.animals.Animal;
import com.gmail.shaltynovm.project2.animals.Predators;
import com.gmail.shaltynovm.project2.field.Cell;
import com.gmail.shaltynovm.project2.field.FieldInitialization;

import java.util.HashMap;
import java.util.Map;

public class Snake extends Predators {
    public Snake(FieldInitialization fieldInitialization, int energyCapacity, int givenEnergyIfEaten) {
        super(fieldInitialization);
        this.energyCapacity = energyCapacity;
        this.givenEnergyIfEaten = givenEnergyIfEaten;
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
    @Override
    public Animal getNewAnimal(FieldInitialization fieldInitialization) {
        return new Snake(fieldInitialization,5,5);
    }
    private int givenEnergyIfEaten;

    public int getGivenEnergyIfEaten() {
        return givenEnergyIfEaten;
    }
    private int energyCapacity;

    public void setEnergyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
    }

    public int getEnergyCapacity() {
        return energyCapacity;
    }

    private Cell position;
    private Map<String,Integer> chanceToEat=new HashMap<>(){{
        put("fox",15);
        put("rabbit",20);
        put("mouse",40);
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
        return "snake";
    }

    @Override
    public String getIcon() {
        return  "🐍";
    }
}
