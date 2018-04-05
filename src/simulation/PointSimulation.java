package simulation;

import javafx.scene.canvas.GraphicsContext;
import java.util.ArrayList;
import java.util.List;

import point.Anchor;
import point.Actor;
import point.Point;

public class PointSimulation implements Runnable {

    private GraphicsContext ctx;
    private List<Anchor> anchors;
    private Actor actor;
    private double cvsWidth, cvsHeight;
    private int delay;
    private boolean running;

    public PointSimulation(GraphicsContext ctx, int anchorCount, int delay) {
        if(delay >= 0) {
            this.delay = delay;
        }
        else {
            throw new IllegalArgumentException("Parameter 'delay' cannot be less than zero!");
        }

        this.ctx = ctx;
        cvsWidth = ctx.getCanvas().getWidth();
        cvsHeight = ctx.getCanvas().getHeight();

        if(anchorCount > 0) {
            actor = getInitActor();
            anchors = generateAnchors(actor, anchorCount);

            anchors.forEach((a) -> a.draw(this.ctx));
            actor.draw(this.ctx);
        }
        else{
            throw new IllegalArgumentException("Only positive numbers accepted for 'anchorCount'!");
        }
        setRunning(false);
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    private List<Anchor> generateAnchors(Point point, int count) {
        ArrayList<Anchor> anc = new ArrayList<>();
        double radius;
        if(cvsHeight > cvsWidth) {
            radius = (cvsWidth / 2) * 0.7;
        }
        else {
            radius = (cvsHeight / 2) * 0.7;
        }

        anc.add(new Anchor(point.getX(), point.getY() + radius));

        double a = Math.toRadians(360 / count);

        for(int i = 1; i < count; i++) {
            Anchor tmp = new Anchor(anc.get(i-1).getX() - point.getX(), anc.get(i-1).getY() - point.getY());
            double x = tmp.getX() * Math.cos(a) - tmp.getY() * Math.sin(a) + point.getX();
            double y = tmp.getX() * Math.sin(a) + tmp.getY() * Math.cos(a) + point.getY();
            anc.add(new Anchor(x, y));
        }

        return anc;
    }

    private Actor getInitActor() {
        return new Actor(cvsWidth / 2, cvsHeight / 2);
    }

    private int getRandomAnchorIndex() {
        return (int)(Math.random() * anchors.size());
    }

    @Override
    public void run() {
        setRunning(true);
        try {
            while(running) {
                int randIndex = getRandomAnchorIndex();
                actor = actor.getNewPointBetween(anchors.get(randIndex));
                actor.draw(ctx);
                Thread.sleep(0, delay);
            }
        }
        catch (Exception e) { setRunning(false); }
    }
}
