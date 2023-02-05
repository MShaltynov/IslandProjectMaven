package com.gmail.shaltynovm.project2.animals;

import com.gmail.shaltynovm.project2.field.Cell;
import com.gmail.shaltynovm.project2.field.FieldInitialization;

import java.util.HashMap;
import java.util.Map;

public class Grass extends Animal {

    private int givenEnergyIfEaten;
    private int energyCapacity;

    public Grass(FieldInitialization fieldInitialization, int energyCapacity, int givenEnergyIfEaten) {
        super(fieldInitialization);
        this.energyCapacity = energyCapacity;
        this.givenEnergyIfEaten = givenEnergyIfEaten;
    }

    private Cell position;

    private Map<String, Integer> chanceToEat = new HashMap<>() {{
    }};

    @Override
    public int getEnergyCapacity() {
        return energyCapacity;
    }

    @Override
    public boolean getBreedableStatus() {
        return false;
    }

    @Override
    public void setBreedableStatus(boolean status) {

    }

    @Override
    public void setEnergyCapacity(int energyCapacity) {
        this.energyCapacity = energyCapacity;
    }

    @Override
    public int getGivenEnergyIfEaten() {
        return givenEnergyIfEaten;
    }

    @Override
    public Animal getNewAnimal(FieldInitialization fieldInitialization) {
        return null;
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
    public Map<String, Integer> getChanceToEatList() {
        return chanceToEat;
    }

    @Override
    public String getIcon() {
        return "🥦";
    }

    @Override
    public String toString() {
        return "grass";
    }
}
