package com.example.ejercicioe;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal de la aplicación JavaFX.
 * Esta clase se encarga de inicializar y mostrar la ventana principal de la aplicación,
 * cargando el archivo FXML correspondiente a la interfaz de usuario.
 */
public class HelloApplication extends Application {

    /*
     * Método que se llama al iniciar la aplicación.
     * Carga el archivo FXML, establece la escena y muestra la ventana.
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EjercicioE.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Image icon = new Image(getClass().getResourceAsStream("/Imagenes/agenda.png"));
        stage.getIcons().add(icon);
        stage.setTitle("Personas");
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método principal de la aplicación.
     * Inicia la ejecución de la aplicación JavaFX.
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        launch();
    }
}