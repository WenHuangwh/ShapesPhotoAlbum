package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * The type Oval.
 */
public class Oval extends AbstractShape {

  private Scale scale;
  private double xRadius;
  private double yRadius;

  /**
   * Instantiates a new Oval.
   *
   * @param name  the name
   * @param point the point
   * @param color the color
   * @param scale the scale
   * @throws IllegalArgumentException if scale is misused: parameters should be matched
   */
  public Oval(String name, Point point, Color color, Scale scale) throws IllegalArgumentException {
    super(name, point, color);
    this.scale = scale;
    this.xRadius = scale.getX();
    this.yRadius = scale.getY();
  }

  public String getType() {
    return "oval";
  }

  public void setScale(Scale scale) throws IllegalArgumentException {
    this.scale = scale;
    this.xRadius = scale.getX();
    this.yRadius = scale.getY();
  }

  public Scale getScale() {
    return this.scale;
  }

  public IShape copy() {
    String name = this.getName();
    Point point = this.getPoint();
    Color color = this.getColor();
    return new Oval(name, point, color, this.scale);
  }

  @Override
  public String toString() {
    String name = this.getName();
    double x = this.getPoint().getX();
    double y = this.getPoint().getY();
    double r = this.getColor().getR();
    double g = this.getColor().getG();
    double b = this.getColor().getB();
    NumberFormat format = new DecimalFormat("0.0");
    return "Name: " + name + "\nType: oval\n" + "Center: (" + format.format(x) + ", "
        + format.format(y) + "), "
        + "X radius: " + format.format(this.xRadius) + ", Y radius: " + format.format(this.yRadius)
        + ", Color: (" + format.format(r)
        + ", " + format.format(g)
        + ", " + format.format(b) + ")\n";
  }
}