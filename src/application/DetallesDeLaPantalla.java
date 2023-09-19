package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class DetallesDeLaPantalla extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage stage) {
        ObservableList<Screen> screenList = Screen.getScreens();
        System.out.println("Numero de pantallas: " + screenList.size());
        // Saca por pantalla los detalles de las pantallas
        for (Screen screen : screenList) {
            print(screen);
        }
        Platform.exit();
    }

    /***
     * Hace una salida por la linea de comandos con todos los detalles de la pantalla que recibe como parametro
     * @param s la pantalla que recibe para estudiar sus parametros
     */
    
    public void print(Screen s) {
        System.out.println("DPI: " + s.getDpi());
        System.out.print("Limites de la pantalla: ");
        Rectangle2D bounds = s.getBounds();
        print(bounds);
        System.out.print("Limites visuales de la pantalla: ");
        Rectangle2D visualBounds = s.getVisualBounds();
        print(visualBounds);
        System.out.println("-----------------------");
    }

    /***
     * Saca por salida estandar un rectangulo de las dimensiones Rectangle2D
     * @param r
     */
    
    public void print(Rectangle2D r) {
        System.out.format("minX=%.2f, minY=%.2f, width=%.2f, height=%.2f%n", r.getMinX(), r.getMinY(), r.getWidth(), r.getHeight());
    }
}
