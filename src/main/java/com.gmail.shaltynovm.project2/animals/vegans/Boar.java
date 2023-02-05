package com.gmail.shaltynovm.project2.animals.vegans;

import com.gmail.shaltynovm.project2.animals.Animal;
import com.gmail.shaltynovm.project2.animals.Vegans;
import com.gmail.shaltynovm.project2.field.Cell;
import com.gmail.shaltynovm.project2.field.InitialField;

import java.util.HashMap;
import java.util.Map;

public class Boar extends Vegans {
    public Boar(InitialField initialField, int energyCapacity, int givenEnergyIfEaten) {
        super(initialField);
        this.energyCapacity = energyCapacity;
        this.givenEnergyIfEaten = givenEnergyIfEaten;
    }

    @Override
    public Animal getNewAnimal(InitialField initialField) {
        return new Boar(initialField, 5, 5);
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
    private Map<String, Integer> chanceToEat = new HashMap<>() {{
        put("mouse", 50);
        put("caterpillar", 90);
        put("grass", 100);
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
        return "boar";
    }

    @Override
    public String getIcon() {
        return "🐗";
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
}