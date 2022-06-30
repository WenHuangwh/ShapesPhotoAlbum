package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * The type Rectangle.
 */
public class Rectangle extends AbstractShape {

  private Scale scale;
  private double width;
  private double height;

  /**
   * Instantiates a new Rectangle.
   *
   * @param name  the name
   * @param point the point
   * @param color the color
   * @param scale the scale
   * @throws IllegalArgumentException if scale is misused: parameters should be matched
   */
  public Rectangle(String name, Point point, Color color, Scale scale)
      throws IllegalArgumentException {
    super(name, point, color);
    this.scale = scale;
    this.width = scale.getX();
    this.height = scale.getY();
  }

  public String getType() {
    return "rectangle";
  }

  public void setScale(Scale scale) throws IllegalArgumentException {
    this.scale = scale;
    this.width = scale.getX();
    this.height = scale.getY();
  }

  public Scale getScale() {
    return this.scale;
  }

  public IShape copy() {
    String name = this.getName();
    Point point = this.getPoint();
    Color color = this.getColor();
    return new Rectangle(name, point, color, this.scale);
  }

  @Override
  public String toString() {
    String name = getName();
    double x = this.getPoint().getX();
    double y = this.getPoint().getY();
    double r = this.getColor().getR();
    double g = this.getColor().getG();
    double b = this.getColor().getB();
    NumberFormat format = new DecimalFormat("0.0");
    return "Name: " + name + "\nType: rectangle\n" + "Min corner: (" + format.format(x) + ", "
        + format.format(y) + "), "
        + "Width: " + format.format(this.width) + ", Height: " + format.format(this.height)
        + ", Color: (" + format.format(r)
        + ", " + format.format(g)
        + ", " + format.format(b) + ")\n";
  }
}
