/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.smg.farm.services;

import bg.smg.farm.util.DBManager;
import bg.smg.farm.frames.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bg.smg.farm.model.Animal;
import java.util.ArrayList;
import java.sql.Date;
/**
 *
 * @author smgCCFE
 */
public class AnimalService implements AnimalServiceI{
    private final DataSource dataSource;
    private Connection connection;
    private ArrayList<AnimalPrevieww> listOfObjects = new ArrayList<>();

    
    public AnimalService() throws SQLException {
        dataSource = DBManager.getInstance().getDataSource();
    }
    
    @Override
    public Animal getAnimal(int id){
        return null;
    }
    
    @Override
    public Animal getAllAnimals() throws SQLException {
        
        try {
            
            this.connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM cow")) {
                ResultSet resultSet = statement.executeQuery();
                Animal animal = new Animal();
                while(resultSet.next()){
                animal.setName(resultSet.getString("name"));
                animal.setWeight(resultSet.getInt("weight"));
                animal.setPictureName(resultSet.getString("pictureName"));
                AnimalPrevieww pr = new AnimalPrevieww(animal.getName(), animal.getWeight(), animal.getPictureName());
                listOfObjects.add(pr);
                pr.setVisible(true);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("Closing database connection...");
                connection.close();
                System.out.println("Connection valid: " + connection.isValid(5));
            }
        }
        return null;
    }
    
    @Override
     public Animal setAllAnimals(String name, String weight, String pictureName, String dateOfBirth) throws SQLException{
         try {
            
            this.connection = dataSource.getConnection();
            Animal animal = new Animal();
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO cow(name, weight, pictureName, dateOfbirth)" 
                            + "VALUES('" + name + "','" 
                                         + weight + "','" 
                                         + pictureName + "','" 
                                         + dateOfBirth + "')" )) {
                statement.setString(1, name);
                statement.setInt(2, Integer.parseInt(weight));
                statement.setString(3, pictureName);
                System.out.println(animal.getPictureName());
                statement.setDate(4, Date.valueOf(dateOfBirth));
                statement.executeQuery();
               
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (connection != null) {
                System.out.println("Closing database connection...");
                connection.close();
                System.out.println("Connection valid: " + connection.isValid(5));
            }
        }
         
         
        return null;
     }
    
    public void closePreviews() {
        listOfObjects.forEach((n) -> n.setVisible(false));
    }
}
