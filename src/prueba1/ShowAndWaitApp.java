package prueba1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ShowAndWaitApp extends Application {
    // parametro contador para contar la cantidad de ventanas que se abren
	protected static int contador = 0;
	// parametro tipo Escena para saber cual es la ultima escena que se ha abierto 
    protected Stage ultimaEscena;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        Button openButton = new Button("Nueva ventana");
        openButton.setOnAction(e -> open(++contador));
        root.getChildren().add(openButton);
        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.setTitle("Escena principal");
        stage.show();
        this.ultimaEscena = stage;
    }

    private void open(int stageNumber) {
        Stage stage = new Stage();
        stage.setTitle("#" + stageNumber);
        Button sayHelloButton = new Button("Saludar");
        sayHelloButton.setOnAction(e -> System.out.println("Hola desde ventana #" + stageNumber));
        Button openButton = new Button("Nueva ventana");
        openButton.setOnAction(e -> open(++contador));
        VBox root = new VBox();
        root.getChildren().addAll(sayHelloButton, openButton);
        Scene scene = new Scene(root, 200, 200);
        stage.setScene(scene);
        stage.setX(this.ultimaEscena.getX() + 50);
        stage.setY(this.ultimaEscena.getY() + 50);
        this.ultimaEscena = stage;
        System.out.println("Creada stage.showAndWait(): " + stageNumber);
        // Muestra el escenario y esperar a que el usuario lo cierre
        stage.showAndWait();
        System.out.println("Cerrada stage.showAndWait(): " + stageNumber);
    }
}