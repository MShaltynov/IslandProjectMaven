package com.gmail.shaltynovm.project2.animals;

import com.gmail.shaltynovm.project2.field.Cell;
import com.gmail.shaltynovm.project2.field.FieldInitialization;

import java.util.Map;

public abstract class Vegans extends Animal {
    private int energyCapacity;
    public void setEnergyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
    }
    public int getEnergyCapacity() {
        return 0;
    }

    @Override
    public abstract boolean getBreedableStatus();

    @Override
    public  abstract void setBreedableStatus(boolean status);

    @Override
    public int getGivenEnergyIfEaten() {
        return 0;
    }

    @Override
    public abstract Animal getNewAnimal(FieldInitialization fieldInitialization);

    private Cell position;

    public Vegans(FieldInitialization fieldInitialization) {
        super(fieldInitialization);
    }

    @Override
    public Cell getPosition() {
        return this.position;
    }

    @Override
    public void setPosition(Cell positionCell) {
        this.position = positionCell;
    }

    @Override
    public Map<String, Integer> getChanceToEatList() {
        return null;
    }

}
