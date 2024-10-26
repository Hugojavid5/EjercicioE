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
    private Button btt_modificar;

    @FXML
    private Button btt_eliminar;

    private ObservableList<Persona> personasList = FXCollections.observableArrayList();


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
    private void abrirVentanaModificar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ejercicioe/NuevaPersona.fxml"));
            Parent modalRoot = loader.load();
            Stage modalStage = new Stage();
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(btt_agregar.getScene().getWindow());

            NuevaPersonaController modalController = loader.getController();

            if (event.getSource() == btt_agregar) {
                modalStage.setTitle("Agregar Persona");
                modalController.setPersonasList(personasList);
            } else if (event.getSource() == btt_modificar) {
                Persona personaSeleccionada = personTable.getSelectionModel().getSelectedItem();
                if (personaSeleccionada == null) {
                    mostrarAlerta("No hay ninguna persona seleccionada", "Por favor, seleccione una persona para editar.");
                    return; // No continuar si no hay selección
                }
                modalStage.setTitle("Editar Persona");
                modalController.setPersonasList(personasList);
                modalController.setPersonaAEditar(personaSeleccionada);
            }

            modalStage.setScene(new Scene(modalRoot));
            modalStage.showAndWait();

            // Refrescar la tabla después de modificar
            tablaPersonas.refresh();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        c_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        c_apellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        c_edad.setCellValueFactory(new PropertyValueFactory<>("edad"));
    }

    @FXML
    private void eliminar(ActionEvent event) {
        // Obtener la persona seleccionada
        Persona personaSeleccionada = tablaPersonas.getSelectionModel().getSelectedItem();

        if (personaSeleccionada == null) {
            // Si no hay ninguna persona seleccionada, mostrar alerta
            mostrarAlerta("No hay ninguna persona seleccionada", "Por favor, seleccione una persona para eliminar.");
        } else {
            // Eliminar la persona seleccionada de la lista
            personasList.remove(personaSeleccionada);
            // Mostrar un mensaje de confirmación
            mostrarAlerta("Persona eliminada", "La persona ha sido eliminada con éxito.");
        }
    }
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}