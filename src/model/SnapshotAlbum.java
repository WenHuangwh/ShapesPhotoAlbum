package model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * This is the snapshot album. It stores all snapshots before reset.
 */
public class SnapshotAlbum {

  // snapshotID is used to record order of snapshot added in
  private final List<String> snapshotID;
  private final HashMap<String, Snapshot> snapshotAlbum;

  /**
   * Instantiates a new snapshot album.
   */
  public SnapshotAlbum() {
    this.snapshotID = new LinkedList<>();
    this.snapshotAlbum = new HashMap<>();
  }

  /**
   * Add snapshot.
   *
   * @param ID       the id
   * @param snapshot the snapshot
   */
  protected void addSnapshot(String ID, Snapshot snapshot) {
    this.snapshotID.add(ID);
    this.snapshotAlbum.put(ID, snapshot);
  }

  /**
   * Display snapshot album.
   *
   * @return the list of snapshot
   */
  protected List<Snapshot> display() {
    List<Snapshot> album = new LinkedList<>();
    for (String snapshotid : this.snapshotID) {
      album.add(this.snapshotAlbum.get(snapshotid));
    }
    return album;
  }

  /**
   * Display in string.
   *
   * @return the string
   */
  protected String displayToString() {
    // Generate final string output:
    // Firstly add all snapshot id.
    String temp = "List of snapshots taken before reset: [";
    for (String snapshotpid : this.snapshotID) {
      temp += snapshotpid;
      temp += ", ";
    }
    String output = temp.substring(0, temp.length() - 2);
    output += "]\n";

    // Secondly add each snapshot in snapshotAlbum.
    output += "\nPrinting Snapshots\n";
    for (String snapshotpid : this.snapshotID) {
      output += this.snapshotAlbum.get(snapshotpid).displayToString();
      output += "\n\n";
    }
    output = output.substring(0, output.length() - 2);
    return output;
  }

}
