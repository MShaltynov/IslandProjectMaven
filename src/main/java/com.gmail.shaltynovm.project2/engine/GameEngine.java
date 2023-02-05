package com.gmail.shaltynovm.project2.engine;

import com.gmail.shaltynovm.project2.animals.Animal;
import com.gmail.shaltynovm.project2.field.InitialField;
import com.gmail.shaltynovm.project2.field.Island;
import com.gmail.shaltynovm.project2.render.GameRender;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private int delay;
    private GameRender gameRender;
    Island island;
    int dayNumber = 1;
    InitialField initialField;

    public GameEngine(int delay, Island island, GameRender gameRender) {
        this.delay = delay;
        this.island = island;
        this.gameRender = gameRender;
        initialField = new InitialField(island);
    }

    public void start() {
        initialField.populateIsland(island);
        System.out.println("===============================================================================");
        System.out.println("Initial map. Animal amount= " + gameRender.getTotalAmount(island));
        gameRender.printMap(island);
        while (island.getAllAnimals().stream().count() > 0) {
            try {
                long startTime = System.currentTimeMillis();
                System.out.println("===============================================================================");
                System.out.println("Day: " + dayNumber + ", Animal amount= " + gameRender.getTotalAmount(island));
                nextDay();
                gameRender.printMap(island);
                Thread.sleep(delay);
                dayNumber++;
                System.out.println("Cycle duration: " + (System.currentTimeMillis() - startTime) + "ms");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\uD83C\uDFC1 Game is finished!");
    }

    private void nextDay() {
        long startTime = System.currentTimeMillis();
        List<Animal> newAnimals = new ArrayList<>();
        for (Animal currentAnimal : island.getAllAnimals()
        ) {
            currentAnimal.move(initialField.getSpeedFromString(currentAnimal));
            currentAnimal.eat();
            currentAnimal.checkEnergy();
            int maxEnergy = initialField.getDataFromTXT(currentAnimal.toString(), 3);
            boolean chanceToAppear = initialField.calculateChanceToEmerge(initialField.getDataFromTXT(currentAnimal.toString(), 1));
            newAnimals.addAll(currentAnimal.breedingProcess(chanceToAppear, maxEnergy));
        }
        System.out.print("\uD83D\uDC76New com.gmail.shaltynovm.project2.animals: ");
        System.out.print(newAnimals.size() + "pcs: ");
        newAnimals.forEach(animal -> {
            System.out.print(animal.getIcon());
        });
        System.out.println("\nMoving and eating time: " + (System.currentTimeMillis() - startTime) + "ms");

        initialField.printEatenAnimals();
        //initialField.breed();
        startTime = System.currentTimeMillis();
        initialField.consumeEnergy();
        System.out.println("Set energy after day time: " + (System.currentTimeMillis() - startTime) + "ms");
    }


}
