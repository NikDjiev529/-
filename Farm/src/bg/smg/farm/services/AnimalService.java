/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bg.smg.farm.services;

import bg.smg.farm.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bg.smg.farm.model.User;
import bg.smg.farm.util.DBManager;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import bg.smg.farm.model.Animal;
/**
 *
 * @author smgCCFE
 */
public class AnimalService implements AnimalServiceI{
    private DataSource dataSource;
    private Connection connection;

    public AnimalService() throws SQLException {
        dataSource = DBManager.getInstance().getDataSource();
    }
    
    @Override
    public Animal getAnimal(int id){
        return null;
    }
    
    @Override
    public Animal getAnimalsByName(String name) throws SQLException {
        try {
            
            this.connection = dataSource.getConnection();
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM cow WHERE name=?")) {
                statement.setString(1, name);
                ResultSet resultSet = statement.executeQuery();
                resultSet.first();
                Animal animal = new Animal(name);
                animal.setName(resultSet.getString("name"));
                animal.setWeight(resultSet.getInt("weight"));
                return animal;
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
}
