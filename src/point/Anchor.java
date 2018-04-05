package point;

import javafx.scene.canvas.GraphicsContext;

public class Anchor implements Point, Drawable {

    private static final int POINT_SIZE = 5;
    private double x, y;

    public Anchor(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void draw(GraphicsContext ctx) {
        ctx.fillOval(getX(), getY(), POINT_SIZE, POINT_SIZE);
    }
}
