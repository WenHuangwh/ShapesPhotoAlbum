package model;

/**
 * The interface Shapes. A shape can set its position, set its color and scale, and returns a copy
 * of itself.
 */
public interface IShape {

  /**
   * Sets position.
   *
   * @param point the point
   */
  void setPosition(Point point);

  /**
   * Sets color.
   *
   * @param color the color
   */
  void setColor(Color color);

  /**
   * Sets scale.
   *
   * @param scale the scale
   * @throws IllegalArgumentException if scale is misused: parameters should be matched
   */
  void setScale(Scale scale) throws IllegalArgumentException;

  /**
   * Gets name.
   *
   * @return the name
   */
  String getName();

  /**
   * Gets type.
   *
   * @return the type
   */
  String getType();

  /**
   * Gets color.
   *
   * @return the color
   */
  Color getColor();

  /**
   * Gets scale.
   *
   * @return the scale
   */
  Scale getScale();

  /**
   * Gets point.
   *
   * @return the point
   */
  Point getPoint();

  /**
   * Copy shapes.
   *
   * @return the copy of a shape
   */
  IShape copy();
}
