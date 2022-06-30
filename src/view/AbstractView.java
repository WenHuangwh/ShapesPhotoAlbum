package view;

import java.awt.Dimension;
import java.util.List;
import model.Snapshot;

/**
 * The type Abstract view.
 */
public abstract class AbstractView implements IView {

  private String name;
  private List<Snapshot> album;
  private Dimension dimension = new Dimension(1000, 1000);

  /**
   * Instantiates a new Abstract view.
   */
  protected AbstractView() {
  }

  @Override
  public void setOutputName(String outputName) {
    if (outputName == null) {
      System.out.print("Invalid output filename\n");
    }
    this.name = outputName;
  }

  /**
   * Gets output filename or title of frame.
   *
   * @return the name
   */
  protected String getOutputName() {
    return this.name;
  }

  @Override
  public void setAlbum(List<Snapshot> album) {
    if (album == null) {
      System.out.print("Invalid album\n");
    }
    this.album = album;
  }

  /**
   * Gets album.
   *
   * @return the album
   */
  protected List<Snapshot> getAlbum() {
    return this.album;
  }

  @Override
  public void setDimension(Dimension dimension) {
    if (dimension == null) {
      System.out.print("Invalid dimension\n");
    }
    this.dimension = dimension;
  }

  /**
   * Gets dimension.
   *
   * @return the dimension
   */
  protected Dimension getDimension() {
    return this.dimension;
  }

  @Override
  public void reset() {
    this.name = null;
    this.album = null;
    this.dimension = null;
  }
}
