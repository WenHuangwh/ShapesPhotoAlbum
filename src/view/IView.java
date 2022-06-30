package view;

import java.awt.Dimension;
import java.util.List;
import model.Snapshot;

/**
 * The interface View.
 */
public interface IView {

  /**
   * Sets output filename or frame title.
   *
   * @param outputName string
   */
  void setOutputName(String outputName);

  /**
   * Sets album list.
   *
   * @param album the album list
   */
  void setAlbum(List<Snapshot> album);

  /**
   * Sets panel dimension.
   *
   * @param dimension the dimension
   */
  void setDimension(Dimension dimension);

  /**
   * Display view.
   */
  void display();

  /**
   * Reset information.
   */
  void reset();
}
