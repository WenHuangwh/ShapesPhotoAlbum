package viewhelper;

import java.awt.Dimension;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import model.IShape;
import model.Snapshot;


/**
 * The type Web view helper.
 */
public class WebViewHelper implements ViewHelper {

  private final List<Snapshot> album;
  private final int canvasWidth;
  private final int canvasHeight;
  private final String fileName;
  private static final String TITLE = "&emsp; This is the portfolio of ";
  private static final String ID_FORMAT = "&emsp; &emsp; Time: %s &emsp; Snapshot ID: %s\n";
  private static final String DESCRIPTION_FORMAT = "&emsp; &emsp; Description: ";
  private static final String RECT = "rectangle";
  private static final String OVAL = "oval";
  private static final String BACKGROUND_COLOR = "grey";

  /**
   * Instantiates a new Web view helper.
   *
   * @param album     the album
   * @param dimension the dimension
   * @param fileName  the file name
   */
  public WebViewHelper(List<Snapshot> album, Dimension dimension, String fileName) {
    if (fileName == null || fileName.equalsIgnoreCase("")) {
      throw new IllegalArgumentException("Empty output name");
    }
    this.album = album;
    this.canvasWidth = dimension.width;
    this.canvasHeight = dimension.height;
    this.fileName = fileName;
  }

  private String writeID(String content) {
    return "<h3>" + content + "</h3> \n";
  }

  private String writeDescription(String content) {
    return "<h3>" + content + "</h3> \n";
  }

  private String drawRect(IShape rect) {
    String id = rect.getName();
    double x = rect.getPoint().getX();
    double y = rect.getPoint().getY();
    double width = rect.getScale().getX();
    double height = rect.getScale().getY();
    double r = rect.getColor().getR();
    double g = rect.getColor().getG();
    double b = rect.getColor().getB();
    return String.format(
        "<rect id = '%s' x='%f' y='%f' width='%f' height='%f' fill='rgb(%f, %f, %f)' visibility='visible'>\n</rect> \n",
        id, x, y, width, height, r, g, b);
  }

  private String drawOval(IShape oval) {
    String id = oval.getName();
    double x = oval.getPoint().getX();
    double y = oval.getPoint().getY();
    double xRadius = oval.getScale().getX();
    double yRadius = oval.getScale().getY();
    double r = oval.getColor().getR();
    double g = oval.getColor().getG();
    double b = oval.getColor().getB();
    return String.format(
        "<ellipse id = '%s' cx='%f' cy='%f' rx='%f' ry='%f' fill='rgb(%f, %f, %f)' visibility='visible'>\n</ellipse> \n",
        id, x, y, xRadius, yRadius, r, g, b);
  }

  public void display() {
    try (
        PrintStream output =
            new PrintStream(new FileOutputStream(this.fileName))
    ) {
      output.print("<!DOCTYPE html>\n");
      output.print("<html>\n");
      output.printf("<body style=\"background-color:%s;\">\n", BACKGROUND_COLOR);
      output.print("<h3>" + TITLE + this.fileName + "</h3>\n");
      for (Snapshot snapshot : this.album) {
        output.print("<div>\n");
        output.print(writeID(String.format(ID_FORMAT, snapshot.getTimeStamp(), snapshot.getID())));
        output.print(writeDescription(DESCRIPTION_FORMAT + snapshot.getDescription()));
        output.printf("<svg width='%d' height='%d'>\n", this.canvasWidth, this.canvasHeight);
        for (IShape shape : snapshot.getIShapeList()) {
          switch (shape.getType()) {
            case RECT -> output.print(drawRect(shape));
            case OVAL -> output.print(drawOval(shape));
          }
        }
        output.print("</svg>");
        output.print("</div>\n");
      }
      output.print("</body>\n");
      output.print("</html>\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
