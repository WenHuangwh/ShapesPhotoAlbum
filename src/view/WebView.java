package view;

import java.awt.Dimension;
import java.util.List;
import model.Snapshot;
import viewhelper.ViewHelper;
import viewhelper.WebViewHelper;

/**
 * The type Web view.
 */
public class WebView extends AbstractView {

  /**
   * Instantiates a new Web view.
   */
  public WebView() {
    super();
  }

  @Override
  public void display() {
    String title = getOutputName();
    List<Snapshot> album = getAlbum();
    Dimension dimension = getDimension();
    if (title == null) {
      throw new IllegalArgumentException("Empty output filename\n");
    }
    if (album == null || album.size() < 1) {
      System.out.print("Empty album\n");
      return;
    }
    if (dimension == null) {
      System.out.print("Invalid dimension\n");
      return;
    }
    ViewHelper web = new WebViewHelper(album, dimension, title);
    web.display();
    System.out.print("Web view created!\nReady for next command: \n");
  }
}
