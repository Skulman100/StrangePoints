package point;

import javafx.scene.canvas.GraphicsContext;

public class Actor implements Point, Drawable {

    private static final int POINT_SIZE = 1;
    private double x, y;

    public Actor(double x, double y) {
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

    public Actor getNewPointBetween(Point point) {
        // Half-length-vector between both points
        double xValue = (point.getX() - getX()) * 0.5;
        double yValue = (point.getY() - getY()) * 0.5;

        // Sum vector and point to get the new point
        xValue += getX();
        yValue += getY();

        return new Actor(xValue, yValue);
    }

    @Override
    public void draw(GraphicsContext ctx) {
        ctx.fillOval(getX(), getY(), POINT_SIZE, POINT_SIZE);
    }
}
