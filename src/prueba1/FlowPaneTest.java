package prueba1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class FlowPaneTest extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Creamos los parametros que le pasamos al FlowPanel
        double espacioHorizontal = 5;
        double espacioVertical = 10;

        // Creamos el FlowPanel y le pasamos como parametros los dos atributos creados anteriormente
        FlowPane root = new FlowPane(espacioHorizontal, espacioVertical);

        // Añadimos 10 botones al FlowPanel
        for(int i = 1; i <= 10; i++) {
            root.getChildren().add(new Button("Button " + i));
        }

        // Le ponemos el estilo al FlowPanel
        root.setStyle("-fx-padding: 10;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 2;" +
                      "-fx-border-insets: 5;" + 
                      "-fx-border-radius: 5;" + 
                      "-fx-border-color: blue;");

        // Creamos la escena, le ponemos titulo y la mostramos
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("A Horizontal FlowPane");
        stage.show();
    }
}
