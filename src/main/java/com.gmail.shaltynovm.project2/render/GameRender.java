package com.gmail.shaltynovm.project2.render;

import com.gmail.shaltynovm.project2.animals.Animal;
import com.gmail.shaltynovm.project2.field.Cell;
import com.gmail.shaltynovm.project2.field.Island;

public class GameRender {

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
}

