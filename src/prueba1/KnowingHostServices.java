package prueba1;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KnowingHostServices extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        // le pasamos la direccion del buscador al que queremos ir y en el boton de ir a la url le "decimos" al usuario que buscador es (en este caso google)
        String googleURL = "https://www.google.es/";
        Button abrirURL = new Button("Ir a Google!");
        abrirURL.setOnAction(e -> getHostServices().showDocument(googleURL));
        Button mostrarAlerta = new Button("Mostrar alerta");
        mostrarAlerta.setOnAction(e -> mostrarAlerta());
        VBox root = new VBox();
        // añade los botones y todos los detalles del host a la VBox
        root.getChildren().addAll(abrirURL, mostrarAlerta);
        Map<String, String> hostdetails = consigueLosDetallesDelHost();
        for (Map.Entry<String, String> entry : hostdetails.entrySet()) {
            String desc = entry.getKey() + ": " + entry.getValue();
            root.getChildren().add(new Label(desc));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Conoce al Host");
        stage.show();
    }

    protected Map<String, String> consigueLosDetallesDelHost() {
        Map<String, String> map = new HashMap<>();
        HostServices host = this.getHostServices();
        String codeBase = host.getCodeBase();
        map.put("CodeBase", codeBase);
        // guarda en la variable documentBase la raiz del documento que estamos ejecutando
        String documentBase = host.getDocumentBase();
        map.put("DocumentBase", documentBase);
        // accede a la imagen gracias a la variable documentBase
        String splashImageURI = host.resolveURI(documentBase, "splash.jpg");
        map.put("Splash Image URI", splashImageURI);
        return map;
    }


    // Alerta emergente que se muestra y hasta que no la aceptas no te deja hacer nada con la ventana principal
    protected void mostrarAlerta() {
        Stage s = new Stage(StageStyle.UTILITY);
        s.initModality(Modality.APPLICATION_MODAL);
        Label msgLabel = new Label("¡Esto es una alerta de FX!");
        Group root = new Group(msgLabel);
        Scene scene = new Scene(root);
        s.setScene(scene);
        s.setTitle("Alerta FX");
        s.show();
    }
}
