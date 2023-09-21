// EventHandlerProperties.java
package prueba1;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class EventHandlerProperties extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage escenario) {
        //creamos el circulo y le decimos de que color lo queremos
        Circle circle = new Circle(100, 100, 50);
        circle.setFill(Color.CORAL);

        // Creamos la HBox en la que añadimos el circulo
        HBox root = new HBox();
        root.getChildren().add(circle);

        // Creamos la escena, la asignamos al escenario, le ponemos titulo, lo mostramos y lo reescalamos
        Scene scene = new Scene(root);
        escenario.setScene(scene);
        escenario.setTitle("Probando eventos de raton");
        escenario.show();
        escenario.sizeToScene();

        // Creamos un eventHandler
        EventHandler<MouseEvent> eventHandler = e -> 
            System.out.println("Me has clickado con el raton, evento activado");
        // Almacenamos el eventHandler usando el metodo set para el tipo de evento al hacer clic con el raton
        circle.setOnMouseClicked(eventHandler);
    }
}