package application;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		//Mensaje de texto en ventana
		Text mensaje1 = new Text("Prueba de un cuadro de mensaje");
		
		//Creacion organizacion VBox
		VBox raiz = new VBox();
		
		raiz.getChildren().add(mensaje1);
		
		//Creacion escena de 500*100
		Scene escena = new Scene(raiz, 1000, 250);
		
		// asignamos al escenario la escena
		stage.setScene(escena);
		
		// cambiando titulo
		stage.setTitle("Hola, prueba 1 de aplicacion de JavaFX");
		
		//mostrar escena
		stage.show();
	}
}