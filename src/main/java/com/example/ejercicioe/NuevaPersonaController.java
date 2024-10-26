package com.example.ejercicioe;

import Model.Persona;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NuevaPersonaController {

    @FXML
    private TextField txt_Apellidos;

    @FXML
    private TextField txt_Edad;

    @FXML
    private TextField txt_Nombre;

    private TableView<Persona> tablaPersonas;

    private Persona personaAEditar;

    public void setTablaPersonas(TableView<Persona> tablaPersonas) {
        this.tablaPersonas = tablaPersonas;
    }

    public void setPersonaAEditar(Persona persona) {
        this.personaAEditar = persona;
        if (persona != null) {
            txt_Nombre.setText(persona.getNombre());
            txt_Apellidos.setText(persona.getApellidos());
            txt_Edad.setText(String.valueOf(persona.getEdad()));
        }
    }

    @FXML
    void guardar(ActionEvent event) {
        String error = "";

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
                    error += "Introduce un número superior a 0\n";
                }
            } catch (NumberFormatException e) {
                error += "Introduce un entero como Edad\n";
            }
        }

        if (error.isEmpty()) {
            if (personaAEditar != null) {
                personaAEditar.setNombre(txt_Nombre.getText());
                personaAEditar.setApellidos(txt_Apellidos.getText());
                personaAEditar.setEdad(Integer.parseInt(txt_Edad.getText()));
                tablaPersonas.refresh();
                mostrarInfo("Persona editada correctamente");
            } else {
                Persona p = new Persona(txt_Nombre.getText(), txt_Apellidos.getText(), Integer.parseInt(txt_Edad.getText()));
                if (!tablaPersonas.getItems().contains(p)) {
                    tablaPersonas.getItems().add(p);
                    tablaPersonas.refresh();
                    mostrarInfo("Persona añadida correctamente");
                } else {
                    mostrarError("Esa persona ya existe en la tabla");
                }
            }
            Stage stage = (Stage) txt_Nombre.getScene().getWindow();
            stage.close();
        } else {
            mostrarError(error);
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage = (Stage) txt_Nombre.getScene().getWindow();
        stage.close();
    }

    void mostrarError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(error);
        alert.showAndWait();
    }

    void mostrarInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText(info);
        alert.showAndWait();
    }
}
