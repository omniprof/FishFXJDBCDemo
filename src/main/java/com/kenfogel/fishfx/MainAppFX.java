package com.kenfogel.fishfx;

import java.io.IOException;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import com.kenfogel.fishfx.persistence.FishDAO;
import com.kenfogel.fishfx.persistence.FishDAOImpl;
import com.kenfogel.fishfx.controller.FishFXTableController;

/**
 * This is a demo of combining JDBC and JavaFX
 *
 * @author Ken
 */
public class MainAppFX extends Application {

    private final Logger log = LoggerFactory.getLogger(this.getClass()
            .getName());

    private Stage primaryStage;
    private AnchorPane rootLayout;
    private final FishDAO fishDAO;

    public MainAppFX() {
        super();
        fishDAO = new FishDAOImpl();
    }

    /**
     * The application starts here by configuring the Stage
     *
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Fish Table");

        // Set the application icon using getResourceAsStream.
        this.primaryStage.getIcons().add(
                new Image(MainAppFX.class
                        .getResourceAsStream("/images/bluefish_icon.png")));

        initRootLayout();
        primaryStage.show();
    }

    /**
     * The fxml and, if present, the css file are loaded and control passes to
     * the controller.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainAppFX.class
                    .getResource("/fxml/FishFXTable.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            FishFXTableController controller = loader.getController();

            if (controller == null) {
                log.warn("Bad controller");
            } else {
                controller.loadUpTheFishies(fishDAO);
            }

        } catch (IOException | SQLException e) {
            log.error("Error display table", e);
        }
    }

    /**
     * Where is always begins
     *
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
        System.exit(0);
    }
}
