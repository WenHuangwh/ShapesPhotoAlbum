package viewhelper;

import static viewhelper.GraphicalViewHelper.TEXT_DESCRIPTION_Y;
import static viewhelper.GraphicalViewHelper.TEXT_ID_X;
import static viewhelper.GraphicalViewHelper.TEXT_ID_Y;
import static viewhelper.GraphicalViewHelper.TITLE_BACKGROUND_HEIGHT;
import static viewhelper.GraphicalViewHelper.TITLE_BACKGROUND_X;
import static viewhelper.GraphicalViewHelper.TITLE_BACKGROUND_Y;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import model.IShape;
import model.Snapshot;

/**
 * The type Graphical panel.
 */
class GraphicalPanel extends JPanel {

  private Snapshot snapshot;
  private final Dimension dimension;
  private static final String RECT = "rectangle"; // Shape type rectangle
  private static final String OVAL = "oval"; // Shape type oval
  private static final String TITLE_ID = "Time: %s      ID:%s\n"; // ID format
  private static final String TITLE_DESCRIPTION = "Description: %s\n"; // Description format
  private static final Color CANVAS_COLOR = Color.pink; // Background color of id area
  private static final Color TEXT_COLOR = Color.black; // Text color

  /**
   * Instantiates a new Graphical panel.
   *
   * @param snapshot  the snapshot
   * @param dimension the dimension
   */
  protected GraphicalPanel(Snapshot snapshot, Dimension dimension) {
    this.snapshot = snapshot;
    this.dimension = dimension;
    this.dimension.height += TITLE_BACKGROUND_HEIGHT; // Snapshot height + ID/Description area height
    this.setPreferredSize(dimension);
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    // Draw snapshot
    for (IShape shape : this.snapshot.getIShapeList()) {
      String type = shape.getType();
      int x = (int) shape.getPoint().getX();
      int y = (int) shape.getPoint().getY() + TITLE_BACKGROUND_HEIGHT;
      int R = (int) shape.getColor().getR();
      int G = (int) shape.getColor().getG();
      int B = (int) shape.getColor().getB();
      int width = (int) shape.getScale().getX();
      int height = (int) shape.getScale().getY();
      g2D.setColor(new Color(R, G, B));
      switch (type) {
        case RECT -> g2D.fillRect(x, y, width, height);
        case OVAL -> g2D.fillOval(x, y, width, height);
      }
    }
    // Write id and description
    String id = String.format(TITLE_ID, snapshot.getTimeStamp(), snapshot.getID());
    String description = String.format(TITLE_DESCRIPTION, snapshot.getDescription());
    g2D.setPaint(CANVAS_COLOR);
    g2D.fillRect(TITLE_BACKGROUND_X, TITLE_BACKGROUND_Y, this.dimension.width,
        TITLE_BACKGROUND_HEIGHT);
    g2D.setPaint(TEXT_COLOR);
    g2D.drawString(id, TEXT_ID_X, TEXT_ID_Y);
    g2D.drawString(description, TEXT_ID_X, TEXT_DESCRIPTION_Y);
  }

  /**
   * Updated snapshot for repaint function.
   *
   * @param snapshot the snapshot
   */
  protected void updatedSnapshot(Snapshot snapshot) {
    this.snapshot = snapshot;
  }
}
