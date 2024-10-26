package com.example.ejercicioe;

import Model.Persona;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NuevaPersonaController {

    @FXML
    private TextField txt_Apellidos;

    @FXML
    private TextField txt_Edad;

    @FXML
    private TextField txt_Nombre;

    private ObservableList<Persona> personasList;
    private Persona personaAEditar;

    public void setPersonasList(ObservableList<Persona> personasList) {
        this.personasList = personasList;
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
                // Editar persona existente
                personaAEditar.setNombre(txt_Nombre.getText());
                personaAEditar.setApellidos(txt_Apellidos.getText());
                personaAEditar.setEdad(Integer.parseInt(txt_Edad.getText()));
                mostrarInfo("Persona editada correctamente");
            } else {
                // Agregar nueva persona
                Persona nuevaPersona = new Persona(txt_Nombre.getText(), txt_Apellidos.getText(), Integer.parseInt(txt_Edad.getText()));
                if (!personasList.contains(nuevaPersona)) {
                    personasList.add(nuevaPersona);
                    mostrarInfo("Persona añadida correctamente");
                } else {
                    mostrarError("Esa persona ya existe en la lista");
                }
            }
            cerrarVentana();
        } else {
            mostrarError(error);
        }
    }

    @FXML
    void cancelar(ActionEvent event) {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage stage = (Stage) txt_Nombre.getScene().getWindow();
        stage.close();
    }

    private void mostrarError(String error) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(error);
        alert.showAndWait();
    }

    private void mostrarInfo(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText(info);
        alert.showAndWait();
    }
}
