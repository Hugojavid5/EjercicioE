package com.example.ejercicioe;

import Model.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class PersonaController {

    @FXML
    private Button btt_agregar;

    @FXML
    private TableColumn<Persona, String> c_Nombre;

    @FXML
    private TableColumn<Persona, String> c_apellidos;

    @FXML
    private TableColumn<Persona, Integer> c_edad;

    @FXML
    private TableView<Persona> tablaPersonas;
    @FXML
    private Button modificarButton;

    @FXML
    private Button eliminarButton;

    @FXML
    void agregar(ActionEvent event) throws IOException {
        // Carga el archivo FXML de la nueva ventana
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NuevaPersona.fxml"));
        Parent root = fxmlLoader.load();

        // Obtiene el controlador de la nueva ventana
        NuevaPersonaController NuevaPersonaController = fxmlLoader.getController();
        // Pasa la referencia de la tabla al controlador
        NuevaPersonaController.setTablaPersonas(tablaPersonas);

        // Crea una nueva escena con el contenido cargado
        Scene scene = new Scene(root);

        // Crea un nuevo Stage para la nueva ventana
        Stage stage = new Stage();
        stage.setTitle("Nueva Persona");
        stage.setScene(scene);

        // Evitar que la ventana se redimensione
        stage.setResizable(false);

        // Establece la modalidad para que la ventana sea modal respecto a la ventana principal
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void initialize() {
        c_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        c_apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        c_edad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }

}