package prueba1;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Arrays;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.HBox;
import static javafx.scene.control.TableView.TableViewSelectionModel;

public class TableViewAddDeleteRows extends Application {
    // Campos para añadir los detalles de la persona
    private TextField fNameField;
    private TextField lNameField;
    private DatePicker dobField;
    private TableView<Person> table;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void start(Stage stage) {
        fNameField = new TextField();
        lNameField = new TextField();
        dobField = new DatePicker();
        table = new TableView<>(PersonTableUtil.getPersonList());

        // Pasamos a la seleccion multi linea para la tableview
        TableViewSelectionModel<Person> tsm = table.getSelectionModel();
        tsm.setSelectionMode(SelectionMode.MULTIPLE);

        // Añadimos columnas al tableview
        table.getColumns().addAll(PersonTableUtil.getIdColumn(), PersonTableUtil.getFirstNameColumn(), PersonTableUtil.getLastNameColumn(), PersonTableUtil.getBirthDateColumn());
        GridPane newDataPane  = this.getNewPersonDataPane();

        // Creamos el boton para restablecer las tuplas
        Button restoreBtn = new Button("Restore Rows");
        restoreBtn.setOnAction(e -> restoreRows());

        // Creamos el boton para borrar las tuplas seleccionadas
        Button deleteBtn = new Button("Delete Selected Rows");
        deleteBtn.setOnAction(e -> deleteSelectedRows());
        VBox root = new VBox(newDataPane, new HBox(restoreBtn, deleteBtn), table);
        root.setSpacing(5);
        root.setStyle("-fx-padding: 10;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5;" + 
                      "-fx-border-color: blue;");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Añadiendo/Borrando filas en un tableview");
        stage.show();
    }
    
    public GridPane getNewPersonDataPane() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(5);
        pane.addRow(0, new Label("Nombre:"), fNameField);
        pane.addRow(1, new Label("Apellido:"), lNameField);
        pane.addRow(2, new Label("Fecha Nacimiento:"), dobField);
        Button addBtn = new Button("Añadir");
        addBtn.setOnAction(e -> addPerson());
        // Añadimos el boton "Añadir"
        pane.add(addBtn, 2, 0);
        return pane;
    }

    public void deleteSelectedRows() {  
        TableViewSelectionModel<Person> tsm = table.getSelectionModel();
        if (tsm.isEmpty()) {
            System.out.println("Selecciona una fila para borrar");
            return;
        }

        // Mete todas las filas seleccionadas en un array
        ObservableList<Integer> list = tsm.getSelectedIndices();
        Integer[] selectedIndices = new Integer[list.size()];
        selectedIndices = list.toArray(selectedIndices);

        // Sort the array
        Arrays.sort(selectedIndices);

        // Borra las filas de la ultima a la primera
        for(int i = selectedIndices.length - 1; i >= 0; i--) {
            tsm.clearSelection(selectedIndices[i].intValue());
            table.getItems().remove(selectedIndices[i].intValue());
        }
    }

    public void restoreRows() {
        table.getItems().clear();
        table.getItems().addAll(PersonTableUtil.getPersonList());
    }

    public Person getPerson() {
        return new Person(fNameField.getText(), lNameField.getText(), dobField.getValue());
    }

    public void addPerson() {
        Person p = getPerson();
        table.getItems().add(p);
        clearFields();
    }

    public void clearFields() {
        fNameField.setText(null);
        lNameField.setText(null);
        dobField.setValue(null);
    }
}