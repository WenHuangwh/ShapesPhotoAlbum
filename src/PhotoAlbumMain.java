
import control.Controller;
import control.IControl;
import model.AlbumModel;
import model.IModel;
import view.GraphicalView;
import view.IView;
import view.WebView;

/**
 * The type Photo album main.
 */
public class PhotoAlbumMain {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
    IModel model = new AlbumModel();
    IView graphicalView = new GraphicalView();
    IView webView = new WebView();
    IControl control = new Controller(model, graphicalView, webView);
    control.go(args);
  }
}