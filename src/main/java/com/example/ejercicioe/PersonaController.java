package com.example.ejercicioe;

import Model.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controlador para gestionar la interfaz de la tabla de personas.
 * Permite agregar, modificar y eliminar personas de la lista.
 */
public class PersonaController {

    @FXML
    private Button btt_agregar;

    @FXML
    private TableColumn<Persona, String> c_nombre;

    @FXML
    private TableColumn<Persona, String> c_apellidos;

    @FXML
    private TableColumn<Persona, Integer> c_edad;

    @FXML
    private TableView<Persona> tablaPersonas;

    @FXML
    private Button btt_modificar;

    @FXML
    private Button btt_eliminar;

    /** Lista observable de personas para mostrar en la tabla */
    private ObservableList<Persona> personasList = FXCollections.observableArrayList();

    /**
     * Inicializa la tabla y las columnas al cargar la vista.
     * Establece los valores de las columnas para que correspondan a los atributos de la clase Persona.
     */
    @FXML
    void initialize() {
        c_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        c_apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        c_edad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        // Vincula la lista observable a la tabla
        tablaPersonas.setItems(personasList);
    }

    /**
     * Abre una nueva ventana para agregar una persona.
     * Actualiza la lista y refresca la tabla una vez se cierra la ventana de agregar.
     * @param event Evento de acción que dispara el método
     * @throws IOException en caso de error al cargar el archivo FXML
     */
    @FXML
    void agregar(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NuevaPersona.fxml"));
        Parent root = fxmlLoader.load();

        NuevaPersonaController nuevaPersonaController = fxmlLoader.getController();
        nuevaPersonaController.setPersonasList(personasList);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Nueva Persona");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        // Refrescar la tabla después de agregar
        tablaPersonas.refresh();
    }

    /**
     * Abre una ventana para modificar la persona seleccionada en la tabla.
     * Si no hay ninguna persona seleccionada, muestra una alerta al usuario.
     * @param event Evento de acción que dispara el método
     */
    @FXML
    private void abrirVentanaModificar(ActionEvent event) {
        Persona personaSeleccionada = tablaPersonas.getSelectionModel().getSelectedItem();
        if (personaSeleccionada == null) {
            mostrarAlerta("No hay ninguna persona seleccionada", "Por favor, seleccione una persona para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ejercicioe/NuevaPersona.fxml"));
            Parent modalRoot = loader.load();
            NuevaPersonaController modalController = loader.getController();
            modalController.setPersonasList(personasList);
            modalController.setPersonaAEditar(personaSeleccionada);

            Stage modalStage = new Stage();
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(btt_modificar.getScene().getWindow());
            modalStage.setTitle("Editar Persona");
            modalStage.setScene(new Scene(modalRoot));
            modalStage.showAndWait();

            tablaPersonas.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina la persona seleccionada en la tabla.
     * Si no hay ninguna persona seleccionada, muestra una alerta al usuario.
     * @param event Evento de acción que dispara el método
     */
    @FXML
    private void eliminar(ActionEvent event) {
        Persona personaSeleccionada = tablaPersonas.getSelectionModel().getSelectedItem();
        if (personaSeleccionada == null) {
            mostrarAlerta("No hay ninguna persona seleccionada", "Por favor, seleccione una persona para eliminar.");
        } else {
            personasList.remove(personaSeleccionada);
            mostrarAlerta("Persona eliminada", "La persona ha sido eliminada con éxito.");
        }
    }

    /**
     * Muestra una alerta informativa con un título y un mensaje específico
     * @param titulo  Título de la alerta
     * @param mensaje Mensaje de la alerta
     */
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
