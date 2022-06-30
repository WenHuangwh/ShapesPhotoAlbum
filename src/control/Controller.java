package control;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import model.Color;
import model.IModel;
import model.Point;
import model.Scale;
import model.Snapshot;
import view.IView;

/**
 * The type Controller.
 */
public class Controller implements IControl {

//  private Readable in;
  private final IModel model;
  private final IView graphicalView;
  private final IView webView;
  // For System.in version:
  // This quit control the while loop
  private boolean quit; // Check if command has -quit
  private boolean hasInput; // Check if command has -in input filename
  private boolean hasView; // Check if command has -v view type
  private String inputFile; // Input filename
  private String outputFile; // -out in web viewL: file name of html
  private String frameTitle; // -out in graphical view: title of graphical view
  private String viewType;
  private Dimension dimension; // default size
  private List<Snapshot> album;

  private static final String RESOURCE_PATH = ""; // Path of resources/
  private static final String DEFAULT_TITLE = "CS5004"; // default name
  private static final Dimension DIMENSION_DEFAULT = new Dimension(1000, 1000); // default size
  private static final String GRAPHICS_VIEW = "graphical"; // keyword of graphical view
  private static final String WEB_VIEW = "web"; // keyword of web view
  private static final String COMMENT_SIGN = "#";
  private static final String INPUT_FILE = "-in"; // keyword of input filename
  private static final String OUTPUT_FILE = "-out"; // keyword of output filename
  private static final String VIEW = "-view"; // keyword of view type
  private static final String VIEW_ABBREV = "-v"; // keyword of view type
  private static final String QUIT = "-quit"; // keyword of quit
  private static final String ADD_SHAPE = "shape"; // keyword of add shape
  private static final String MOVE = "move"; // keyword of move shape
  private static final String RESIZE = "resize"; // keyword of resize shape
  private static final String COLOR = "color"; // keyword of change color
  private static final String REMOVE = "remove"; // keyword of remove a shape
  private static final String SNAPSHOT = "snapshot"; // keyword of taking a snapshot


  private String input;
  /**
   * Instantiates a new Controller.
   *
   * @param model         the model
   * @param graphicalView the graphical view
   * @param webView       the web view
   * @throws IllegalArgumentException the illegal argument exception
   */
  public Controller(IModel model, IView graphicalView, IView webView)
      throws IllegalArgumentException {
    if (model == null || graphicalView == null || webView == null) {
      throw new IllegalArgumentException("Invalid model or view");
    }
    this.model = model;
    this.graphicalView = graphicalView;
    this.webView = webView;
    this.frameTitle = DEFAULT_TITLE;
    this.dimension = DIMENSION_DEFAULT;
    this.quit = false;
    this.hasInput = false;
    this.hasView = false;
  }

  public void go(String[] args) {
    this.input = String.join(" ", args);
//    InputStream inputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
//    this.in = new InputStreamReader(inputStream);

    // Below is for String[] args version:
    // Read command
    boolean infoValid = readCommand();
    // If command is valid - view type and input filename
    if (infoValid) {
      // Call album model
      callModel();
      // Get album of snapshots
      getAlbum();
      // Call view
      callView();
    }
    resetInfo(); // reset information for next album

    // If this method use System.in --- void go(Readable in)
    // below is a while loop version that can open and generate multiple frames and files
//    // If user doesn't quit
//    while (!this.quit) {
//      // Check command
//      boolean infoValid = readCommand();
//      // If command is valid - contains view type and input filename
//      if (infoValid) {
//        // Call album model
//        callModel();
//        // Get album of snapshots
//        getAlbum();
//        // Call view
//        callView();
//      }
//      resetInfo(); // reset information for next album
//    }
  }

  // Reset information
  private void resetInfo() {
    this.hasInput = false;
    this.hasView = false;
    this.viewType = null;
    this.inputFile = null;
    this.frameTitle = DEFAULT_TITLE;
    this.dimension = DIMENSION_DEFAULT;
    this.model.resetAlbum();
  }

  // Read command
  private boolean readCommand() {
    String word;
    int num1, num2;
    Scanner scan = new Scanner(this.input);
    String line;
    if (scan.hasNext()) {
      line = scan.nextLine();
    } else {
      return false;
    }
    scan = new Scanner(line);

    while (scan.hasNext()) {
      word = scan.next();
      // Find dimension of canvas
      if (word.matches("-?(0|[1-9]\\d*)")) {
        num1 = Integer.parseInt(word);
        // If next is int
        try {
          word = scan.next();
          num2 = Integer.parseInt(word);
          this.dimension = new Dimension(num1, num2);
        } catch (NumberFormatException e) {
          throw new IllegalArgumentException("Invalid frame size");
        }
        continue;
      }
      // Find keyword
      switch (word.toLowerCase()) {
        // Find input file name
        case INPUT_FILE:
          try {
            this.hasInput = true;
            String fileName = scan.next();
            this.inputFile = RESOURCE_PATH + fileName;
          } catch (NoSuchElementException e) {
            System.out.print("Missing file name");
          }
          break;
        // Find view type
        case VIEW, VIEW_ABBREV:
          this.hasView = true;
          try {
            word = scan.next();
            // Graphical view
            if (word.equalsIgnoreCase(GRAPHICS_VIEW)) {
              this.viewType = GRAPHICS_VIEW;
            }
            // Web view
            else if (word.equalsIgnoreCase(WEB_VIEW)) {
              this.viewType = WEB_VIEW;
            }
          } catch (NoSuchElementException e) {
            System.out.print("Missing view type");
          }
          break;
        // Find output file name
        case OUTPUT_FILE:
          try {
            String outputName = scan.next();
            this.frameTitle = outputName;
            this.outputFile = RESOURCE_PATH + outputName;
          } catch (NoSuchElementException e) {
            System.out.print("Missing output file");
          }
          break;
        // Quit
        case QUIT:
          this.quit = true;
      }
    }
    scan.close();
    // If command doesn't contain view type or input filename,
    // return false
    if (!this.hasView || !this.hasInput) {
      System.out.print("Missing view type or input file!\n");
      return false;
    }
    return true;
  }

  // Call album model
  private void callModel() {
    File file = new File(this.inputFile);
    try {
      FileReader fileReader = new FileReader(file);
      BufferedReader bufferedReader = new BufferedReader(
          fileReader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        // Call modelControl method to analysis each line
        modelControl(line);
      }
      fileReader.close();
    } catch (FileNotFoundException e) {
      System.out.print(file + " not Found Exception\n");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // This method read each line of command and call corresponding methods of model class
  private void modelControl(String line) {
    StringBuilder description = new StringBuilder();
    String shapeName, shapeType;
    double x, y, xScale, yScale, r, g, b;
    Scanner scan = new Scanner(line);
    String command;
    if (scan.hasNext()) {
      command = scan.next();
    } else {
      return;
    }
    // Check keyword
    switch (command.toLowerCase()) {
      case COMMENT_SIGN: // Skip comment
        break;
      case ADD_SHAPE: // Add a shape
        try {
          shapeName = scan.next();
          shapeType = scan.next();
          x = scan.nextInt();
          y = scan.nextInt();
          xScale = scan.nextInt();
          yScale = scan.nextInt();
          r = scan.nextInt();
          g = scan.nextInt();
          b = scan.nextInt();
          this.model.addShape(shapeType, shapeName, new Point(x, y), new Color(r, g, b),
              new Scale(xScale, yScale));
        } catch (NoSuchElementException ignored) {
        }
        break;
      case MOVE: // Move a shape
        try {
          shapeName = scan.next();
          x = scan.nextInt();
          y = scan.nextInt();
          this.model.move(shapeName, new Point(x, y));
        } catch (NoSuchElementException ignored) {
        }
        break;
      case COLOR: // Change shape color
        try {
          shapeName = scan.next();
          r = scan.nextInt();
          g = scan.nextInt();
          b = scan.nextInt();
          this.model.changeColor(shapeName, new Color(r, g, b));
        } catch (NoSuchElementException ignored) {
        }
        break;
      case RESIZE: // Resize a shape
        try {
          shapeName = scan.next();
          xScale = scan.nextInt();
          yScale = scan.nextInt();
          this.model.changeScale(shapeName, new Scale(xScale, yScale));
        } catch (NoSuchElementException ignored) {
        }
        break;
      case REMOVE: // Remove a shape
        try {
          shapeName = scan.next();
          this.model.remove(shapeName);
        } catch (NoSuchElementException ignored) {
        }
        break;
      case SNAPSHOT: // Take a snapshot
        while (scan.hasNext()) {
          description.append(scan.next()).append(" ");
        }
        // Remove last empty space
        if (description.length() > 0) {
          description = new StringBuilder(description.substring(0, description.length() - 1));
        }
        this.model.snapshot(description.toString());
        break;
    }
    scan.close();
  }

  // Call model displayAlbum method to get list of snapshots
  private void getAlbum() {
    this.album = this.model.displayAlbum();
  }

  // Call view
  private void callView() {
    // Graphical view
    if (this.viewType.equalsIgnoreCase(GRAPHICS_VIEW)) {
      this.graphicalView.setAlbum(this.album);
      this.graphicalView.setDimension(this.dimension);
      this.graphicalView.setOutputName(this.frameTitle);
      this.graphicalView.display();
      this.graphicalView.reset();
    }
    // Web view
    else {
      this.webView.setAlbum(this.album);
      this.webView.setDimension(this.dimension);
      this.webView.setOutputName(this.outputFile);
      this.webView.display();
      this.webView.reset();
    }
    // Reset album each time
    this.model.resetAlbum();
  }
}
