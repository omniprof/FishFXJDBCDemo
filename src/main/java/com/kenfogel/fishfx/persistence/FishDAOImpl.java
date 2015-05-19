/**
 * This class contains some basic JDBC methods. When retrieving all the data it
 * is returned as an ObservableList so that it is suitable for use in an a
 * JavaFX control such as a Table. Added logging Changed read to 3 methods,
 * findAll, findID and findDiet Added retrieving the auto increment primary key
 * in the create method
 *
 * @author Ken Fogel
 * @version 1.8
 */
package com.kenfogel.fishfx.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.kenfogel.fishfx.model.FishData;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements the FishDAO interface
 *
 * Exceptions are possible whenever a JDBC object is used. When these exceptions
 * occur it should result in some message appearing to the user and possibly
 * some corrective action. To simplify this, all exceptions are thrown and not
 * caught here. The methods that you write to call any of these methods must
 * either use a try/catch or continue throwing the exception.
 *
 * Updated by creating a new method createFishData that used the resultSet to
 * create an object. Originally this code was repeated three times.
 *
 * @author Ken
 * @version 1.6
 *
 */
public class FishDAOImpl implements FishDAO {

    private final Logger log = LoggerFactory.getLogger(this.getClass()
            .getName());

    private final String url = "jdbc:mysql://localhost:3306/Aquarium";
    private final String user = "fish";
    private final String password = "fish";

    public FishDAOImpl() {
        super();
    }

    /**
     * Retrieve one record from the given table based on the primary key
     *
     * @param id
     * @return The FishData object
     * @throws java.sql.SQLException
     */
    @Override
    public FishData findID(int id) throws SQLException {

        // If there is no record with the desired id then this will be returned
        // as a null pointer
        FishData fishData = null;

        String selectQuery = "SELECT ID, COMMONNAME, LATIN, PH, KH, TEMP, FISHSIZE, SPECIESORIGIN, TANKSIZE, STOCKING, DIET FROM FISH WHERE ID = ?";

        // Using try with resources, available since Java 1.7
        // Class that implement the Closable interface created in the
        // parenthesis () will be closed when the block ends.
        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                // You must use PreparedStatements to guard against SQL Injection
                PreparedStatement pStatement = connection
                .prepareStatement(selectQuery);) {
            // Only object creation statements can be in the parenthesis so
            // first try-with-resources block ends
            pStatement.setInt(1, id);
            // A new try-with-resources block for creating the ResultSet object
            // begins
            try (ResultSet resultSet = pStatement.executeQuery()) {
                if (resultSet.next()) {
                    fishData = createFishData(resultSet);
                }
            }
        }
        log.info("Found " + id + "?: " + (fishData != null));
        return fishData;
    }

    /**
     * Retrieve all the records for the given table and returns the data as an
     * ObservableList of FishData objects for use in a JavaFX control such as a
     * table
     *
     * @return The ObservableList of FishData objects
     * @throws java.sql.SQLException
     */
    @Override
    public ObservableList<FishData> findAll() throws SQLException {

        ObservableList<FishData> fishiesData = FXCollections
                .observableArrayList();

        String selectQuery = "SELECT ID, COMMONNAME, LATIN, PH, KH, TEMP, FISHSIZE, SPECIESORIGIN, TANKSIZE, STOCKING, DIET FROM FISH";

        try (Connection connection = DriverManager.getConnection(url, user, password);
                // You must use PreparedStatements to guard against SQL Injection
                PreparedStatement pStatement = connection.prepareStatement(selectQuery);
                ResultSet resultSet = pStatement.executeQuery()) {
            while (resultSet.next()) {
                fishiesData.add(createFishData(resultSet));
            }
        }
        log.info("# of records found : " + fishiesData.size());
        return fishiesData;
    }

    /**
     * Retrieve all the records from the given table that share the same value
     * in the Diet column and returns the data as an arraylist of FishData
     * objects
     *
     * @param diet
     * @return The arraylist of FishData objects
     * @throws java.sql.SQLException
     */
    @Override
    public ObservableList<FishData> findAllDiet(String diet) throws SQLException {

        ObservableList<FishData> fishiesData = FXCollections
                .observableArrayList();

        String selectQuery = "SELECT ID, COMMONNAME, LATIN, PH, KH, TEMP, FISHSIZE, SPECIESORIGIN, TANKSIZE, STOCKING, DIET FROM FISH WHERE DIET = ?";

        // Using try with resources, available since Java 1.7
        // Class that implement the Closable interface created in the
        // parenthesis () will be closed when the block ends.
        try (Connection connection = DriverManager.getConnection(url, user, password);
                // You must use PreparedStatements to guard against SQL Injection
                PreparedStatement pStatement = connection.prepareStatement(selectQuery);) {
            // Only object creation statements can be in the parenthesis so
            // first try-with-resources block ends
            pStatement.setString(1, diet);
            // A new try-with-resources block begins for creating the ResultSet
            // object
            try (ResultSet resultSet = pStatement.executeQuery()) {
                while (resultSet.next()) {
                    fishiesData.add(createFishData(resultSet));
                }
            }
        }
        log.info("# of records found : " + fishiesData.size());
        return fishiesData;
    }

    /**
     * Private method to create FishData objects from the current position in a
     * ResultSet
     *
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private FishData createFishData(ResultSet resultSet) throws SQLException {
        FishData fishData = new FishData();
        fishData.setCommonName(resultSet.getString("COMMONNAME"));
        fishData.setDiet(resultSet.getString("DIET"));
        fishData.setKh(resultSet.getString("KH"));
        fishData.setLatin(resultSet.getString("LATIN"));
        fishData.setPh(resultSet.getString("PH"));
        fishData.setFishSize(resultSet.getString("FISHSIZE"));
        fishData.setSpeciesOrigin(resultSet.getString("SPECIESORIGIN"));
        fishData.setStocking(resultSet.getString("STOCKING"));
        fishData.setTankSize(resultSet.getString("TANKSIZE"));
        fishData.setTemp(resultSet.getString("TEMP"));
        fishData.setId(resultSet.getInt("ID"));
        System.out.println(fishData.toString());
        return fishData;
    }

    /**
     * This method adds a FishData object as a record to the database. The
     * column list does not include ID as this is an auto increment value in the
     * table. The value generated by MySQL is then retrieved and written to the
     * FishData object.
     *
     * @param fishData
     * @return The number of records created, should always be 1
     * @throws SQLException
     */
    @Override
    public int create(FishData fishData) throws SQLException {

        int result;
        String createQuery = "INSERT INTO FISH (COMMONNAME, LATIN, PH, KH, TEMP, FISHSIZE, SPECIESORIGIN, TANKSIZE, STOCKING, DIET) VALUES (?,?,?,?,?,?,?,?,?,?)";

        // Connection is only open for the operation and then immediately closed
        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                // Using a prepared statement to handle the conversion
                // of special characters in the SQL statement and guard against SQL
                // Injection
                PreparedStatement ps = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, fishData.getCommonName());
            ps.setString(2, fishData.getLatin());
            ps.setString(3, fishData.getPh());
            ps.setString(4, fishData.getKh());
            ps.setString(5, fishData.getTemp());
            ps.setString(6, fishData.getFishSize());
            ps.setString(7, fishData.getSpeciesOrigin());
            ps.setString(8, fishData.getTankSize());
            ps.setString(9, fishData.getStocking());
            ps.setString(10, fishData.getDiet());

            result = ps.executeUpdate();

            // Get the generated auto increment primary key
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                fishData.setId(rs.getInt(1));
            }
        }
        log.info("# of records created : " + result);
        return result;
    }

    /**
     * This method deletes a single record based on the criteria of the primary
     * key field ID value. It should return either 0 meaning that there is no
     * record with that ID or 1 meaning a single record was deleted. If the
     * value is greater than 1 then something unexpected has happened. A
     * criteria other than ID may delete more than one record.
     *
     * @param id
     * @par @return The number of records deleted, should be 0 or 1
     * @throws SQLException
     */
    @Override
    public int delete(int id) throws SQLException {

        int result;

        String deleteQuery = "DELETE FROM FISH WHERE ID = ?";

        // Connection is only open for the operation and then immediately closed
        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                // You must use PreparedStatements to guard against SQL Injection
                PreparedStatement ps = connection.prepareStatement(deleteQuery);) {
            ps.setInt(1, id);
            result = ps.executeUpdate();
        }
        log.info("# of records deleted : " + result);
        return result;
    }

    /**
     * This method will update all the fields of a record except ID. Usually
     * updates are tied to specific fields and so only those fields need appear
     * in the SQL statement.
     *
     * @param fishData An object with an existing ID and new data in the fields
     * @return The number of records updated, should be 0 or 1
     * @throws SQLException
     *
     */
    @Override
    public int update(FishData fishData) throws SQLException {

        int result;

        String updateQuery = "UPDATE FISH SET COMMONNAME=?, LATIN=?, PH=?, KH=?, TEMP=?, FISHSIZE=?, SPECIESORIGIN=?, TANKSIZE=?, STOCKING=?, DIET=? WHERE ID = ?";

        // Connection is only open for the operation and then immediately closed
        try (Connection connection = DriverManager.getConnection(url, user,
                password);
                // You must use a prepared statement to handle the conversion
                // of special characters in the SQL statement and guard against SQL
                // Injection
                PreparedStatement ps = connection.prepareStatement(updateQuery);) {
            ps.setString(1, fishData.getCommonName());
            ps.setString(2, fishData.getLatin());
            ps.setString(3, fishData.getPh());
            ps.setString(4, fishData.getKh());
            ps.setString(5, fishData.getTemp());
            ps.setString(6, fishData.getFishSize());
            ps.setString(7, fishData.getSpeciesOrigin());
            ps.setString(8, fishData.getTankSize());
            ps.setString(9, fishData.getStocking());
            ps.setString(10, fishData.getDiet());

            result = ps.executeUpdate();
        }
        log.info("# of records updated : " + result);
        return result;
    }
}
