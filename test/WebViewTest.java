import static org.junit.Assert.*;

import control.Controller;
import control.IControl;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import model.AlbumModel;
import model.IModel;
import org.junit.Before;
import org.junit.Test;
import view.GraphicalView;
import view.IView;
import view.WebView;

/**
 * The type Web view test.
 */
public class WebViewTest {

  /**
   * The Model.
   */
  private IModel model;
  /**
   * The Graphical view.
   */
  private IView graphicalView;
  /**
   * The Web view.
   */
  private IView webView;
  /**
   * The Control.
   */
  private IControl control;

  /**
   * Sets up.
   */
  @Before
  public void setUp() {
    this.model = new AlbumModel();
    this.graphicalView = new GraphicalView();
    this.webView = new WebView();
    this.control = new Controller(this.model, this.graphicalView, this.webView);
  }

  /**
   * Test null model.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    IControl badControl = new Controller(null, this.graphicalView, this.webView);
  }

  /**
   * Test null graphical view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullGraphicalView() {
    IControl badControl = new Controller(this.model, null, this.webView);
  }

  /**
   * Test null web view.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullWebView() {
    IControl badControl = new Controller(this.model, this.graphicalView, null);
  }

  /**
   * Test file existence.
   */
  @Test
  public void testFileExistence() {
    System.out.print("\nTesting create output file:\n");
    String input = "MyProgram -in resources/test1.txt -v web -out resources/testFileExistence.html -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
    File checkFile = new File("resources/testFileExistence.html");
    assertTrue(checkFile.exists());
  }

  /**
   * Test missing output file.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testMissingOutputFile() {
    System.out.print("\nTesting missing output file:\n");
    String input = "MyProgram -in resources/test1.txt -v web -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
  }

  /**
   * Test missing input file.
   */
  @Test
  public void testMissingInputFile() {
    System.out.print("\nTesting missing input file:\n");
    String input = "MyProgram -out resources/testMissingInputFile.html -v web -quit";
    String[] arr = input.split(" ");
    try {
      this.control.go(arr);
    } catch (IllegalArgumentException e) {
    }
    File checkFile = new File("resources/testMissingInputFile.html");
    assertFalse(checkFile.exists());
  }

  /**
   * Test missing view.
   */
  @Test
  public void testMissingView() {
    System.out.print("\nTesting missing view type:\n");
    String input = "MyProgram -in resources/test1.txt -out resources/testMissingView.html -quit";
    String[] arr = input.split(" ");
    try {
      this.control.go(arr);
    } catch (IllegalArgumentException e) {
    }
    File checkFile = new File("resources/testMissingView.html");
    assertFalse(checkFile.exists());
  }

  /**
   * Test random order.
   */
  @Test
  public void testRandomOrder() {
    System.out.print("\nTesting random command order:\n");
    String input = "-v web MyProgram -out resources/testRandomOrder.html -in resources/test1.txt -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
    File checkFile = new File("resources/testRandomOrder.html");
    assertTrue(checkFile.exists());
  }

  /**
   * Test frame size.
   */
  @Test
  public void testFrameSize() {
    System.out.print("\nTesting frame size:\n");
    String input = "800 800 -v web MyProgram -out resources/testFrameSize.html -in resources/test1.txt -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
    StringBuilder content = new StringBuilder();
    try {
      content = new StringBuilder(Files.readString(Path.of("resources/testFrameSize.html")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] lines = content.toString().split("\n");
    assertEquals("<svg width='800' height='800'>", lines[8]);
  }

  /**
   * Test default frame size.
   */
  @Test
  public void testDefaultFrameSize() {
    System.out.print("\nTesting frame size:\n");
    String input = "-v web MyProgram -out resources/testDefaultFrameSize.html -in resources/test1.txt -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
    StringBuilder content = new StringBuilder();
    try {
      content = new StringBuilder(
          Files.readString(Path.of("resources/testDefaultFrameSize.html")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] lines = content.toString().split("\n");
    assertEquals("<svg width='1000' height='1000'>", lines[8]);
  }

  /**
   * Test bad frame size.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testBadFrameSize() {
    System.out.print("\nTesting bad frame size:\n");
    String input = "800 MyProgram -out resources/testBadFrameSize.html -in resources/test1.txt -v web -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
  }

  /**
   * Test snapshot.
   */
  @Test
  public void testSnapshot() {
    System.out.print("\nTesting take a snapshot:\n");
    String input = "MyProgram -in resources/test2.txt -v web -out resources/testSnapshot.html -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
    StringBuilder content = new StringBuilder();
    try {
      content = new StringBuilder(Files.readString(Path.of("resources/testSnapshot.html")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] lines = content.toString().split("\n");
    assertEquals(12, lines.length);
  }

  /**
   * Test add shape.
   */
  @Test
  public void testAddShape() {
    System.out.print("\nTesting add a shape:\n");
    String input = "MyProgram -in resources/test3.txt -v web -out resources/testAddShape.html -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
    StringBuilder content = new StringBuilder();
    try {
      content = new StringBuilder(Files.readString(Path.of("resources/testAddShape.html")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] lines = content.toString().split("\n");
    assertEquals(14, lines.length);
    assertEquals(
        "<rect id = 'MYRECT' x='200.000000' y='200.000000' width='50.000000' height='100.000000' fill='rgb(255.000000, 0.000000, 0.000000)' visibility='visible'>",
        lines[9]);
  }

  /**
   * Test move.
   */
  @Test
  public void testMove() {
    System.out.print("\nTesting move a shape:\n");
    String input = "MyProgram -in resources/test4.txt -v web -out resources/testMove.html -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
    StringBuilder content = new StringBuilder();
    try {
      content = new StringBuilder(Files.readString(Path.of("resources/testMove.html")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] lines = content.toString().split("\n");
    assertEquals(14, lines.length);
    assertEquals(
        "<rect id = 'MYRECT' x='300.000000' y='200.000000' width='50.000000' height='100.000000' fill='rgb(255.000000, 0.000000, 0.000000)' visibility='visible'>",
        lines[9]);
  }

  /**
   * Test change color.
   */
  @Test
  public void testChangeColor() {
    System.out.print("\nTesting change color:\n");
    String input = "MyProgram -in resources/test5.txt -v web -out resources/testChangeColor.html -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
    StringBuilder content = new StringBuilder();
    try {
      content = new StringBuilder(Files.readString(Path.of("resources/testChangeColor.html")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] lines = content.toString().split("\n");
    assertEquals(14, lines.length);
    assertEquals(
        "<rect id = 'MYRECT' x='200.000000' y='200.000000' width='50.000000' height='100.000000' fill='rgb(0.000000, 0.000000, 255.000000)' visibility='visible'>",
        lines[9]);
  }

  /**
   * Test resize.
   */
  @Test
  public void testResize() {
    System.out.print("\nTesting resize a shape:\n");
    String input = "MyProgram -in resources/test6.txt -v web -out resources/testResize.html -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
    StringBuilder content = new StringBuilder();
    try {
      content = new StringBuilder(Files.readString(Path.of("resources/testResize.html")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] lines = content.toString().split("\n");
    assertEquals(14, lines.length);
    assertEquals(
        "<rect id = 'MYRECT' x='200.000000' y='200.000000' width='25.000000' height='100.000000' fill='rgb(255.000000, 0.000000, 0.000000)' visibility='visible'>",
        lines[9]);
  }

  /**
   * Test remove.
   */
  @Test
  public void testRemove() {
    System.out.print("\nTesting remove a shape:\n");
    String input = "MyProgram -in resources/test7.txt -v web -out resources/testRemove.html -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
    StringBuilder content = new StringBuilder();
    try {
      content = new StringBuilder(Files.readString(Path.of("resources/testRemove.html")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] lines = content.toString().split("\n");
    assertEquals(12, lines.length);
  }

  /**
   * Test multi shape.
   */
  @Test
  public void testMultiShape() {
    System.out.print("\nTesting add multiple shapes:\n");
    String input = "MyProgram -in resources/test8.txt -v web -out resources/testMultiShape.html -quit";
    String[] arr = input.split(" ");
    this.control.go(arr);
    StringBuilder content = new StringBuilder();
    try {
      content = new StringBuilder(Files.readString(Path.of("resources/testMultiShape.html")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    String[] lines = content.toString().split("\n");
    assertEquals(16, lines.length);
    assertEquals(
        "<rect id = 'MYRECT' x='200.000000' y='200.000000' width='50.000000' height='100.000000' fill='rgb(255.000000, 0.000000, 0.000000)' visibility='visible'>",
        lines[9]);
    assertEquals(
        "<ellipse id = 'MYOVAL' cx='500.000000' cy='100.000000' rx='60.000000' ry='30.000000' fill='rgb(0.000000, 255.000000, 1.000000)' visibility='visible'>",
        lines[11]);
  }
}
