package model;

import java.util.List;

/**
 * This is a snapshot of current shapes in shape collection. It has information of name, type,
 * position, color, scale of each shapes in shape collection.
 */
public class Snapshot {

  private final String id;
  private final String timeStamp;
  private final String description;
  private final List<IShape> IShapeList;

  /**
   * Instantiates a new Snapshot.
   *
   * @param id          the id
   * @param timeStamp   the time stamp
   * @param description the description
   * @param IShapeList  the shapes list
   */
  public Snapshot(String id, String timeStamp, String description, List<IShape> IShapeList) {
    this.id = id;
    this.timeStamp = timeStamp;
    this.description = description;
    this.IShapeList = IShapeList;
  }

  /**
   * Gets id.
   *
   * @return the id
   */
  public String getID() {
    return this.id;
  }

  /**
   * Gets time stamp.
   *
   * @return the time stamp
   */
  public String getTimeStamp() {
    return this.timeStamp;
  }

  /**
   * Gets description.
   *
   * @return the description
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Gets shape list.
   *
   * @return the shape list
   */
  public List<IShape> getIShapeList() {
    return this.IShapeList;
  }

  /**
   * Display to string.
   *
   * @return the string
   */
  public String displayToString() {
    String snap = "Snapshot ID: " + this.id + "\nTimestamp: " + this.timeStamp + "\nDescription: "
        + this.description
        + "\nShape Information: \n";
    // Empty shape album
    if (this.IShapeList.size() == 0) {
      return snap;
    }
    // Current shape information in shape album
    for (IShape shape : this.IShapeList) {
      snap += shape.toString();
      snap += "\n";
    }
    snap = snap.substring(0, snap.length() - 1);
    return snap;
  }
}
