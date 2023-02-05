package config;

import com.gmail.shaltynovm.project2.animals.Animal;
import com.gmail.shaltynovm.project2.field.FieldInitialization;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AnimalConfig {
FieldInitialization fieldInitialization;

    public AnimalConfig(FieldInitialization fieldInitialization) {
        this.fieldInitialization = fieldInitialization;
    }

    public int getDataFromTXT(String string, int column) {
        int returnNumber = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("src/main/resources/animalBase.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                try {
                    line = br.readLine();
                    if (line == null) {
                        break;
                    }

                    if (line.contains(string)) {
                        String[] words = line.split("\\W+");
                        returnNumber = Integer.parseInt(words[column].toString());
                    }

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            String everything = sb.toString();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return returnNumber;
    }

    public int getEnergyConsumptionFromTXT(Animal animal) {
        int energyCapacity = 0;
        for (Map.Entry<Animal, String> stringAnimal : fieldInitialization.getAnimalStringMap().entrySet()) {
            if (stringAnimal.getKey().toString().equals(animal.toString())) {
                energyCapacity = getDataFromTXT(stringAnimal.getValue(), 4);
            }
        }
        return energyCapacity;
    }

    public Map<Animal, Integer> getChanceToAppearFromTXT() {
        HashMap<Animal, Integer> mapFromTXT = new HashMap<>();
        for (Map.Entry<Animal, String> entry : fieldInitialization.getAnimalStringMap().entrySet()) {
            String str = entry.getKey().toString();
            mapFromTXT.put(entry.getKey(), getDataFromTXT(str, 1));
        }
        return mapFromTXT;
    }

    public int getSpeedFromString(Animal currentAnimal) {
        int speed = 0;
        for (Map.Entry<Animal, String> stringAnimal : fieldInitialization.getAnimalStringMap().entrySet()) {
            if (stringAnimal.getKey().toString().equals(currentAnimal.toString())) {
                speed = getDataFromTXT(stringAnimal.getValue(), 2);
            }
        }
        return speed;
    }
    public boolean calculateChanceToEmerge(int chancePercentage) {
        Random random = new Random();
        int randomChance = random.nextInt(100);
        return randomChance < chancePercentage;
    }

}
