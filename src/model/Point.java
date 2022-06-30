package model;

/**
 * The type Point. A Point has two dimension: x and y.
 */
public class Point {

  private final double x;
  private final double y;

  /**
   * Instantiates a new Point.
   *
   * @param x the x
   * @param y the y
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Gets x.
   *
   * @return the x
   */
  public double getX() {
    return this.x;
  }

  /**
   * Gets y.
   *
   * @return the y
   */
  public double getY() {
    return this.y;
  }
}
