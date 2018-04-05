package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.canvas.Canvas;
import javafx.event.ActionEvent;
import simulation.PointSimulation;

public class MainController {

    private Thread thread;
    private GraphicsContext ctx;
    private PointSimulation pSim;

    @FXML
    private Button btnStart;

    @FXML
    private Canvas cvsPlayground;

    @FXML
    private Slider sldrCountOfAnchors;

    public void btnStartClicked(ActionEvent e) {
        ctx = cvsPlayground.getGraphicsContext2D();
        if(thread == null) {
            Platform.runLater(() -> {
                pSim = new PointSimulation(ctx, (int) sldrCountOfAnchors.getValue(), 500);
                thread = new Thread(pSim);
                thread.start();
            });

            btnStart.setText("Stop");
        }
        else {
            pSim.setRunning(false);
            thread = null;
            btnStart.setText("Start");
        }
    }
}
