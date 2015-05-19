package com.kenfogel.fishfx.controller;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import com.kenfogel.fishfx.model.FishData;
import com.kenfogel.fishfx.persistence.FishDAO;

public class FishFXTableController {

    private final Logger log = LoggerFactory.getLogger(this.getClass()
            .getName());

    //private FishDAO fishDAO;
    @FXML
    private AnchorPane fishFXTable;
    @FXML
    private TableView<FishData> fishDataTable;
    @FXML
    private TableColumn<FishData, Number> idColumn;
    @FXML
    private TableColumn<FishData, String> commonNameColumn;
    @FXML
    private TableColumn<FishData, String> latinColumn;
    @FXML
    private TableColumn<FishData, String> phColumn;
    @FXML
    private TableColumn<FishData, String> khColumn;
    @FXML
    private TableColumn<FishData, String> tempColumn;
    @FXML
    private TableColumn<FishData, String> fishSizeColumn;
    @FXML
    private TableColumn<FishData, String> speciesOriginColumn;
    @FXML
    private TableColumn<FishData, String> tankSizeColumn;
    @FXML
    private TableColumn<FishData, String> stockingColumn;
    @FXML
    private TableColumn<FishData, String> dietColumn;

    /**
     * The constructor. The constructor is called before the initialize()
     * method.
     */
    public FishFXTableController() {
        super();
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        connectTableColumnsToFishData();
        calculateAndSetColumWidths();
    }

    /**
     * Given a reference to the DAO object, load all the fish into the table
     *
     * @param fishDAO
     * @throws SQLException
     */
    public void loadUpTheFishies(FishDAO fishDAO) throws SQLException {
        // Add observable list data to the table
        fishDataTable.setItems(fishDAO.findAll());

    }

    /**
     * Private method that sets the column factory to the property type of the
     * FishData
     */
    private void connectTableColumnsToFishData() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().getIdProperty());
        commonNameColumn.setCellValueFactory(cellData -> cellData.getValue().getCommonNameProperty());
        latinColumn.setCellValueFactory(cellData -> cellData.getValue().getLatinProperty());
        phColumn.setCellValueFactory(cellData -> cellData.getValue().getPhProperty());
        khColumn.setCellValueFactory(cellData -> cellData.getValue().getKhProperty());
        tempColumn.setCellValueFactory(cellData -> cellData.getValue().getTempProperty());
        fishSizeColumn.setCellValueFactory(cellData -> cellData.getValue().getFishSizeProperty());
        speciesOriginColumn.setCellValueFactory(cellData -> cellData.getValue().getSpeciesOriginProperty());
        tankSizeColumn.setCellValueFactory(cellData -> cellData.getValue().getTankSizeProperty());
        stockingColumn.setCellValueFactory(cellData -> cellData.getValue().getStockingProperty());
        dietColumn.setCellValueFactory(cellData -> cellData.getValue().getDietProperty());
    }

    /**
     * Private method that sets the widths of each column by assigning a
     * percentage of the total width
     */
    private void calculateAndSetColumWidths() {
        //fishDataTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        double width = fishFXTable.getPrefWidth();
        idColumn.setPrefWidth(width * .05);
        commonNameColumn.setPrefWidth(width * .15);
        latinColumn.setPrefWidth(width * .15);
        phColumn.setPrefWidth(width * .05);
        khColumn.setPrefWidth(width * .05);
        tempColumn.setPrefWidth(width * .05);
        fishSizeColumn.setPrefWidth(width * .1);
        speciesOriginColumn.setPrefWidth(width * .1);
        tankSizeColumn.setPrefWidth(width * .1);
        stockingColumn.setPrefWidth(width * .1);
        dietColumn.setPrefWidth(width * .1);

        log.info("Stage width is " + fishFXTable.getPrefWidth());
    }
}
