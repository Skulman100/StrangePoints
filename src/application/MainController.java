package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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
    public void initialize() {
        ctx = cvsPlayground.getGraphicsContext2D();
        pSim = new PointSimulation(ctx, 7, 500);
    }

    public void btnStartClicked(ActionEvent e) {
        if(thread == null) {
            Platform.runLater(() -> {
                thread = new Thread(pSim);
                thread.start();
            });

            btnStart.setText("Stop");
        }
        else {
            if(thread != null) {
                pSim.setRunning(false);
                thread = null;
                btnStart.setText("Start");
            }
        }
    }
}
