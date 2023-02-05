package com.gmail.shaltynovm.project2.animals;

import com.gmail.shaltynovm.project2.field.Cell;
import com.gmail.shaltynovm.project2.field.InitialField;

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
    public abstract Animal getNewAnimal(InitialField initialField);

    private Cell position;

    public Vegans(InitialField initialField) {
        super(initialField);
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
