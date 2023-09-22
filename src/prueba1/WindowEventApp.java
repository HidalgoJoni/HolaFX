package prueba1;
import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST;

public class WindowEventApp extends Application {
    private CheckBox puedeCerrarVentana;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Creamos la checkbox para saber si se puede o no cerrar la ventana
        // Tambien creamos los botones ocultar y cerrar y les asignamos sus funciones
        puedeCerrarVentana = new CheckBox("Puede cerrar la ventana desde la barra de titulo haciendo click en cerrar (la X)");
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setOnAction(e -> stage.close());
        Button btnOcultar = new Button("Ocultar");
        btnOcultar.setOnAction(e -> {
            PopUp(stage);
            stage.hide();
        });

        //Creamos una HBox, le configuramos el padding y el espacio, y le añadimos los elementos
        HBox root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(puedeCerrarVentana, btnCerrar, btnOcultar);

        // Añadimos los controladores de eventos al escenario
        stage.setOnShowing(e -> Consumir(e));
        stage.setOnShown(e -> Consumir(e));
        stage.setOnHiding(e -> Consumir(e));
        stage.setOnHidden(e -> Consumir(e));
        stage.setOnCloseRequest(e -> Consumir(e));

        // Creamos la escena, le ponemos titulo y la mostramos
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Window Events");
        stage.show();
    }

    /**
     * Recibe eventos del escenario y en funcion del evento que reciba hace una cosa u otra
     * @param e El evento que tiene que gestionar
     */
    public void Consumir(WindowEvent e) {
        // Consume el evento si la checkbox no esta seleccionada para prevenir que el usuario la cierre
        EventType<WindowEvent> type = e.getEventType();
        if (type == WINDOW_CLOSE_REQUEST && !puedeCerrarVentana.isSelected()) {
            e.consume();
        }
        System.out.println(type + ": Consumido=" + e.isConsumed());
    }

    /**
     * Oculta la ventana principal mientras no se haga click en mostrar 
     * @param mainWindow Recibe el escenario principal para volver a mostrarlo cuando se hace click en btnCerrar
     */
    public void PopUp(Stage mainWindow) {
        Stage popup = new Stage();
        Button btnCerrar = new Button("Haz click para mostrar la ventana principal");
        btnCerrar.setOnAction(e -> {
            popup.close();
            mainWindow.show();
        });

        //Creamos Hbox, le damos sus parametros y añadimos el boton de cerrar
        HBox root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(btnCerrar);

        // Creamos la escena, le ponemos titulo y la mostramos
        Scene scene = new Scene(root);
        popup.setScene(scene);
        popup.setTitle("Popup");
        popup.show();
    }
}