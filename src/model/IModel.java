package model;

import java.util.List;

/**
 * This is the interface of album model. Album model can add shape into the shape collection,
 * reshape shapes, remove shapes, take a snapshot of current shapes in the shape collection and
 * print snapshot album of all snapshots
 */
public interface IModel {

  /**
   * Create a shape and add it into shape collection.
   *
   * @param type  the type
   * @param name  the name
   * @param point the point
   * @param color the color
   * @param scale the scale
   * @throws IllegalArgumentException if name is null or "" or is repeated
   */
  void addShape(String type, String name, Point point, Color color, Scale scale)
      throws IllegalArgumentException;

  /**
   * Move a shape.
   *
   * @param name  the name
   * @param point the point
   * @throws IllegalArgumentException if name is null or "" or not exists
   */
  void move(String name, Point point) throws IllegalArgumentException;

  /**
   * Change color of a shape.
   *
   * @param name  the name
   * @param color the color
   * @throws IllegalArgumentException if name is null or "" or not exists
   */
  void changeColor(String name, Color color) throws IllegalArgumentException;

  /**
   * Change scale of a shape.
   *
   * @param name  the name
   * @param scale the scale
   * @throws IllegalArgumentException if name is null or "" or not exists
   */
  void changeScale(String name, Scale scale) throws IllegalArgumentException;

  /**
   * Remove a shape.
   *
   * @param name the name
   * @throws IllegalArgumentException if name is null or "" or not exists
   */
  void remove(String name) throws IllegalArgumentException;

  /**
   * Take a Snapshot with description.
   *
   * @param description the description
   * @return the string
   */
  Snapshot snapshot(String description);

  /**
   * Take a Snapshot without description.
   *
   * @return the string
   */
  Snapshot snapshot();

  /**
   * Display snapshot album.
   *
   * @return the list of snapshot
   */
  List<Snapshot> displayAlbum();

  /**
   * Display snapshot album.
   *
   * @return the string
   */
  String  displayToString();

  /**
   * Reset snapshot album.
   */
  void resetAlbum();

}
