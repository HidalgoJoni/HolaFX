package prueba1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Prueba extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		//Mensaje de texto en ventana
		Text mensaje1 = new Text("Saludo vacio");
		
		Button botonHola = new Button("Hola");
		Button botonAdios = new Button("Adios");
		
		// expresion lambda
		botonHola.setOnAction(e -> mensaje1.setText("Hola"));
		botonAdios.setOnAction(e -> mensaje1.setText("Adios"));
		
		// clase anonima
		//botonHola.setOnAction(new EventHandler<ActionEvent>() {
		//	@Override
			//public void handle(ActionEvent arg0) {
		//		Platform.exit();
		//	}
		//});
		
		//Creacion organizacion VBox
		VBox raiz = new VBox();
		
		raiz.getChildren().addAll(mensaje1, botonHola, botonAdios);
		
		//Creacion escena de 500*100
		Scene escena = new Scene(raiz, 1000, 250);
		
		stage.setScene(escena);
		stage.setTitle("Hola, prueba de botones");
		stage.show();
	}
}