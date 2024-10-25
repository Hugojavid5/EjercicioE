package com.example.ejercicioe;

import Model.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la ventana de añadir una nueva persona en una aplicación JavaFX.
 * Este controlador gestiona la interacción del usuario para agregar una persona
 * a la lista mostrada en la tabla.
 */
public class NuevaPersonaController {

    /** Campo de texto para introducir los apellidos de la persona. */
    @FXML
    private TextField txt_Apellidos;

    /** Campo de texto para introducir la edad de la persona. */
    @FXML
    private TextField txt_Edad;

    /** Campo de texto para introducir el nombre de la persona. */
    @FXML
    private TextField txt_Nombre;

    /** Referencia a la tabla de personas en la ventana principal. */
    private TableView<Persona> tablaPersonas;

    /**
     * Establece la referencia de la tabla de personas para que este controlador
     * pueda añadir personas directamente a la tabla.
     * @param tablaPersonas La tabla que contiene la lista de personas.
     */
    public void setTablaPersonas(TableView<Persona> tablaPersonas) {
        this.tablaPersonas = tablaPersonas;
    }

    /**
     * Acción que se ejecuta cuando el usuario hace clic en el botón "Guardar".
     * Valida los datos introducidos, muestra un mensaje de error si es necesario
     * y añade la nueva persona a la tabla si los datos son válidos.
     * @param event El evento que desencadenó la acción.
     */
    @FXML
    void guardar(ActionEvent event) {
        String error = "";

        // Validación de los campos de entrada
        if (txt_Nombre.getText().isEmpty()) {
            error += "Introduce un Nombre\n";
        }
        if (txt_Apellidos.getText().isEmpty()) {
            error += "Introduce un Apellido\n";
        }
        if (txt_Edad.getText().isEmpty()) {
            error += "Introduce una Edad\n";
        } else {
            try {
                int edad = Integer.parseInt(txt_Edad.getText());
                if (edad <= 0) {
                    throw new Exception();
                }
            } catch (NumberFormatException e) {
                error += "Introduce un entero como Edad\n";
            } catch (Exception e) {
                error += "Introduce un número superior a 0\n";
            }
        }

        // Verificación de errores y adición de la persona a la tabla
        if (error.isEmpty()) {
            boolean existe = false;
            Persona p = new Persona(txt_Nombre.getText(), txt_Apellidos.getText(), Integer.parseInt(txt_Edad.getText()));
            // Verifica si la persona ya existe en la tabla
            for (Persona persona : tablaPersonas.getItems()) {
                if (persona.equals(p)) {
                    mostrarError("Esa persona ya existe en la tabla");
                    existe = true;
                    break;
                }
            }
            // Si la persona no existe, la añade a la tabla
            if (!existe) {
                tablaPersonas.getItems().add(p);
                tablaPersonas.refresh();
                mostrarInfo("Persona añadida correctamente");

                Stage stage = (Stage) txt_Nombre.getScene().getWindow();
                stage.close();
            }
        } else {
            mostrarError(error);
        }
    }

    /**
     * Acción que se ejecuta cuando el usuario hace clic en el botón "Cancelar".
     * Cierra la ventana sin añadir ninguna persona a la tabla.
     * @param event El evento que desencadenó la acción.
     */
    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) txt_Nombre.getScene().getWindow();
        stage.close();
    }

    /**
     * Muestra un mensaje de error en forma de alerta.
     * @param error El mensaje de error a mostrar.
     */
    void mostrarError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(error);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de información en forma de alerta.
     * @param info El mensaje de información a mostrar.
     */
    void mostrarInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText(info);
        alert.showAndWait();
    }
}