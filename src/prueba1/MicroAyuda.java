package prueba1;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MicroAyuda extends Application {
    //crea variable de tipo Texto llamada ayuda
    private Text ayuda = new Text();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage escena) {
        //crea 3 TextField de nombre, apellido y salario
        TextField nombre = new TextField();
        TextField apellido = new TextField();
        TextField sueldo = new TextField();
        //crea el boton de cerrar la ventana
        Button closeBtn = new Button("Cerrar");
        closeBtn.setOnAction(e -> Platform.exit());
        nombre.getProperties().put("ayuda", "Introduce el nombre");
        apellido.getProperties().put("ayuda", "Introduce el apellido");
        sueldo.getProperties().put("ayuda", "Introduce un sueldo mayor a 2000€");
        // El nodo del oayuda no esta controlado
        ayuda.setManaged(false);
        ayuda.setTextOrigin(VPos.TOP);
        ayuda.setFill(Color.RED);
        ayuda.setFont(Font.font(null, 9));
        ayuda.setMouseTransparent(true);
        // Añade todos los textfield a un panel
        GridPane root = new GridPane();
        root.add(new Label("Nombre:"), 1, 1);
        root.add(nombre, 2, 1);
        root.add(new Label("Apellido:"), 1, 2);
        root.add(apellido, 2, 2);
        root.add(new Label("Sueldo:"), 1, 3);
        root.add(sueldo, 2, 3);
        root.add(closeBtn, 3, 3);
        root.add(ayuda, 4, 3);
        Scene escenaPrincipal = new Scene(root, 300, 100);
        // Añadimos a un "listener" a la escena para saber cuando el usuario cambia y muestra la ayuda
        escenaPrincipal.focusOwnerProperty().addListener((ObservableValue<? extends Node> value, Node oldNode, Node newNode) -> focusChanged(value, oldNode, newNode));
        escena.setScene(escenaPrincipal);
        escena.setTitle("Enseñando ayuda");
        escena.show();
    }

    public void focusChanged(ObservableValue<? extends Node> value, Node oldNode, Node newNode) {
        // Focus has changed to a new node
        String microayuda = (String) newNode.getProperties().get("ayuda");
        if (microayuda != null && microayuda.trim().length() > 0) {
            ayuda.setText(microayuda);
            ayuda.setVisible(true);
            // Posicionamos la ventana que muestra la ayuda
            double x = newNode.getLayoutX() + newNode.getLayoutBounds().getMinX() - ayuda.getLayoutBounds().getMinX();
            double y = newNode.getLayoutY() + newNode.getLayoutBounds().getMinY() + newNode.getLayoutBounds().getHeight() - ayuda.getLayoutBounds().getMinX();
            ayuda.setLayoutX(x);
            ayuda.setLayoutY(y);
            ayuda.setWrappingWidth(newNode.getLayoutBounds().getWidth());
        } else {
            ayuda.setVisible(false);
        }
    }
}
