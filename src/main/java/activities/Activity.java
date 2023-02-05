package activities;

import com.gmail.shaltynovm.project2.animals.Animal;
import com.gmail.shaltynovm.project2.field.Cell;
import com.gmail.shaltynovm.project2.field.Island;
import config.AnimalConfig;

public class Activity {
    Island island;
    AnimalConfig animalConfig;
    public Activity(Island island, AnimalConfig animalConfig) {
        this.island = island;
        this.animalConfig=animalConfig;
    }

    public void consumeEnergy() {
        int i = 0;
        Cell[][] cells = island.islandGrid;
        for (int x = 0; x < island.xDimension; x++) {
            for (int y = 0; y < island.yDimension; y++) {
                for (Animal animal : cells[x][y].getAnimalList()) {
                    animal.getEnergyCapacity();
                    int energyConsumption = animalConfig.getEnergyConsumptionFromTXT(animal);
                    animal.setEnergyCapacity(animal.getEnergyCapacity() - energyConsumption);
                    if (i == 2) {
                        animal.setBreedableStatus(true);
                        i = 0;
                    } else {
                        i++;
                    }
                }
            }
        }
    }
}
