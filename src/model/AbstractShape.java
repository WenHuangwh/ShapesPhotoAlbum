package model;

/**
 * The type Abstract shape.
 */
public abstract class AbstractShape implements IShape {

  private final String name;
  private Point point;
  private Color color;

  /**
   * Instantiates a new Abstract shape.
   *
   * @param name  the name
   * @param point the point
   * @param color the color
   * @throws IllegalArgumentException if name is null or ""
   */
  protected AbstractShape(String name, Point point, Color color) {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Invalid name");
    }
    this.name = name;
    this.point = point;
    this.color = color;
  }

  public String getName() {
    return this.name;
  }

  public void setPosition(Point point) {
    this.point = point;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public Point getPoint() {
    return this.point;
  }

  public Color getColor() {
    return this.color;
  }
}
