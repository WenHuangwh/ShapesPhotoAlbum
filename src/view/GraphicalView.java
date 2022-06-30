package view;

import viewhelper.GraphicalViewHelper;
import java.awt.Dimension;
import java.util.List;
import model.Snapshot;
import viewhelper.ViewHelper;

/**
 * The type Graphical view.
 */
public class GraphicalView extends AbstractView {

  /**
   * Instantiates a new Graphical view.
   */
  public GraphicalView() {
    super();
  }

  @Override
  public void display() {
    String title = getOutputName();
    List<Snapshot> album = getAlbum();
    Dimension dimension = getDimension();
    if (title == null) {
      System.out.print("Invalid title\n");
      return;
    }
    if (album == null || album.size() < 1) {
      System.out.print("Invalid album\n");
      return;
    }
    if (dimension == null) {
      System.out.print("Invalid dimension\n");
      return;
    }
    ViewHelper frame = new GraphicalViewHelper(album, dimension, title);
    frame.display();
    System.out.print("Graphical view created!\nReady for next command: \n");
  }
}
