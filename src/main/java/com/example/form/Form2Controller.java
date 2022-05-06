package com.example.form;

import com.example.form.model.Persona;
import com.example.form.util.ConexionBBDD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Form2Controller {
    @FXML
    public TableView<Persona> tbvPersonas;
    @FXML
    public TableColumn<Persona,String> tbcNombre;
    @FXML
    public TableColumn<Persona,String> tbcApellidos;
    @FXML
    public TableColumn<Persona,Number> tbcEdad;// En JavaFX esta definido así

    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtApellidos;
    @FXML
    public TextField txtEdad;

// Conexión
    private ConexionBBDD connection;
    private ResultSet resultSet;




    ObservableList<Persona> personas = FXCollections.observableArrayList();

// Crea un la lista con varias personas de ejemplo
    private List<Persona> cargarDatos() throws SQLException {
        // Cargamos desde una base de datos
        connection=new ConexionBBDD();
        // Nos conectamos
        connection.getConexion();
        // Cargamos los datos
        resultSet=connection.ejecutarConsulta("SELECT * FROM personas");

        List<Persona> listaPersonas = new ArrayList<>();
        String nombre="";
        String apellidos="";
        int edad=0;

        while (resultSet.next()){
            nombre=resultSet.getString("nombre");
            apellidos=resultSet.getString("apellidos");
            edad=Integer.parseInt(resultSet.getString("edad"));
            listaPersonas.add(new Persona(nombre,apellidos,edad));
        }

        return listaPersonas;

    }


    // El método initialize (siempre ejecuta desde el inicio)
    @FXML
    private void initialize() throws SQLException {
        personas.addAll(cargarDatos());//carga los datos
        // Ir poniendo las columnas
        // Indicar a JavaFX que dato tiene cada columna
        // Cual atributo se asigna
        tbcNombre.setCellValueFactory(celda -> celda.getValue().nombreProperty());
        tbcApellidos.setCellValueFactory(celda -> celda.getValue().apellidosProperty());
        tbcEdad.setCellValueFactory(celda -> celda.getValue().edadProperty());

        tbvPersonas.setItems(personas);

    }


    public void onGuardarButtonClick(ActionEvent actionEvent) {
        String nombre=txtNombre.getText();
        String apellidos=txtNombre.getText();
        int edad=Integer.parseInt(txtEdad.getText());
        Persona p=new Persona(nombre,apellidos,edad);
        personas.add(p);//añadirlo al array

        String sql="INSERT INTO personas values (NULL,'"+p.getNombre()+"','"+p.getApellidos()+"','"+p.getEdad()+"')";
        System.out.println(sql);
        connection.ejecutarConsultaDelete(sql);//Deberia guardar el id

        tbvPersonas.getSelectionModel().select(p);
    }

    public void onBorrarButtonClick(ActionEvent actionEvent) {
        Persona persona=tbvPersonas.getSelectionModel().getSelectedItem();
        personas.remove(persona);


        // Borrado SQL
       // connection=new ConexionBBDD();
        // Nos conectamos
      //  connection.getConexion();

        String consulta="DELETE FROM personas WHERE nombre='"+persona.getNombre()+"'";
        System.out.println(consulta);
        connection.ejecutarConsultaDelete(consulta);//Deberia guardar el id


    }

    public void onActualizarButtonClick(ActionEvent actionEvent) throws SQLException { //Botón de actualizar
        personas.removeAll(personas);
        personas.addAll(cargarDatos());//carga los datos
    }

    // QUedan de tarea para actualizar

    public void onUpdateButtonClick(ActionEvent actionEvent) {
//        String consulta="DELETE FROM personas WHERE nombre='"+persona.getNombre()+"'";
//        System.out.println(consulta);
//        connection.ejecutarConsultaDelete(consulta);//Deberia guardar el id


    }


    // El editar lo dejo de tarea
    // Seria con execute
    //String sql="UPDATE CLIENTE SET cedula='"+cliente.getCedula()+"', nombres='"+cliente.getNombre()+"', apellidos='"+cliente.getApellido()+"'" +" WHERE ID="+cliente.getId();





//    private Person selectedPerson;
//    private final BooleanProperty modifiedProperty = new SimpleBooleanProperty(false);
//    private ChangeListener<Person> personChangeListener;
//
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//
//        // Disable the Remove/Edit buttons if nothing is selected in the ListView control
//        removeButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull());
//        updateButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull()
//                .or(modifiedProperty.not())
//                .or(firstnameTextField.textProperty().isEmpty()
//                        .or(lastnameTextField.textProperty().isEmpty())));
//        createButton.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNotNull()
//                .or(firstnameTextField.textProperty().isEmpty()
//                        .or(lastnameTextField.textProperty().isEmpty())));
//
//        SampleData.fillSampleData(personList);
//
//        // Use a sorted list; sort by lastname; then by firstname
//        SortedList<Person> sortedList = new SortedList<>(personList);
//
//        // sort by lastname first, then by firstname; ignore notes
//        sortedList.setComparator((p1, p2) -> {
//            int result = p1.getLastname().compareToIgnoreCase(p2.getLastname());
//            if (result == 0) {
//                result = p1.getFirstname().compareToIgnoreCase(p2.getFirstname());
//            }
//            return result;
//        });
//        listView.setItems(sortedList);
//
//        listView.getSelectionModel().selectedItemProperty().addListener(
//                personChangeListener = (observable, oldValue, newValue) -> {
//                    System.out.println("Selected item: " + newValue);
//                    // newValue can be null if nothing is selected
//                    selectedPerson = newValue;
//                    modifiedProperty.set(false);
//                    if (newValue != null) {
//                        // Populate controls with selected Person
//                        firstnameTextField.setText(selectedPerson.getFirstname());
//                        lastnameTextField.setText(selectedPerson.getLastname());
//                        notesTextArea.setText(selectedPerson.getNotes());
//                    } else {
//                        firstnameTextField.setText("");
//                        lastnameTextField.setText("");
//                        notesTextArea.setText("");
//                    }
//                });
//
//        // Pre-select the first item
//        listView.getSelectionModel().selectFirst();
//
//    }
//
//    @FXML
//    private void handleKeyAction(KeyEvent keyEvent) {
//        modifiedProperty.set(true);
//    }
//
//    @FXML
//    private void createButtonAction(ActionEvent actionEvent) {
//        System.out.println("Create");
//        Person person = new Person(firstnameTextField.getText(),
//                lastnameTextField.getText(), notesTextArea.getText());
//        personList.add(person);
//        // and select it
//        listView.getSelectionModel().select(person);
//    }
//
//    @FXML
//    private void removeButtonAction(ActionEvent actionEvent) {
//        System.out.println("Remove " + selectedPerson);
//        personList.remove(selectedPerson);
//    }
//
//    @FXML
//    private void updateButtonAction(ActionEvent actionEvent) {
//        System.out.println("Update " + selectedPerson);
//        Person p = listView.getSelectionModel().getSelectedItem();
//        listView.getSelectionModel().selectedItemProperty().removeListener(personChangeListener);
//        p.setFirstname(firstnameTextField.getText());
//        p.setLastname(lastnameTextField.getText());
//        p.setNotes(notesTextArea.getText());
//        listView.getSelectionModel().selectedItemProperty().addListener(personChangeListener);
//        modifiedProperty.set(false);
//    }









}
