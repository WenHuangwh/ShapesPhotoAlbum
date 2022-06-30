package viewhelper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.Snapshot;

/**
 * The type Graphical view helper.
 */
public class GraphicalViewHelper extends JFrame implements ViewHelper, ActionListener {

  private final List<Snapshot> album; // Snapshot list from model
  private final Dimension canvasDimension;
  private final String title;
  private Snapshot snapshot; // Snapshot to display
  private final List<String> snapshotID; // Use this list to select snapshot to display
  private int snapshotIndex; // Prev, next, select button will change this index
  private GraphicalPanel snapshotPanel; // Panel to draw snapshot
  private JPanel buttonPanel; // Panel of buttons
  private JButton buttonPrev;
  private JButton buttonSelect;
  private JButton buttonNext;
  private JButton buttonQuit;

  static final int TITLE_BACKGROUND_HEIGHT = 40; // Height of ID and description area
  static final int TITLE_BACKGROUND_X = 0; // x of ID and description area
  static final int TITLE_BACKGROUND_Y = 0; // y of ID and description area
  static final int TEXT_ID_X = 10; // x of ID text
  static final int TEXT_ID_Y = 15; // y of ID text
  static final int TEXT_DESCRIPTION_Y = 33; // y of description text
  private static final String PREV = "<< Prev <<";
  private static final String SElECT = "^^ Select ^^";
  private static final String NEXT = ">> Next >>";
  private static final String QUIT = "xx Quit xx";
  private static final String SELECT_TITLE = "Snapshot ID";
  private static final String SELECT_INFO = "Choose a snapshot:";
  private static final String SELECT_WARNING = "There is only one snapshot!!!";
  private static final String NEXT_WARNING = "This is the end of this album!!!";
  private static final String PREV_WARNING = "There is the first snapshot of this album!!!";
  private static final Color BACKGROUND_COLOR = Color.gray;
  private static final Color BUTTON_BACKGROUND = Color.orange;

  /**
   * Instantiates a new Graphical view helper.
   *
   * @param album     the album
   * @param dimension the dimension
   * @param title     the title
   * @throws IllegalArgumentException the illegal argument exception
   */
  public GraphicalViewHelper(List<Snapshot> album, Dimension dimension, String title)
      throws IllegalArgumentException {
    // Check empty album
    if (album.size() < 1) {
      throw new IllegalArgumentException("Empty Album");
    }
    this.album = album;
    this.canvasDimension = dimension;
    this.title = title;
    // Default snapshot is the 1st one
    this.snapshotIndex = 0;
    this.snapshot = album.get(this.snapshotIndex);
    // Store ID in a list
    this.snapshotID = new ArrayList<>();
    for (Snapshot snapshot : this.album) {
      snapshotID.add(snapshot.getID());
    }
  }

  public void display() {
    // Set button panel
    setButtonPanel();
    this.setBackground(BACKGROUND_COLOR);
    this.setTitle(this.title);
    // Snapshot panel
    this.snapshotPanel = new GraphicalPanel(this.snapshot, this.canvasDimension);
    this.snapshotPanel.setLayout(null);
    this.add(this.snapshotPanel, BorderLayout.NORTH);
    this.add(this.buttonPanel, BorderLayout.SOUTH);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null); // Appear in the middle of screen
    this.pack();
    this.setVisible(true);
  }

  // Set button panel
  private void setButtonPanel() {
    this.buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    this.buttonPanel.setBackground(BUTTON_BACKGROUND);

    this.buttonPrev = createButton(PREV);
    this.buttonNext = createButton(NEXT);
    this.buttonSelect = createButton(SElECT);
    this.buttonQuit = createButton(QUIT);

    this.buttonPanel.add(this.buttonPrev);
    this.buttonPanel.add(this.buttonSelect);
    this.buttonPanel.add(this.buttonNext);
    this.buttonPanel.add(this.buttonQuit);
  }

  // Create button
  private JButton createButton(String text) {
    JButton button = new JButton();
    button.addActionListener(this);
    button.setText(text);
    button.setFocusable(false);
    return button;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    // Move previous
    if (e.getSource() == this.buttonPrev) {
      this.snapshotIndex--;
      // If index < 0, index reset to 0 and prompt warning
      if (this.snapshotIndex < 0) {
        JOptionPane.showMessageDialog(this, PREV_WARNING,
            null, JOptionPane.WARNING_MESSAGE);
        this.snapshotIndex++;
      }
      this.snapshot = album.get(this.snapshotIndex);
      this.snapshotPanel.updatedSnapshot(this.snapshot);
      this.snapshotPanel.repaint();
    }
    // Select a snapshot
    else if (e.getSource() == this.buttonSelect) {
      // If there are more than 1 snapshot
      if (this.snapshotID.size() > 1) {
        Object[] possibleValues = this.snapshotID.toArray();
        Object selectedValue = JOptionPane.showInputDialog(this,
            SELECT_INFO, SELECT_TITLE,
            JOptionPane.PLAIN_MESSAGE, null,
            possibleValues, possibleValues[0]);
        if (selectedValue == null) {
          return;
        }
        String selectedID = selectedValue.toString();
        this.snapshotIndex = Integer.parseInt(selectedID);
        this.snapshot = album.get(this.snapshotIndex);
        this.snapshotPanel.updatedSnapshot(this.snapshot);
        this.snapshotPanel.repaint();
      }
      // If there is only 1 snapshot
      else {
        JOptionPane.showMessageDialog(this, SELECT_WARNING,
            SELECT_TITLE, JOptionPane.INFORMATION_MESSAGE);
      }
    }
    // Move next
    else if (e.getSource() == this.buttonNext) {
      this.snapshotIndex++;
      // If index > album size, index reset to size - 1 and prompt warning
      if (this.snapshotIndex >= this.album.size()) {
        JOptionPane.showMessageDialog(this, NEXT_WARNING,
            null, JOptionPane.WARNING_MESSAGE);
        this.snapshotIndex--;
      }
      this.snapshot = album.get(this.snapshotIndex);
      this.snapshotPanel.updatedSnapshot(this.snapshot);
      this.snapshotPanel.repaint();
    }
    // Quit button
    else if (e.getSource() == this.buttonQuit) {
      System.exit(0);
    }
  }
}
