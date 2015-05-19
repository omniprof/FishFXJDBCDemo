package com.kenfogel.fishfx.persistence;

import com.kenfogel.fishfx.model.FishData;

import java.sql.SQLException;

import javafx.collections.ObservableList;

/**
 * Interface for CRUD methods
 *
 * @author Ken
 */
public interface FishDAO {

    // Create
    public int create(FishData fishData) throws SQLException;

    // Read
    public FishData findID(int id) throws SQLException;

    public ObservableList<FishData> findAll() throws SQLException;

    public ObservableList<FishData> findAllDiet(String diet) throws SQLException;

    // Update
    public int update(FishData fishData) throws SQLException;

    // Delete
    public int delete(int ID) throws SQLException;
}
