package com.gmail.shaltynovm.project2.field;

import com.gmail.shaltynovm.project2.animals.Animal;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    public int x;
    public int y;


    public List<Animal> getAnimalList() {
        return animalList;
    }

    List<Animal> animalList;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.animalList = new ArrayList<>();
    }

    public void removeAnimal(Animal animal) {
        animalList.remove(animal);
    }

    public void addAnimal(Animal animal) {
        animalList.add(animal);
    }

    @Override
    public String toString() {
        return "x|y=" + x +
                "|" + y+" ";
    }
}
