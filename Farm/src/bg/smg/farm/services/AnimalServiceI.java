package bg.smg.farm.services;

import bg.smg.farm.model.Animal;
import java.sql.SQLException;

public interface AnimalServiceI {
    public Animal getAnimal(int id);
    public Animal getAllAnimals() throws SQLException;  
    public Animal setAllAnimals(String name, String weight, String pictureName, String dateOfBirth) throws SQLException; 
    
}
