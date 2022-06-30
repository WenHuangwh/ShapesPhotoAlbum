package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the Shape collection. It contains 0 - n shapes. It can add, remove shapes, change color
 * and scale of shapes and return a copy of itself.
 */
class ShapeCollection {

  // shapeName list is used to record of shapes added in
  private List<String> shapeName;
  private HashMap<String, IShape> shapeAlbum;

  /**
   * Instantiates a new Shape collection.
   */
  protected ShapeCollection() {
    this.shapeName = new LinkedList<>();
    this.shapeAlbum = new HashMap<>();
  }

  /**
   * Add shape.
   *
   * @param type  the type
   * @param name  the name
   * @param point the point
   * @param color the color
   * @param scale the scale
   * @throws IllegalArgumentException if type is illegal or name is repeated
   */
  protected void addShape(String type, String name, Point point, Color color, Scale scale) {
    name = name.toUpperCase();
    // If the name already exists.
    if (this.shapeAlbum.containsKey(name)) {
      throw new IllegalArgumentException("Name already exists");
    }
    // Create a new shape and add it to shape album.
    // shapeName is used to record the order in which shapes were added.
    if (type.equalsIgnoreCase("rectangle")) {
      IShape shape = new Rectangle(name, point, color, scale);
      // Record order of shapes in shapeName list
      this.shapeAlbum.put(name, shape);
      this.shapeName.add(name);
    } else if (type.equalsIgnoreCase("oval")) {
      IShape shape = new Oval(name, point, color, scale);
      this.shapeAlbum.put(name, shape);
      this.shapeName.add(name);
    } else {
      // Invalid type:
      throw new IllegalArgumentException("Invalid Type");
    }
  }

  /**
   * Move.
   *
   * @param name  the name
   * @param point the point
   * @throws IllegalArgumentException if name is not in the shape album
   */
  protected void move(String name, Point point) {
    name = name.toUpperCase();
    // If name is not in shape album.
    if (!this.shapeAlbum.containsKey(name)) {
      throw new IllegalArgumentException("Name does not exist");
    }
    this.shapeAlbum.get(name).setPosition(point);
  }

  /**
   * Change color.
   *
   * @param name  the name
   * @param color the color
   * @throws IllegalArgumentException if name is not in the shape album
   */
  protected void changeColor(String name, Color color) {
    name = name.toUpperCase();
    // If name is not in shape album.
    if (!this.shapeAlbum.containsKey(name)) {
      throw new IllegalArgumentException("Name does not exist");
    }
    this.shapeAlbum.get(name).setColor(color);
  }

  /**
   * Change scale.
   *
   * @param name  the name
   * @param scale the scale
   * @throws IllegalArgumentException if name is not in the shape album
   */
  protected void changeScale(String name, Scale scale) {
    name = name.toUpperCase();
    // If name is not in shape album.
    if (!this.shapeAlbum.containsKey(name)) {
      throw new IllegalArgumentException("Name does not exist");
    }
    this.shapeAlbum.get(name).setScale(scale);
  }

  /**
   * Remove.
   *
   * @param name the name
   * @throws IllegalArgumentException if name is not in the shape album
   */
  protected void remove(String name) {
    name = name.toUpperCase();
    // If name is not in shape album.
    if (!this.shapeAlbum.containsKey(name)) {
      throw new IllegalArgumentException("Name does not exist");
    }
    this.shapeAlbum.remove(name);
    this.shapeName.remove(name);
  }

  /**
   * Gets copy of shapes.
   *
   * @return the list of copy of shapes
   */
  protected List<IShape> getCopyOfShapes() {
    List<IShape> copy = new LinkedList<>();
    // Keep the original order
    for (String shapeName : this.shapeName) {
      copy.add(this.shapeAlbum.get(shapeName).copy());
    }
    return copy.stream().collect(Collectors.toUnmodifiableList());
  }

  /**
   * Reset shape album.
   */
  protected void reset() {
    this.shapeName = new LinkedList<>();
    this.shapeAlbum = new HashMap<>();
  }
}
