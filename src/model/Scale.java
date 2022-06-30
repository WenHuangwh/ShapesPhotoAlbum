package model;

/**
 * The type Scale. A scale can be one parameter scale, two parameters scale to n parameters scale.
 */
public class Scale {

  // All parameters are intentionally set to negative in default.
  // When using a one parameter scale for a two parameter shape,
  // that shape class will receive a negative parameter and throw Illegal argument exception.
  private double X;
  private double Y;

  /**
   * Instantiates a two parameter Scale.
   *
   * @param twoParameterX the two parameter x
   * @param twoDimensionY the two dimension y
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Scale(double twoParameterX, double twoDimensionY) throws IllegalArgumentException {
    if (twoParameterX <= 0 || twoDimensionY <= 0) {
      throw new IllegalArgumentException("Invalid Scale");
    }
    this.X = twoParameterX;
    this.Y = twoDimensionY;
  }

  /**
   * Gets parameter x of two parameter scale.
   *
   * @return the parameter x of two parameter scale
   */
  public double getX() {
    return this.X;
  }

  /**
   * Gets parameter y of two parameter scale.
   *
   * @return the parameter y of two parameter scale
   */
  public double getY() {
    return this.Y;
  }

}
