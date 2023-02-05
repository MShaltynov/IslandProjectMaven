package com.gmail.shaltynovm.project2.field;

import com.gmail.shaltynovm.project2.animals.Animal;

import java.util.ArrayList;

public class Island {
    public Cell[][] islandGrid;
    public final int xDimension;
    public final int yDimension;

    public Island(int xDimension, int yDimension) {
        this.xDimension = xDimension;
        this.yDimension = yDimension;
        this.islandGrid = new Cell[xDimension][yDimension];
        for (int x = 0; x < xDimension; x++) {
            for (int y = 0; y < yDimension; y++) {
                islandGrid[x][y] = new Cell(x, y);
            }
        }
    }

    public ArrayList<Animal> getAllAnimals() {
        ArrayList<Animal> animalArrayList = new ArrayList<>();
        for (int x = 0; x < xDimension ; x++) {
            for (int y = 0; y < yDimension ; y++) {
                for (Animal animal : islandGrid[x][y].getAnimalList()) {
                    animalArrayList.add(animal);
                }
            }
        }
        return animalArrayList;
    }
}



