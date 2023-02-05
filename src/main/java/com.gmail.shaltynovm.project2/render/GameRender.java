package com.gmail.shaltynovm.project2.render;

import com.gmail.shaltynovm.project2.animals.Animal;
import com.gmail.shaltynovm.project2.field.Cell;
import com.gmail.shaltynovm.project2.field.Island;

import java.util.ArrayList;
import java.util.List;

public class GameRender {
    private  List<Animal> eatenAnimals = new ArrayList<>();

    public void addEatenAnimals(Animal animal) {
        eatenAnimals.add(animal);
    }

    public List<Animal> getEatenAnimals() {
        return eatenAnimals;
    }

    public int longestCell(Island island) {
        int cellLength=0;
        Cell[][] array = island.islandGrid;
        for (int i = 0; i < island.xDimension; i++) {
            for (int j = 0; j < island.yDimension; j++) {
                if (cellLength<array[i][j].getAnimalList().size()){
                    cellLength=array[i][j].getAnimalList().size();
                }
            }
        }
        return cellLength;
    }

    public void printMap(Island island) {
        int cellLength=longestCell(island);
        Cell[][] array = island.islandGrid;
        for (int i = 0; i < island.xDimension; i++) {
            for (int j = 0; j < island.yDimension; j++) {
                System.out.print("x,y: " + i + " " + j);
                int countSpace=0;
                for (Animal animal : array[i][j].getAnimalList()) {
                    System.out.print(animal.getIcon());
                    countSpace++;
                }
                while (countSpace<cellLength){
                    System.out.print("  ");
                    countSpace++;
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }

    public int getTotalAmount(Island island) {
        int animalCount = 0;
        Cell[][] array = island.islandGrid;
        for (int i = 0; i < island.xDimension; i++) {
            for (int j = 0; j < island.yDimension; j++) {
                for (Animal animal : array[i][j].getAnimalList()) {
                    animalCount++;
                }
            }
        }
        return animalCount;
    }
    public void printEatenAnimals() {
        System.out.print("\uD83C\uDF57Eaten  animals: ");
        System.out.print(eatenAnimals.size() + "pcs: ");
        for (Animal animal : eatenAnimals
        ) {
            System.out.print(animal.getIcon());
        }
        System.out.println();
        eatenAnimals.clear();
    }
}

