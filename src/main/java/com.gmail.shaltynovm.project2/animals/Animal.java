package com.gmail.shaltynovm.project2.animals;

import com.gmail.shaltynovm.project2.field.Cell;
import com.gmail.shaltynovm.project2.field.FieldInitialization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public abstract class Animal {
    public abstract Map<String, Integer> getChanceToEatList();

    public abstract void setPosition(Cell positionCell);

    public abstract Cell getPosition();

    public abstract int getEnergyCapacity();

    public abstract boolean getBreedableStatus();

    public abstract void setBreedableStatus(boolean status);

    public abstract void setEnergyCapacity(int energyCapacity);

    public abstract int getGivenEnergyIfEaten();

    public abstract Animal getNewAnimal(FieldInitialization fieldInitialization);

    FieldInitialization fieldInitialization;

    public Animal(FieldInitialization fieldInitialization) {
        this.fieldInitialization = fieldInitialization;
    }

    public void move(int distance) {
        Random moveDecider = new Random();
        for (int i = 0; i < distance; i++) {
            boolean moveDecision = moveDecider.nextBoolean();
            if (moveDecision) {
                //System.out.print(getIcon() + "Animal will move to other cell");
                moveToOtherCell();
            } else {
            }
        }
    }

    private void moveToOtherCell() {
        Random directionPicker = new Random();
        Direction direction;
        Direction[] directions = Direction.values();
        do {
            direction = directions[directionPicker.nextInt(directions.length)];
        } while (!directionValid(direction));
        changePosition(direction);
    }

    private boolean directionValid(Direction direction) {
        int xPosition = getPosition().x;
        int yPosition = getPosition().y;
        switch (direction) {
            case UP -> {
                return yPosition - 1 >= 0;
            }
            case DOWN -> {
                return yPosition + 1 < fieldInitialization.getIsland().yDimension;
            }
            case LEFT -> {
                return xPosition - 1 >= 0;
            }
            case RIGHT -> {
                return xPosition + 1 < fieldInitialization.getIsland().xDimension;
            }
            default -> throw new IllegalArgumentException("IllegalAccessException");
        }
    }

    private void changePosition(Direction direction) {
        int newX = 0;
        int newY = 0;
        switch (direction) {
            case UP -> {
                newX = getPosition().x;
                newY = getPosition().y - 1;
            }
            case DOWN -> {
                newX = getPosition().x;
                newY = getPosition().y + 1;
            }
            case LEFT -> {
                newX = getPosition().x - 1;
                newY = getPosition().y;
            }
            case RIGHT -> {
                newX = getPosition().x + 1;
                newY = getPosition().y;
            }
        }
        Cell newCell = fieldInitialization.getIsland().islandGrid[newX][newY];

        removeAnimal(this);
        this.setPosition(newCell);
        this.getPosition().addAnimal(this);
    }

    private void removeAnimal(Animal animal) {
        this.getPosition().removeAnimal(animal);
    }

    public void eat() {
        eatingProcess();
    }

    public boolean eatingProcess() {
        for (int x = 0; x < fieldInitialization.getIsland().xDimension; x++) {
            for (int y = 0; y < fieldInitialization.getIsland().yDimension; y++) {
                List<Animal> victimList = fieldInitialization.getIsland().islandGrid[x][y].getAnimalList();
                if (findVictim(victimList)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void swallow(Animal actualAnimal) {
        int actualEnergyEaten = actualAnimal.getGivenEnergyIfEaten();
        int newEnergy = this.getEnergyCapacity() + actualEnergyEaten;
        int maxEnergy = fieldInitialization.getAnimalConfig().getDataFromTXT(actualAnimal.toString(), 3);
        if (newEnergy > maxEnergy) {
            setEnergyCapacity(maxEnergy);
        } else {
            setEnergyCapacity(newEnergy);
        }
    }

    private boolean findVictim(List<Animal> animalsInCell) {
        for (Animal actualAnimal : animalsInCell) {
        for (Map.Entry eatableAnimal : getChanceToEatList().entrySet()) {
                if (actualAnimal.toString().equals(eatableAnimal.getKey().toString())) {
                    if (calculateChance(Integer.parseInt(eatableAnimal.getValue().toString()))) {
                        swallow(this);
                        if (!actualAnimal.getClass().equals(Grass.class)) {
                            fieldInitialization.getGameRender().addEatenAnimals(actualAnimal);
                            Cell findPosition = actualAnimal.getPosition();
                            findPosition.removeAnimal(actualAnimal);
                            growGrass(findPosition);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void growGrass(Cell position) {
        boolean findGrass = false;
        for (Animal animal : position.getAnimalList()) {
            if (animal.getClass().equals(Grass.class)) {
                findGrass = true;
                setEnergyToGrass(animal.getPosition(), animal);
                break;
            }
        }
        if (!findGrass) {
            Animal newAnimal = new Grass(fieldInitialization, 10, 10);
            position.addAnimal(newAnimal);
            newAnimal.setPosition(position);
        }
    }

    private void setEnergyToGrass(Cell position, Animal animal) {
        animal.setEnergyCapacity(10);
    }

    private boolean calculateChance(int probability) {
        Random random = new Random();
        int randomChance = random.nextInt(100);
        return randomChance < probability;

    }

    public void checkEnergy() {
        if (getEnergyCapacity() < 0) {
            removeAnimal(this);
            if (!this.getClass().equals(Grass.class)) {
                System.out.println("\uD83D\uDC80" + "Animal " + getIcon() + this + " died from starving in cell:" + this.getPosition());
                growGrass(this.getPosition());
            }
        }
    }

    public Animal breed(Cell position) {
        Animal newAnimal = getNewAnimal(fieldInitialization);
        position.addAnimal(newAnimal);
        newAnimal.setPosition(position);
        newAnimal.setBreedableStatus(false);
        return newAnimal;
    }

    public List<Animal> breedingProcess(boolean chanceToAppear, int maxEnergy) {
        List<Animal> breedList = new ArrayList<>();
        for (Animal animal : getPosition().getAnimalList()) {
            if (this.getClass().equals(animal.getClass()) &&
                    (!this.equals(animal)) &&
                    (this.getBreedableStatus()) &&
                    (animal.getBreedableStatus()) &&
                    chanceToAppear &&
                    checkEnergy(animal, this, maxEnergy)) {
                breedList.add(animal);
                this.setBreedableStatus(false);
                animal.setBreedableStatus(false);
            }
        }

        breedList.forEach(animal -> {
            animal.breed(animal.getPosition());
        });
        return breedList;
    }

    private boolean checkEnergy(Animal maleAnimal, Animal femaleAnimal, int maxEnergy) {
        int middleEnergy = (maxEnergy / 2);
        if ((maleAnimal.getEnergyCapacity() > middleEnergy) && (femaleAnimal.getEnergyCapacity() > middleEnergy)) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public String toString() {
        return super.toString();
    }

    public String getIcon() {
        return "";
    }
}




