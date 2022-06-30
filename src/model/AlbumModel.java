package model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * The type Album model.
 */
public class AlbumModel implements IModel {

  // Collection of current shapes;
  private final ShapeCollection shapeCollection;
  // Snapshot album
  private SnapshotAlbum snapshotAlbum;
  /**
   * The Snapshot id.
   */
// ID of next snapshot
  int snapshotID;

  /**
   * Instantiates a new Album model.
   */
  public AlbumModel() {
    this.shapeCollection = new ShapeCollection();
    this.snapshotAlbum = new SnapshotAlbum();
    this.snapshotID = 0;
  }

  public void addShape(String type, String name, Point point, Color color, Scale scale)
      throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Invalid name");
    }
    shapeCollection.addShape(type, name, point, color, scale);
  }

  public void move(String name, Point point) throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Invalid name");
    }
    shapeCollection.move(name, point);
  }

  public void changeColor(String name, Color color) throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Invalid name");
    }
    shapeCollection.changeColor(name, color);
  }

  public void changeScale(String name, Scale scale) throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Invalid name");
    }
    shapeCollection.changeScale(name, scale);

  }

  public void remove(String name) throws IllegalArgumentException {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Invalid name");
    }
    shapeCollection.remove(name);
  }

  public Snapshot snapshot(String description) {
    if (description == null) {
      description = "";
    }
    // Time stamp
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
    // Generate id
    NumberFormat format = new DecimalFormat("000000");
    String ID = format.format(this.snapshotID);
    // Get copy list of shapes
    List<IShape> copy = this.shapeCollection.getCopyOfShapes();
    // Generate a snapshot
    Snapshot snapshot = new Snapshot(ID, timeStamp, description, copy);
    // Add this snapshot into photo album
    this.snapshotAlbum.addSnapshot(ID, snapshot);
    // Update next snapshot ID
    this.snapshotID++;
    return snapshot;
  }

  public Snapshot snapshot() {
    // Time stamp
    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
    // Generate id
    NumberFormat format = new DecimalFormat("000000");
    String ID = format.format(this.snapshotID);
    // Get copy list of shapes
    List<IShape> copy = this.shapeCollection.getCopyOfShapes();
    // Generate a snapshot
    Snapshot snapshot = new Snapshot(ID, timeStamp, "", copy);
    // Add this snapshot into photo album
    this.snapshotAlbum.addSnapshot(ID, snapshot);
    // Update next snapshot ID
    this.snapshotID++;
    return snapshot;
  }

  public List<Snapshot> displayAlbum() {
    return this.snapshotAlbum.display();
  }

  public String displayToString() {
    return this.snapshotAlbum.displayToString();
  }

  public void resetAlbum() {
    this.snapshotAlbum = new SnapshotAlbum();
    this.snapshotID = 0;
    this.shapeCollection.reset();
  }
}
