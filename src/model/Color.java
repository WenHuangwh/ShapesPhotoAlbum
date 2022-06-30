package model;

/**
 * The type Color. A color has 3 values of R, G, B.
 */
public class Color {

  private final double r;
  private final double g;
  private final double b;

  /**
   * Instantiates a new Color.
   *
   * @param r the r
   * @param g the g
   * @param b the b
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Color(double r, double g, double b) throws IllegalArgumentException {
    if (r < 0 || g < 0 || b < 0 || r > 255 || g > 255 || b > 255) {
      throw new IllegalArgumentException("Invalid color");
    }
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Gets r.
   *
   * @return the r
   */
  public double getR() {
    return this.r;
  }

  /**
   * Gets g.
   *
   * @return the g
   */
  public double getG() {
    return this.g;
  }

  /**
   * Gets b.
   *
   * @return the b
   */
  public double getB() {
    return this.b;
  }
}
